package julian.mousemat

import julian.mousemat.ui.HotbarHighlightRenderer
import net.fabricmc.api.ClientModInitializer

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements

import net.minecraft.resources.Identifier

object HypixelSqueakyMousematHelper : ClientModInitializer {

    override fun onInitializeClient() {
        HudElementRegistry.attachElementAfter(VanillaHudElements.HOTBAR, Identifier.parse("hypixel-squeaky-mousemat-helper:hotbar_highlight"), HotbarHighlightRenderer::drawHotbarHighlight)
    }
}