package julian.mousemat.utils

import net.minecraft.world.item.component.ItemLore

object LoreParser {

    fun extractYawPitchFromLore(lore: ItemLore): Pair<Float, Float>? {
        var yaw: Float? = null
        var pitch: Float? = null

        for (line in lore.lines) {
            val text = line.string.trim()
            if (text.startsWith("Selected Yaw:")) {
                yaw = text.substringAfter("Selected Yaw: ").toFloatOrNull()
            } else if (text.startsWith("Selected Pitch:")) {
                pitch = text.substringAfter("Selected Pitch: ").toFloatOrNull()
            }
        }

        return if (yaw != null && pitch != null) Pair(yaw, pitch) else null
    }
}