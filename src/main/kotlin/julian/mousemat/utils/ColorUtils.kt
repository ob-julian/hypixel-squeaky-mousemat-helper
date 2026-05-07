package julian.mousemat.utils

import net.minecraft.util.math.ColorHelper
import kotlin.math.PI

object ColorUtils {

    fun getColor(rad: Double): Int {
        val wayOffColor = 0x80FF0000.toInt() // Red
        val closeColor = 0x8000FF00.toInt() // Green
        val lerpedAmount = (PI - rad) / PI
        val interpolated = ColorHelper.lerp(lerpedAmount.toFloat(), wayOffColor, closeColor)
        return interpolated
    }
}