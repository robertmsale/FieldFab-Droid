import androidx.core.math.MathUtils
class Vector3 (
   var x: Float,
   var y: Float,
   var z: Float
) {
   constructor(other: Vector3): this(
   other.x,
   other.y,
   other.z
   ) {}
   fun addScalar(s: Float) {
       x += s
       y += s
       z += s
   }
   fun addedScalar(s: Float): Vector3 {
       var retval = Vector3(this)
       retval.x += s
       retval.y += s
       retval.z += s
       return retval
   }
   fun add(v: Vector3) {
       x += v.x
       y += v.y
       z += v.z
   }
   fun added(v: Vector3): Vector3 {
       var retval = Vector3(this)
       retval.x += v.x
       retval.y += v.y
       retval.z += v.z
       return retval
   }
   fun subScalar(s: Float) {
       x -= s
       y -= s
       z -= s
   }
   fun subbedScalar(s: Float): Vector3 {
       var retval = Vector3(this)
       retval.x -= s
       retval.y -= s
       retval.z -= s
       return retval
   }
   fun subtract(v: Vector3) {
       x -= v.x
       y -= v.y
       z -= v.z
   }
   fun subtracted(v: Vector3): Vector3 {
       var retval = Vector3(this)
       retval.x -= v.x
       retval.y -= v.y
       retval.z -= v.z
       return retval
   }
   fun multiplyScalar(s: Float) {
       x *= s
       y *= s
       z *= s
   }
   fun multipliedScalar(s: Float): Vector3 {
       var retval = Vector3(this)
       retval.x *= s
       retval.y *= s
       retval.z *= s
       return retval
   }
   fun multiply(v: Vector3) {
       x *= v.x
       y *= v.y
       z *= v.z
   }
   fun multiplied(v: Vector3): Vector3 {
       var retval = Vector3(this)
       retval.x *= v.x
       retval.y *= v.y
       retval.z *= v.z
       return retval
   }
   fun divideScalar(s: Float) {
       x /= s
       y /= s
       z /= s
   }
   fun dividedScalar(s: Float): Vector3 {
       var retval = Vector3(this)
       retval.x /= s
       retval.y /= s
       retval.z /= s
       return retval
   }
   fun divide(v: Vector3) {
       x /= v.x
       y /= v.y
       z /= v.z
   }
   fun divided(v: Vector3): Vector3 {
       var retval = Vector3(this)
       retval.x /= v.x
       retval.y /= v.y
       retval.z /= v.z
       return retval
   }
   fun zero() {
       x = 0.0f
       y = 0.0f
       z = 0.0f
   }
   fun clamp(min: Vector3, max: Vector3) {
       x = MathUtils.clamp(x, min.x, max.x)
       y = MathUtils.clamp(y, min.y, max.y)
       z = MathUtils.clamp(z, min.z, max.z)
   }
   fun clamped(min: Vector3, max: Vector3): Vector3 {
       var retval = Vector3(this)
       retval.x = MathUtils.clamp(x, min.x, max.x)
       retval.y = MathUtils.clamp(y, min.y, max.y)
       retval.z = MathUtils.clamp(z, min.z, max.z)
       return retval
   }
   fun floor() {
       x = Math.floor(x.toDouble()).toFloat()
       y = Math.floor(y.toDouble()).toFloat()
       z = Math.floor(z.toDouble()).toFloat()
   }
   fun floored(): Vector3 {
       var retval = Vector3(this)
       retval.x = Math.floor(x.toDouble()).toFloat()
       retval.y = Math.floor(y.toDouble()).toFloat()
       retval.z = Math.floor(z.toDouble()).toFloat()
       return retval
   }
   fun ceil() {
       x = Math.ceil(x.toDouble()).toFloat()
       y = Math.ceil(y.toDouble()).toFloat()
       z = Math.ceil(z.toDouble()).toFloat()
   }
   fun ceiled(): Vector3 {
       var retval = Vector3(this)
       retval.x = Math.ceil(x.toDouble()).toFloat()
       retval.y = Math.ceil(y.toDouble()).toFloat()
       retval.z = Math.ceil(z.toDouble()).toFloat()
       return retval
   }
   fun round() {
       x = Math.round(x).toFloat()
       y = Math.round(y).toFloat()
       z = Math.round(z).toFloat()
   }
   fun rounded(): Vector3 {
       var retval = Vector3(this)
       retval.x = Math.round(x).toFloat()
       retval.y = Math.round(y).toFloat()
       retval.z = Math.round(z).toFloat()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.0f) Math.ceil(x.toDouble()).toFloat() else Math.floor(x.toDouble()).toFloat()
       y = if (y < 0.0f) Math.ceil(y.toDouble()).toFloat() else Math.floor(y.toDouble()).toFloat()
       z = if (z < 0.0f) Math.ceil(z.toDouble()).toFloat() else Math.floor(z.toDouble()).toFloat()
   }
   fun roundedToZero(): Vector3 {
       var retval = Vector3(this)
       retval.x = if (x < 0.0f) Math.ceil(x.toDouble()).toFloat() else Math.floor(x.toDouble()).toFloat()
       retval.y = if (y < 0.0f) Math.ceil(y.toDouble()).toFloat() else Math.floor(y.toDouble()).toFloat()
       retval.z = if (z < 0.0f) Math.ceil(z.toDouble()).toFloat() else Math.floor(z.toDouble()).toFloat()
       return retval
   }
   fun negate() {
       x = - x
       y = - y
       z = - z
   }
   fun negated(): Vector3 {
       var retval = Vector3(this)
       retval.x = - x
       retval.y = - y
       retval.z = - z
       return retval
   }
   fun length(): Float {
       return Math.sqrt(
           (x * x).toDouble() +
           (y * y).toDouble() +
           (z * z).toDouble() 
       ).toFloat()
   }
   fun manhattanLength(): Float {
       return (
           Math.abs(x) +
           Math.abs(y) +
           Math.abs(z) 
       )
   }
   fun normalize(): Vector3 {
       val len = length()
       return dividedScalar(if (len <= 0.0f) 1.0f else len)
   }
   fun setLength(to: Float) {
        normalize()
        multiplyScalar(length())
   }
   // TODO continue from lerp
}
