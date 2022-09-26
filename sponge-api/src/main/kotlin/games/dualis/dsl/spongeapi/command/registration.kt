package games.dualis.dsl.spongeapi.command

import games.dualis.dsl.spongeapi.listener
import org.jetbrains.annotations.ApiStatus
import org.spongepowered.api.command.Command
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent
import org.spongepowered.plugin.PluginContainer

/**
 * A class used for syntax purposes.
 */
@ApiStatus.Internal
class KCommandRegistration(
    private val event: RegisterCommandEvent<Command.Parameterized>,
    private val container: PluginContainer
) {

    /**
     * Registers given [cmd] with [this] [String] as an alias.
     */
    infix fun String.runs(cmd: Command.Parameterized) = event.register(container, cmd, this)

    /**
     * Registers given [cmd] with [this] array of [String] as aliases.
     */
    infix fun Array<String>.runs(cmd: Command.Parameterized) =
        event.register(container, cmd, this[0], *this.drop(1).toTypedArray())

    /**
     * Registers given [cmd] with [this] list of [String] as aliases.
     */
    infix fun List<String>.runs(cmd: Command.Parameterized) =
        event.register(container, cmd, this[0], *this.drop(1).toTypedArray())

}


/**
 * A DSL syntax to register commands.
 */
inline fun commands(container: PluginContainer, crossinline block: KCommandRegistration.() -> Unit) =
    container.listener<RegisterCommandEvent<Command.Parameterized>> {
        block(KCommandRegistration(this, container))
    }
