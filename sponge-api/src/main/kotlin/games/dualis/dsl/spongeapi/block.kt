package games.dualis.dsl.spongeapi

import org.spongepowered.api.block.BlockSnapshot
import org.spongepowered.api.block.BlockState
import org.spongepowered.api.block.entity.BlockEntityArchetype

/**
 * Inlined function that applies [block] to a new [BlockSnapshot.builder].
 */
inline fun blockSnapshot(crossinline block: BlockSnapshot.Builder.() -> Unit) =
    BlockSnapshot.builder()
        .apply(block)
        .build()

/**
 * Inlined function that applies [block] to a new [BlockState.builder].
 */
inline fun blockState(crossinline block: BlockState.Builder.() -> Unit) =
    BlockState.builder()
        .apply(block)
        .build()

/**
 * Inlined function that applies [block] to a new [BlockEntityArchetype.builder].
 */
inline fun blockEntityArchetype(crossinline block: BlockEntityArchetype.Builder.() -> Unit) =
    BlockEntityArchetype.builder()
        .apply(block)
        .build()
