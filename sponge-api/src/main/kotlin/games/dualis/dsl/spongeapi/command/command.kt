@file:Suppress("UNCHECKED_CAST")

package games.dualis.dsl.spongeapi.command

import org.jetbrains.annotations.ApiStatus
import org.spongepowered.api.command.Command
import org.spongepowered.api.command.parameter.Parameter
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A class used for syntax purposes.
 */
@ApiStatus.Internal
class KCommand(private val command: Command.Builder) : Command.Builder by command {

    /**
     * Stores the values for each parameter.
     */
    internal val dslParameters = mutableMapOf<Parameter.Key<*>, Any?>()

    /**
     * Provides delegations for non-nullable command [Parameter]s.
     */
    operator fun <T> Parameter.Value.Builder<T>.provideDelegate(
        _this: Any?, property: KProperty<*>
    ): ReadOnlyProperty<Any?, T> {
        val parameter = key(property.name)
            .build()
            .also(command::addParameter)
            .also { p -> dslParameters[p.key()] = null }
        return ReadOnlyProperty { _, _ -> dslParameters[parameter.key()] as T }
    }

    /**
     * Provides delegations for nullable command [Parameter]s.
     */
    operator fun <T> KNullableParameter<T>.provideDelegate(
        _this: Any?, property: KProperty<*>
    ): ReadOnlyProperty<Any?, T?> {
        val parameter = key(property.name)
            .build()
            .also(command::addParameter)
            .also { p -> dslParameters[p.key()] = null }
        return ReadOnlyProperty { _, _ -> dslParameters[parameter.key()] as T? }
    }

    /**
     * Registers given [cmd] with [this] [String] as an alias.
     */
    infix fun String.runs(cmd: Command.Parameterized) = addChild(cmd, this)

    /**
     * Registers given [cmd] with [this] array of [String] as aliases.
     */
    infix fun Array<String>.runs(cmd: Command.Parameterized) =
        addChild(cmd, this[0], *this.drop(1).toTypedArray())

    /**
     * Registers given [cmd] with [this] list of [String] as aliases.
     */
    infix fun List<String>.runs(cmd: Command.Parameterized) =
        addChild(cmd, this[0], *this.drop(1).toTypedArray())

}

/**
 * Builds a new [KCommand] using given [block].
 */
inline fun command(crossinline block: KCommand.() -> Unit) =
    KCommand(Command.builder())
        .apply(block)
        .build()
