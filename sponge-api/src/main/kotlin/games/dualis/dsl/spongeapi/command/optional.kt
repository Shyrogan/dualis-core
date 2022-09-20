@file:Suppress("NOTHING_TO_INLINE")

package games.dualis.dsl.spongeapi.command

import org.jetbrains.annotations.ApiStatus
import org.spongepowered.api.command.parameter.Parameter

/**
 * A class used for syntax purposes.
 */
@ApiStatus.Internal
data class KNullableParameter<T>(val builder: Parameter.Value.Builder<T>) :
    Parameter.Value.Builder<T> by builder {

    init {
        builder.optional()
    }
}

/**
 * Marks given [Parameter.Value.Builder] as `nullable` meaning the value is optional and the delegation
 * will actually return a nullable value.
 */
inline fun <T> Parameter.Value.Builder<T>.nullable() = KNullableParameter(this)
