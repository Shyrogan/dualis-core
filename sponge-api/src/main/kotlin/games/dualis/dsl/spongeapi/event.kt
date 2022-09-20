package games.dualis.dsl.spongeapi

import io.leangen.geantyref.TypeToken
import org.spongepowered.api.event.Event
import org.spongepowered.api.event.EventListenerRegistration
import org.spongepowered.api.event.Order
import org.spongepowered.plugin.PluginContainer

/**
 * Returns a new [EventListenerRegistration] that runs [block].
 */
inline fun <reified T : Event> PluginContainer.listener(
    order: Order = Order.DEFAULT,
    beforeModifications: Boolean = true,
    crossinline block: T.() -> Unit
) = EventListenerRegistration.builder(object : TypeToken<T>() {})
    .plugin(this)
    .order(order)
    .beforeModifications(beforeModifications)
    .listener { e -> block(e) }
