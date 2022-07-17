package com.just1guy.fieldfab.math

import Vector2
import Vector3
import Vector4
import androidx.core.math.MathUtils.clamp
import java.lang.Math.sqrt
import kotlin.math.abs

fun Float.distance(to: Float): Float =
    if (this < to) abs(to - this) else abs(this - to)

fun Vector2.copy(): Vector2 = Vector2(x, y)
fun Vector3.copy(): Vector3 = Vector3(x, y, z)
fun Vector4.copy(): Vector4 = Vector4(x, y, z, w)

fun Vector3.cross(with: Vector3) {
    val nx = y * with.z - z * with.y
    val ny = z * with.x - x * with.z
    val nz = x * with.y - y * with.x
    x = nx; y = ny; z = nz
}

fun Vector3.crossed(with: Vector3): Vector3 {
    var retval = copy()
    retval.cross(with)
    return retval
}

fun Vector3.angle(to: Vector3): Float {
    val d = kotlin.math.sqrt(lengthSquared() * to.lengthSquared())
    if (d == 0F) return kotlin.math.PI.toFloat() / 2F
    val theta = dotProduct(to) / d
    return kotlin.math.acos(clamp(theta, -1F, 1F))
}

fun Vector2.zeroed(x: Boolean = false, y: Boolean = false): Vector2 =
    copy().multiplied(Vector2(if (x) 0F else 1F, if (y) 0F else 1F))
fun Vector3.zeroed(x: Boolean = false, y: Boolean = false, z: Boolean = false): Vector3 =
    copy().multiplied(Vector3(if (x) 0F else 1F, if (y) 0F else 1F, if (z) 0F else 1F))
fun Vector4.zeroed(x: Boolean = false, y: Boolean = false, z: Boolean = false, w: Boolean = false): Vector4 =
    copy().multiplied(Vector4(if (x) 0F else 1F, if (y) 0F else 1F, if (z) 0F else 1F, if (w) 0F else 1F))
