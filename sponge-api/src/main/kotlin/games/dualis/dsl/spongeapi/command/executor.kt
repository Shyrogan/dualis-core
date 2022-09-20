package games.dualis.dsl.spongeapi.command

import org.jetbrains.annotations.ApiStatus
import org.spongepowered.api.command.CommandExecutor
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.parameter.CommandContext

/**
 * A class used for syntax purposes.
 */
@ApiStatus.Internal
abstract class KCommandExecutor(private val command: KCommand) : CommandExecutor {
    override fun execute(context: CommandContext): CommandResult {
        command.dslParameters.entries.forEach {
            it.setValue(context.one(it.key).get())
        }
        return CommandResult.success()
    }
}

/**
 * Builds a new [KCommandExecutor] using given [block].
 */
inline fun KCommand.execute(crossinline block: CommandContext.() -> CommandResult) {
    executor(object : KCommandExecutor(this) {
        override fun execute(context: CommandContext): CommandResult {
            super.execute(context)
            return block(context)
        }
    })
}
