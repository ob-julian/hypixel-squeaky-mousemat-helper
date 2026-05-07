package julian.mousemat

import julian.mousemat.utils.LoreParser
import julian.mousemat.utils.MathUtils
import org.junit.jupiter.api.Test
import kotlin.math.PI
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import net.minecraft.component.type.LoreComponent
import net.minecraft.text.Text
import net.minecraft.util.Formatting

class HypixelSqueakyMousematHelperTest {

    @Test
    fun testExtractYawPitchFromLore() {
        val yawLine = Text.empty()
            .append(Text.literal("Selected Yaw: ").formatted(Formatting.GRAY))
            .append(Text.literal("100.5").formatted(Formatting.GREEN))
        val pitchLine = Text.empty()
            .append(Text.literal("Selected Pitch: ").formatted(Formatting.GRAY))
            .append(Text.literal("45.8").formatted(Formatting.GREEN))
        val lore = LoreComponent(listOf(yawLine, pitchLine))

        val result = LoreParser.extractYawPitchFromLore(lore)

        assertEquals(Pair(100.5f, 45.8f), result)
    }

    @Test
    fun testAngleBetweenSameDirectionIsZero() {
        val a = MathUtils.Vec3(1.0, 0.0, 0.0)
        val b = MathUtils.Vec3(1.0, 0.0, 0.0)

        val result = MathUtils.angleBetween(a, b)

        assertEquals(0.0, result, 1e-9)
    }

    @Test
    fun testAngleBetweenOrthogonalVectorsIsPiOverTwo() {
        val a = MathUtils.Vec3(1.0, 0.0, 0.0)
        val b = MathUtils.Vec3(0.0, 1.0, 0.0)

        val result = MathUtils.angleBetween(a, b)

        assertEquals(PI / 2.0, result, 1e-9)
    }

    @Test
    fun testAngleBetweenOppositeVectorsIsPi() {
        val a = MathUtils.Vec3(1.0, 0.0, 0.0)
        val b = MathUtils.Vec3(-1.0, 0.0, 0.0)

        val result = MathUtils.angleBetween(a, b)

        assertEquals(PI, result, 1e-9)
    }

    @Test
    fun testAngleBetweenZeroVectorReturnsNaN() {
        val a = MathUtils.Vec3(0.0, 0.0, 0.0)
        val b = MathUtils.Vec3(1.0, 0.0, 0.0)

        val result = MathUtils.angleBetween(a, b)

        assertTrue(result.isNaN())
    }
}