package julian.mousemat.utils

import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object MathUtils {

    data class Vec3(
        val x: Double,
        val y: Double,
        val z: Double
    ) {
        fun dot(other: Vec3): Double {
            return x * other.x + y * other.y + z * other.z
        }

        fun length(): Double {
            return sqrt(x * x + y * y + z * z)
        }

        fun normalize(): Vec3 {
            val len = length()
            return Vec3(x / len, y / len, z / len)
        }
    }

    fun angleBetween(a: Vec3, b: Vec3): Double {
        val magA = a.length()
        val magB = b.length()

        if (magA == 0.0 || magB == 0.0) return Double.NaN

        val dot = a.dot(b) / (magA * magB)

        return acos(dot.coerceIn(-1.0, 1.0)) // radians
    }

    fun yawPitchToVec(yaw: Float, pitch: Float): Vec3 {
        val yawRad = Math.toRadians((-yaw - 180.0))
        val pitchRad = Math.toRadians((-pitch).toDouble())

        val x = sin(yawRad) * cos(pitchRad)
        val y = sin(pitchRad)
        val z = cos(yawRad) * cos(pitchRad)

        return Vec3(x, y, z).normalize()
    }
}