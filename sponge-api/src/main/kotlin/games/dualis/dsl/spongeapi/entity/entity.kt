package games.dualis.dsl.spongeapi

import org.spongepowered.api.entity.EntityArchetype
import org.spongepowered.api.entity.EntitySnapshot

/**
 * Inlined function that applies [block] to a new [EntityArchetype.builder].
 */
inline fun entityArchetype(crossinline block: EntityArchetype.Builder.() -> Unit) =
    EntityArchetype.builder()
        .apply(block)
        .build()

/**
 * Inlined function that applies [block] to a new [EntitySnapshot.builder].
 */
inline fun entitySnapshot(crossinline block: EntitySnapshot.Builder.() -> Unit) =
    EntitySnapshot.builder()
        .apply(block)
        .build()
