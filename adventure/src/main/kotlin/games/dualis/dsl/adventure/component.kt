@file:Suppress("NOTHING_TO_INLINE")

package games.dualis.dsl.adventure

import net.kyori.adventure.text.*
import net.kyori.adventure.text.Component.*
import net.kyori.adventure.text.format.Style

/**
 * A DSL-like syntax for [Component] builders.
 */
inline fun textComponent(crossinline block: TextComponent.Builder.() -> Unit) =
    text().apply(block).build()

/**
 * Converts a [String] to a [TextComponent].
 */
inline fun String.component() = text(this)

/**
 * Converts a [String] to a [TextComponent].
 */
inline fun String.component(style: Style) =
    this.component().style(style)

/**
 * Converts a [String] to a [TextComponent].
 */
inline fun String.component(noinline style: Style.Builder.() -> Unit) =
    this.component().style(style)
