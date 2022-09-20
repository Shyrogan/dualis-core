@file:Suppress("NOTHING_TO_INLINE")

package games.dualis.dsl.adventure

import net.kyori.adventure.audience.*

/**
 * Creates an [Audience] that forwards to many other instances.
 *
 * The underlying `Iterable` is not copied, therefore any changes
 * made will be reflected in `Audience`.
 */
inline fun Iterable<Audience>.forward() = Audience.audience(this)

/**
 * Creates an [Audience] that forwards to many other instances.
 *
 * The underlying `Iterable` is not copied, therefore any changes
 * made will be reflected in `Audience`.
 */
inline fun Array<Audience>.forward() = Audience.audience(this.asIterable())
