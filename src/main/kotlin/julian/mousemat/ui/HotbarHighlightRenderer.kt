package julian.mousemat.ui

import julian.mousemat.utils.ColorUtils
import julian.mousemat.utils.LoreParser
import julian.mousemat.utils.MathUtils
import net.minecraft.client.Minecraft
import net.minecraft.client.DeltaTracker
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.component.ItemLore

object HotbarHighlightRenderer {

    // defined in pixels, the size of each hotbar slot
    const val hotbarSlotSize = 20
    // defined in slots, the number of slots in the hotbar
    const val hotbarSize = 9

    fun drawHotbarHighlight(context: GuiGraphicsExtractor, tickDelta: DeltaTracker) {
        val client = Minecraft.getInstance()
        // Only render if the player is in-game
        val player = client.player ?: return

        for (slot in 0 until hotbarSize) {
            val stack: ItemStack = player.inventory.getItem(slot)

            // Skip empty slots
            if (stack.isEmpty) continue

            // Highlight player heads
            if (stack.`is`(Items.PAPER)) {
                val nbt: CompoundTag = stack.get(DataComponents.CUSTOM_DATA)?.copyTag() ?: continue
                if (nbt.getString("id").orElse("") == "SQUEAKY_MOUSEMAT") {
                    val lore: ItemLore = stack.get(DataComponents.LORE) ?: continue
                    LoreParser.extractYawPitchFromLore(lore)?.let { (yaw, pitch) ->
                        val playerYaw = player.yRot % 360
                        val playerPitch = player.xRot % 360
                        val angle = MathUtils.angleBetween(MathUtils.yawPitchToVec(playerYaw, playerPitch), MathUtils.yawPitchToVec(yaw, pitch))
                        drawHighlight(context, slot, ColorUtils.getColor(angle))
                    }
                }
            }
        }
    }

    fun getHotbarLocation(slot: Int): Pair<Int, Int>? {
        val client = Minecraft.getInstance()
        val player = client.player ?: return null

        val window = client.window
        val screenWidth = window.guiScaledWidth
        val screenHeight = window.guiScaledHeight

        // Hotbar position
        val hotbarX = (screenWidth / 2 - (hotbarSlotSize * hotbarSize / 2)) + (slot * hotbarSlotSize)
        val hotbarY = screenHeight - hotbarSlotSize - 1 // 1 pixel is hotbarborder

        return Pair(hotbarX, hotbarY)
    }

    fun drawHighlight(context: GuiGraphicsExtractor, slot: Int, color: Int) {
        val location = getHotbarLocation(slot) ?: return
        val (hotbarX, hotbarY) = location

        // 1 px less on each side to not overlap with the slot border
        context.fill(
            hotbarX + 1,
            hotbarY + 1,
            hotbarX + hotbarSlotSize - 1,
            hotbarY + hotbarSlotSize - 1,
            color
        )
    }
}