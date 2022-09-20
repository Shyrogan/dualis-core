@file:Suppress("NOTHING_TO_INLINE")

package games.dualis.dsl.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.ScopedComponent

/**
 * Implements the concatenation operation of two [Component] with Kotlin's `+`
 * operator.
 *
 * ```kotlin
 * val result = text1 + text2 // yay!
 * ```
 */
inline operator fun <C, T : ScopedComponent<C>> T.plus(other: ComponentLike): C =
    this.append(other)

/**
 * Implements the concatenation operation of one [String] to a [Component] with Kotlin's
 * `+` operator.
 *
 * ```kotlin
 * val result = text1 + "hello" // yay!
 * ```
 */
inline operator fun <C, T : ScopedComponent<C>> T.plus(other: String): C =
    this.append(other.component())

/**
 * Implements the join operation of a [Iterable] of [Component].
 * ```kotlin
 * val componentList: List<Component> = ...
 * sendMessage(componentList.join(" ".component())) // yay!
 * ```
 */
inline fun Iterable<ScopedComponent<*>>.joinToText(
    separator: ComponentLike,
) = textComponent {
    val iterator = iterator()
    while (iterator.hasNext()) {
        append(iterator.next())
        if(iterator.hasNext())
            append(separator)
    }
}

/**
 * Implements the join operation of a [Component] [Iterable].
 * ```kotlin
 * val componentList: List<Component> = ...
 * sendMessage(componentList.join(" ")) // yay!
 * ```
 *
 * @param separator the string to include between every component
 */
inline fun Iterable<ScopedComponent<*>>.joinToText(
    separator: String,
) = joinToText(separator.component())
