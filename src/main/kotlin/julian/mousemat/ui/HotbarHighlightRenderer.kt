package julian.mousemat.ui

import julian.mousemat.utils.ColorUtils
import julian.mousemat.utils.LoreParser
import julian.mousemat.utils.MathUtils
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.render.RenderTickCounter
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.LoreComponent
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound

object HotbarHighlightRenderer {

    const val hotbarSlotSize = 20
    const val hotbarSize = 9

    fun drawHotbarHighlight(context: DrawContext, tickDelta: RenderTickCounter) {
        val client = MinecraftClient.getInstance()
        // Only render if the player is in-game
        val player = client.player ?: return

        for (slot in 0 until hotbarSize) {
            val stack: ItemStack = player.inventory.getStack(slot)

            // Skip empty slots
            if (stack.isEmpty) continue

            // Highlight player heads
            if (stack.isOf(Items.PLAYER_HEAD)) {
                val nbt: NbtCompound = stack.get(DataComponentTypes.CUSTOM_DATA)?.copyNbt() ?: continue
                if (nbt.getString("id").orElse("") == "SQUEAKY_MOUSEMAT") {
                    val lore: LoreComponent = stack.get(DataComponentTypes.LORE) ?: continue
                    LoreParser.extractYawPitchFromLore(lore)?.let { (yaw, pitch) ->
                        val playerYaw = player.yaw % 360
                        val playerPitch = player.pitch % 360
                        val angle = MathUtils.angleBetween(MathUtils.yawPitchToVec(playerYaw, playerPitch), MathUtils.yawPitchToVec(yaw, pitch))
                        drawHighlight(context, slot, ColorUtils.getColor(angle))
                    }
                }
            }
        }
    }

    fun getHotbarLocation(slot: Int): Pair<Int, Int>? {
        val client = MinecraftClient.getInstance()
        val player = client.player ?: return null

        val window = client.window
        val screenWidth = window.scaledWidth
        val screenHeight = window.scaledHeight

        // Hotbar position
        val hotbarX = (screenWidth / 2 - (hotbarSlotSize * hotbarSize / 2)) + (slot * hotbarSlotSize)
        val hotbarY = screenHeight - hotbarSlotSize - 1 // 1 pixel is hotbarborder

        return Pair(hotbarX, hotbarY)
    }

    fun drawHighlight(context: DrawContext, slot: Int, color: Int) {
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