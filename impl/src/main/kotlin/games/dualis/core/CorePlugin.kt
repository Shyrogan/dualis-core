package games.dualis.core

import com.google.inject.Inject
import games.dualis.core.Core.dataSource
import games.dualis.core.command.REDIS_COMMAND
import games.dualis.core.command.SQL_COMMAND
import games.dualis.core.config.RedisConfig
import games.dualis.core.config.SQLConfig
import games.dualis.dsl.koin.pluginKoinModule
import games.dualis.dsl.spongeapi.command.commands
import org.apache.logging.log4j.Logger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.spongepowered.api.config.DefaultConfig
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.lifecycle.ConstructPluginEvent
import org.spongepowered.api.event.lifecycle.StoppedGameEvent
import org.spongepowered.plugin.PluginContainer
import java.io.Closeable
import java.nio.file.Path
import javax.sql.DataSource

data class CorePlugin @Inject constructor(
    val logger: Logger,
    val container: PluginContainer,
    @DefaultConfig(sharedRoot = false)
    val defaultConfig: Path
) {

    init {
        @Suppress("UnstableApiUsage")
        commands(container) {
            SQL_COMMAND handles "sql"
            REDIS_COMMAND handles "redis"
        }
    }

    @Listener
    fun onPluginConstruct(e: ConstructPluginEvent) {
        val hikari = SQLConfig.loadOrDefault(defaultConfig.parent).hikariDataSource
        val redis = RedisConfig.loadOrDefault(defaultConfig.parent).jedisPool
        startKoin {
            val coreModule = module {
                // HikariCP
                single<DataSource> { hikari }
                single { redis }
            }
            // First loads the Koin module of the plugin
            // Then initializes the services provided by the core.
            modules(container.pluginKoinModule, coreModule)
        }
    }

    @Listener
    fun onGameStop(e: StoppedGameEvent) {
        (dataSource as Closeable).close()
    }

}
