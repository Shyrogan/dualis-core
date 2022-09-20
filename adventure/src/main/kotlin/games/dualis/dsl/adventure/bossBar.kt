package games.dualis.dsl.adventure

import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.*
import org.jetbrains.annotations.ApiStatus

/**
 * A class used for syntax purposes.
 */
@ApiStatus.Internal
class KBossBar(
    private var name: ComponentLike,
    private var progress: Float = 0F,
    private var color: BossBar.Color = BossBar.Color.RED,
    private var overlay: BossBar.Overlay = BossBar.Overlay.PROGRESS,
    private var flags: Set<BossBar.Flag> = emptySet(),
) {
    fun progress(progress: Float) {
        this.progress = progress
    }

    fun color(color: BossBar.Color) {
        this.color = color
    }

    fun overlay(overlay: BossBar.Overlay) {
        this.overlay = overlay
    }

    fun flags(vararg flags: BossBar.Flag) {
        this.flags = flags.toSet()
    }

    @ApiStatus.Internal
    fun build() = BossBar.bossBar(name, progress, color, overlay, flags)
}

/**
 * Creates a new boss bar.
 */
inline fun bossBar(
    name: ComponentLike,
    crossinline block: KBossBar.() -> Unit,
) = KBossBar(name).apply(block).build()

/**
 * Creates a new boss bar.
 */
@JvmName("_bossBar")
inline fun ComponentLike.bossBar(crossinline block: KBossBar.() -> Unit) =
    bossBar(this, block)

/**
 * Creates a new boss bar.
 */
inline fun String.bossBar(
    crossinline block: KBossBar.() -> Unit,
) = this.component().bossBar(block)

/**
 * When the name is changed.
 */
inline fun BossBar.nameChanged(
    crossinline block: BossBar.(
        oldName: Component,
        newName: Component,
    ) -> Unit,
) = this.addListener(object : BossBar.Listener {
    override fun bossBarNameChanged(
        _bar: BossBar,
        oldName: Component,
        newName: Component,
    ) = block(oldName, newName)
})

/**
 * When the progress is changed.
 */
inline fun BossBar.progressChanged(
    crossinline block: BossBar.(
        oldProgression: Float,
        newProgression: Float,
    ) -> Unit,
) = this.addListener(object : BossBar.Listener {
    override fun bossBarProgressChanged(
        _bar: BossBar,
        oldProgress: Float,
        newProgress: Float,
    ) = block(oldProgress, newProgress)
})

/**
 * When the color is changed.
 */
inline fun BossBar.colorChanged(
    crossinline block: BossBar.(
        oldColor: BossBar.Color,
        newColor: BossBar.Color,
    ) -> Unit,
) = this.addListener(object : BossBar.Listener {
    override fun bossBarColorChanged(
        _bar: BossBar,
        oldColor: BossBar.Color,
        newColor: BossBar.Color,
    ) = block(oldColor, newColor)
})

/**
 * When the overlay is changed.
 */
inline fun BossBar.overlayChanged(
    crossinline block: BossBar.(
        oldOverlay: BossBar.Overlay,
        newOverlay: BossBar.Overlay,
    ) -> Unit,
) = this.addListener(object : BossBar.Listener {
    override fun bossBarOverlayChanged(
        _bar: BossBar,
        oldOverlay: BossBar.Overlay,
        newOverlay: BossBar.Overlay,
    ) = block(oldOverlay, newOverlay)
})

/**
 * When a flag is changed.
 */
inline fun BossBar.flagChanged(
    crossinline block: BossBar.(
        oldFlag: Set<BossBar.Flag>,
        newFlag: Set<BossBar.Flag>,
    ) -> Unit,
) = this.addListener(object : BossBar.Listener {
    override fun bossBarFlagsChanged(
        bar: BossBar,
        oldFlag: Set<BossBar.Flag>,
        newFlag: Set<BossBar.Flag>,
    ) = block(oldFlag, newFlag)
})
