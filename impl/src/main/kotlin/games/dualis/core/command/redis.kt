@file:Suppress("UnstableApiUsage")

package games.dualis.core.command

import games.dualis.core.Core.jedisPool
import games.dualis.dsl.adventure.component
import games.dualis.dsl.spongeapi.command.command
import games.dualis.dsl.spongeapi.command.execute
import net.kyori.adventure.text.format.NamedTextColor
import org.spongepowered.api.command.CommandResult.success
import kotlin.system.measureNanoTime


val REDIS_COMMAND = command {
    permission("dualis.admin.redis.status")
    execute {
        val audience = cause().audience()

        audience.sendMessage("Redis requests for status sent. Please wait...".component {
            color(NamedTextColor.GRAY)
        })
        val dummyQueryTime = measureNanoTime {
            jedisPool.exists("dummy")
        }
        audience.sendMessage(("Dummy query executed in ${dummyQueryTime * 1e-6}ms!").component {
            color(NamedTextColor.GREEN)
        })

        success()
    }
}