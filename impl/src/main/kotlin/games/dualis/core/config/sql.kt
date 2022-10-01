package games.dualis.core.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import games.dualis.dsl.spongeapi.hoconLoader
import org.postgresql.Driver
import org.spongepowered.configurate.objectmapping.ConfigSerializable
import java.nio.file.Path
import kotlin.io.path.createFile
import kotlin.io.path.div
import kotlin.io.path.exists

@ConfigSerializable
data class SQLConfig @JvmOverloads constructor(
    val host: String = "localhost", val username: String = "root", val password: String = "",
    val database: String = "db", val port: Int = 3306
) {

    /**
     * Returns a new [HikariDataSource] based on this configuration.
     */
    val hikariDataSource: HikariDataSource
        get() = HikariDataSource(HikariConfig().also { c ->
            c.jdbcUrl = "jdbc:postgresql://${host}:${port}/${database}"
            c.username = username
            if (password.isNotEmpty()) c.password = password
            c.driverClassName = Driver::class.java.name
        })

    companion object {
        /**
         * Loads the configuration in given [configFolder].
         * If the configuration file does not exist, creates one with the default configuration inside.
         */
        fun loadOrDefault(configFolder: Path): SQLConfig {
            val sqlConfigPath = configFolder / "sql.conf"
            val sqlLoader = hoconLoader(sqlConfigPath)
            return if (sqlConfigPath.exists()) {
                val sqlNode = sqlLoader.load()
                sqlNode[SQLConfig::class.java] ?: SQLConfig()
            } else {
                sqlConfigPath.createFile()
                val sqlNode = sqlLoader.load()

                SQLConfig().also { conf ->
                    sqlNode.set(conf)
                    sqlLoader.save(sqlNode)
                }
            }
        }
    }

}
