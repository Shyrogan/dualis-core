package games.dualis.dsl.spongeapi

import org.spongepowered.api.advancement.Advancement
import org.spongepowered.api.advancement.DisplayInfo
import org.spongepowered.api.advancement.criteria.AdvancementCriterion

/**
 * Inlined function that applies [block] to a new [Advancement.builder].
 */
inline fun advancement(crossinline block: Advancement.Builder.() -> Unit) =
    Advancement.builder()
        .apply(block)
        .build()

/**
 * Inlined function that applies [block] to a new [DisplayInfo.builder].
 */
inline fun Advancement.Builder.display(crossinline block: DisplayInfo.Builder.() -> Unit) =
    DisplayInfo.builder()
        .apply(block)
        .build()
        .apply { displayInfo(this) }

/**
 * Inlined function that applies [block] to a new [DisplayInfo.builder].
 */
inline fun Advancement.Builder.criterion(crossinline block: AdvancementCriterion.Builder.() -> Unit) =
    AdvancementCriterion.builder()
        .apply(block)
        .build()
        .apply { criterion(this) }
