package julian.mousemat.utils

import kotlin.math.PI
import kotlin.math.roundToInt

object ColorUtils {

    private fun lerp(a: Int, b: Int, t: Float) =
        (a + (b - a) * t).roundToInt()

    fun getColor(rad: Double): Int {
        val start = 0x80FF0000.toInt()
        val end = 0x8000FF00.toInt()

        val t = ((PI - rad) / PI).toFloat().coerceIn(0f, 1f)

        val a = lerp((start ushr 24) and 0xFF, (end ushr 24) and 0xFF, t)
        val r = lerp((start ushr 16) and 0xFF, (end ushr 16) and 0xFF, t)
        val g = lerp((start ushr 8) and 0xFF, (end ushr 8) and 0xFF, t)
        val b = lerp(start and 0xFF, end and 0xFF, t)

        return (a shl 24) or (r shl 16) or (g shl 8) or b
    }
}