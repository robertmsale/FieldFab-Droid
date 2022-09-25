package com.just1guy.fieldfab.math

import Vector2Float
import Vector3Float
import Vector4Float
import Vector2Double
import Vector3Double
import Vector4Double
import Vector2Int
import Vector3Int
import Vector4Int
import androidx.core.math.MathUtils.clamp
import kotlin.math.abs

enum class V2Axis {X, Y}
enum class V3Axis {X, Y, Z}
enum class V4Axis {X, Y, Z, W}

fun Float.distance(to: Float): Float =
    if (this < to) abs(to - this) else abs(this - to)
fun Double.distance(to: Double): Double =
    if (this < to) abs(to - this) else abs(this - to)
fun Int.distance(to: Int): Int =
    if (this < to) abs(to - this) else abs(this - to)

fun Float.clamp(min: Float, max: Float): Float = clamp(this, min, max)
fun Double.clamp(min: Double, max: Double): Double = clamp(this, min, max)
fun Int.clamp(min: Int, max: Int): Int = clamp(this, min, max)

fun Vector3Float.cross(with: Vector3Float) {
    val nx = y * with.z - z * with.y
    val ny = z * with.x - x * with.z
    val nz = x * with.y - y * with.x
    x = nx; y = ny; z = nz
}

fun Vector3Float.crossed(with: Vector3Float): Vector3Float {
    var retval = copy()
    retval.cross(with)
    return retval
}

fun Vector3Float.angle(to: Vector3Float): Float {
    val d = kotlin.math.sqrt(lengthSquared() * to.lengthSquared())
    if (d == 0F) return kotlin.math.PI.toFloat() / 2F
    val theta = dotProduct(to) / d
    return kotlin.math.acos(clamp(theta, -1F, 1F))
}

operator fun Vector3Float.get(axis: V3Axis): Float = when (axis) {
    V3Axis.X -> x
    V3Axis.Y -> y
    V3Axis.Z -> z
}
