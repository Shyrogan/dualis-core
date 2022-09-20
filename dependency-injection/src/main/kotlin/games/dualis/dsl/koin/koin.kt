package games.dualis.dsl.koin

import org.koin.core.module.Module
import org.koin.dsl.module
import org.spongepowered.api.*
import org.spongepowered.api.config.ConfigManager
import org.spongepowered.api.data.DataManager
import org.spongepowered.api.event.EventManager
import org.spongepowered.api.network.channel.ChannelManager
import org.spongepowered.api.plugin.PluginManager
import org.spongepowered.api.scheduler.Scheduler
import org.spongepowered.api.service.ServiceProvider
import org.spongepowered.api.sql.SqlManager
import org.spongepowered.api.util.metric.MetricsConfigManager
import org.spongepowered.plugin.PluginContainer

/**
 * Returns a Koin [Module] that injects basic managers/implementations of [Sponge].
 */
inline val PluginContainer.pluginKoinModule: Module
    get() = module {
        single { this@pluginKoinModule }
        single<Platform> { Sponge.platform() }
        single<DataManager> { Sponge.dataManager() }
        single<PluginManager> { Sponge.pluginManager() }
        single<EventManager> { Sponge.eventManager() }
        single<ConfigManager> { Sponge.configManager() }
        single<ChannelManager> { Sponge.channelManager() }
        single<Server> { Sponge.server() }
        single<Client> { Sponge.client() }
        single<SystemSubject> { Sponge.systemSubject() }
        single<MetricsConfigManager> { Sponge.metricsConfigManager() }
        single<Scheduler> { Sponge.asyncScheduler() }
        single<SqlManager> { Sponge.sqlManager() }
        single<ServiceProvider.GameScoped> { Sponge.serviceProvider() }
    }