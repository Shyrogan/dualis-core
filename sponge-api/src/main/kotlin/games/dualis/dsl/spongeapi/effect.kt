package games.dualis.dsl.spongeapi

import org.spongepowered.api.effect.particle.ParticleEffect
import org.spongepowered.api.effect.potion.PotionEffect

/**
 * Inlined function that applies [block] to a new [ParticleEffect.builder].
 */
inline fun particleEffect(crossinline block: ParticleEffect.Builder.() -> Unit) =
    ParticleEffect.builder()
        .apply(block)
        .build()

/**
 * Inlined function that applies [block] to a new [PotionEffect.builder].
 */
inline fun potionEffect(crossinline block: PotionEffect.Builder.() -> Unit) =
    PotionEffect.builder()
        .apply(block)
        .build()