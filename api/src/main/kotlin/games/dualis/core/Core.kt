package games.dualis.core

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.spongepowered.plugin.PluginContainer
import redis.clients.jedis.JedisPooled
import javax.sql.DataSource

object Core : KoinComponent {

    val pluginContainer by inject<PluginContainer>()
    val dataSource by inject<DataSource>()
    val jedisPool by inject<JedisPooled>()

}