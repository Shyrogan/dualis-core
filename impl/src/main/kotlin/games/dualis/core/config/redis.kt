package games.dualis.core.config

import com.zaxxer.hikari.HikariDataSource
import games.dualis.dsl.spongeapi.hoconLoader
import org.spongepowered.configurate.objectmapping.ConfigSerializable
import redis.clients.jedis.JedisPooled
import java.nio.file.Path
import kotlin.io.path.createFile
import kotlin.io.path.div
import kotlin.io.path.exists

@ConfigSerializable
data class RedisConfig @JvmOverloads constructor(
    val host: String = "localhost",
    val port: Int = 6379
) {

    /**
     * Returns a new [HikariDataSource] based on this configuration.
     */
    val jedisPool: JedisPooled
        get() = JedisPooled(host, port)

    companion object {
        /**
         * Loads the configuration in given [configFolder].
         * If the configuration file does not exist, creates one with the default configuration inside.
         */
        fun loadOrDefault(configFolder: Path): RedisConfig {
            val redisConfigPath = configFolder / "redis.conf"
            val redisLoader = hoconLoader(redisConfigPath)
            return if (redisConfigPath.exists()) {
                val node = redisLoader.load()
                node[RedisConfig::class.java] ?: RedisConfig()
            } else {
                redisConfigPath.createFile()
                val node = redisLoader.load()

                RedisConfig().also { conf ->
                    node.set(conf)
                    redisLoader.save(node)
                }
            }
        }
    }

}
