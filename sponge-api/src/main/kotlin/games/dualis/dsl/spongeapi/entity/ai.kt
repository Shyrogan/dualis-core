package games.dualis.dsl.spongeapi.entity

import org.spongepowered.api.entity.ai.goal.builtin.LookAtGoal
import org.spongepowered.api.entity.ai.goal.builtin.LookRandomlyGoal
import org.spongepowered.api.entity.ai.goal.builtin.SwimGoal
import org.spongepowered.api.entity.living.Agent

/**
 * Inlined function that applies [block] to a new [LookAtGoal.builder].
 */
inline fun Agent.lookAtGoal(crossinline block: LookAtGoal.Builder.() -> Unit) =
    LookAtGoal.builder()
        .apply(block)
        .build(this)

/**
 * Inlined function that applies [block] to a new [LookRandomlyGoal.builder].
 */
inline fun Agent.lookRandomlyGoal(crossinline block: LookRandomlyGoal.Builder.() -> Unit) =
    LookRandomlyGoal.builder()
        .apply(block)
        .build(this)

/**
 * Inlined function that applies [block] to a new [SwimGoal.builder].
 */
inline fun Agent.swimGoal(crossinline block: SwimGoal.Builder.() -> Unit) =
    SwimGoal.builder()
        .apply(block)
        .build(this)
