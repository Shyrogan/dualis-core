@file:Suppress("UnstableApiUsage")

package games.dualis.core.command

import games.dualis.core.Core
import games.dualis.dsl.adventure.component
import games.dualis.dsl.spongeapi.command.command
import games.dualis.dsl.spongeapi.command.execute
import net.kyori.adventure.text.format.NamedTextColor
import org.spongepowered.api.command.CommandResult.success
import java.sql.Connection
import kotlin.system.measureNanoTime


val SQL_COMMAND = command {
    permission("dualis.admin.sql.status")
    execute {
        val audience = cause().audience()

        audience.sendMessage("SQL requests for status sent. Please wait...".component {
            color(NamedTextColor.GRAY)
        })
        var connection: Connection
        val openingConnectionTime = measureNanoTime {
            connection = Core.dataSource.connection
        }
        val dummyQueryTime = measureNanoTime {
            connection.prepareStatement(
                "SELECT id FROM regexp_split_to_table('1,2,3,4,5,7,8,9', ',') AS id"
            ).executeQuery()
        }
        audience.sendMessage(("Opened connection in ${openingConnectionTime * 1e-6}ms. Dummy query" +
                " executed in ${dummyQueryTime * 1e-6}ms!").component {
            color(NamedTextColor.GREEN)
        })

        success()
    }
}