@file:Suppress("NOTHING_TO_INLINE")

package games.dualis.dsl.adventure

import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.util.HSVLike
import net.kyori.adventure.util.RGBLike

/**
 * Colorizes this [Style.Builder] with given [value].
 */
inline fun Style.Builder.color(value: Int) =
    this.color(TextColor.color(value))

/**
 * Colorizes this [Style.Builder] with given [rgb].
 */
inline fun Style.Builder.color(rgb: RGBLike) =
    this.color(TextColor.color(TextColor.color(rgb)))

/**
 * Colorizes this [Style.Builder] with given [hsv].
 */
inline fun Style.Builder.color(hsv: HSVLike) =
    this.color(TextColor.color(TextColor.color(hsv)))

/**
 * Colorizes this [Style.Builder] with given [r] [g] [b].
 */
inline fun Style.Builder.color(r: Int, g: Int, b: Int) =
    this.color(TextColor.color(TextColor.color(r, g, b)))

/**
 * Colorizes this [Style.Builder] with given [r] [g] [b].
 */
inline fun Style.Builder.color(r: Float, g: Float, b: Float) =
    this.color(TextColor.color(TextColor.color(r, g, b)))
