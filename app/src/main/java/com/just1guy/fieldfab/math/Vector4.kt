import androidx.core.math.MathUtils
class Vector4 (
   var x: Float,
   var y: Float,
   var z: Float,
   var w: Float
) {
   constructor(other: Vector4): this(
   other.x,
   other.y,
   other.z,
   other.w
   ) {}
   fun addScalar(s: Float) {
       x += s
       y += s
       z += s
       w += s
   }
   fun addedScalar(s: Float): Vector4 {
       var retval = Vector4(this)
       retval.x += s
       retval.y += s
       retval.z += s
       retval.w += s
       return retval
   }
   fun add(v: Vector4) {
       x += v.x
       y += v.y
       z += v.z
       w += v.w
   }
   fun added(v: Vector4): Vector4 {
       var retval = Vector4(this)
       retval.x += v.x
       retval.y += v.y
       retval.z += v.z
       retval.w += v.w
       return retval
   }
   fun subScalar(s: Float) {
       x -= s
       y -= s
       z -= s
       w -= s
   }
   fun subbedScalar(s: Float): Vector4 {
       var retval = Vector4(this)
       retval.x -= s
       retval.y -= s
       retval.z -= s
       retval.w -= s
       return retval
   }
   fun subtract(v: Vector4) {
       x -= v.x
       y -= v.y
       z -= v.z
       w -= v.w
   }
   fun subtracted(v: Vector4): Vector4 {
       var retval = Vector4(this)
       retval.x -= v.x
       retval.y -= v.y
       retval.z -= v.z
       retval.w -= v.w
       return retval
   }
   fun multiplyScalar(s: Float) {
       x *= s
       y *= s
       z *= s
       w *= s
   }
   fun multipliedScalar(s: Float): Vector4 {
       var retval = Vector4(this)
       retval.x *= s
       retval.y *= s
       retval.z *= s
       retval.w *= s
       return retval
   }
   fun multiply(v: Vector4) {
       x *= v.x
       y *= v.y
       z *= v.z
       w *= v.w
   }
   fun multiplied(v: Vector4): Vector4 {
       var retval = Vector4(this)
       retval.x *= v.x
       retval.y *= v.y
       retval.z *= v.z
       retval.w *= v.w
       return retval
   }
   fun divideScalar(s: Float) {
       x /= s
       y /= s
       z /= s
       w /= s
   }
   fun dividedScalar(s: Float): Vector4 {
       var retval = Vector4(this)
       retval.x /= s
       retval.y /= s
       retval.z /= s
       retval.w /= s
       return retval
   }
   fun divide(v: Vector4) {
       x /= v.x
       y /= v.y
       z /= v.z
       w /= v.w
   }
   fun divided(v: Vector4): Vector4 {
       var retval = Vector4(this)
       retval.x /= v.x
       retval.y /= v.y
       retval.z /= v.z
       retval.w /= v.w
       return retval
   }
   fun zero() {
       x = 0.0f
       y = 0.0f
       z = 0.0f
       w = 0.0f
   }
   fun clamp(min: Vector4, max: Vector4) {
       x = MathUtils.clamp(x, min.x, max.x)
       y = MathUtils.clamp(y, min.y, max.y)
       z = MathUtils.clamp(z, min.z, max.z)
       w = MathUtils.clamp(w, min.w, max.w)
   }
   fun clamped(min: Vector4, max: Vector4): Vector4 {
       var retval = Vector4(this)
       retval.x = MathUtils.clamp(x, min.x, max.x)
       retval.y = MathUtils.clamp(y, min.y, max.y)
       retval.z = MathUtils.clamp(z, min.z, max.z)
       retval.w = MathUtils.clamp(w, min.w, max.w)
       return retval
   }
   fun floor() {
       x = Math.floor(x.toDouble()).toFloat()
       y = Math.floor(y.toDouble()).toFloat()
       z = Math.floor(z.toDouble()).toFloat()
       w = Math.floor(w.toDouble()).toFloat()
   }
   fun floored(): Vector4 {
       var retval = Vector4(this)
       retval.x = Math.floor(x.toDouble()).toFloat()
       retval.y = Math.floor(y.toDouble()).toFloat()
       retval.z = Math.floor(z.toDouble()).toFloat()
       retval.w = Math.floor(w.toDouble()).toFloat()
       return retval
   }
   fun ceil() {
       x = Math.ceil(x.toDouble()).toFloat()
       y = Math.ceil(y.toDouble()).toFloat()
       z = Math.ceil(z.toDouble()).toFloat()
       w = Math.ceil(w.toDouble()).toFloat()
   }
   fun ceiled(): Vector4 {
       var retval = Vector4(this)
       retval.x = Math.ceil(x.toDouble()).toFloat()
       retval.y = Math.ceil(y.toDouble()).toFloat()
       retval.z = Math.ceil(z.toDouble()).toFloat()
       retval.w = Math.ceil(w.toDouble()).toFloat()
       return retval
   }
   fun round() {
       x = Math.round(x).toFloat()
       y = Math.round(y).toFloat()
       z = Math.round(z).toFloat()
       w = Math.round(w).toFloat()
   }
   fun rounded(): Vector4 {
       var retval = Vector4(this)
       retval.x = Math.round(x).toFloat()
       retval.y = Math.round(y).toFloat()
       retval.z = Math.round(z).toFloat()
       retval.w = Math.round(w).toFloat()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.0f) Math.ceil(x.toDouble()).toFloat() else Math.floor(x.toDouble()).toFloat()
       y = if (y < 0.0f) Math.ceil(y.toDouble()).toFloat() else Math.floor(y.toDouble()).toFloat()
       z = if (z < 0.0f) Math.ceil(z.toDouble()).toFloat() else Math.floor(z.toDouble()).toFloat()
       w = if (w < 0.0f) Math.ceil(w.toDouble()).toFloat() else Math.floor(w.toDouble()).toFloat()
   }
   fun roundedToZero(): Vector4 {
       var retval = Vector4(this)
       retval.x = if (x < 0.0f) Math.ceil(x.toDouble()).toFloat() else Math.floor(x.toDouble()).toFloat()
       retval.y = if (y < 0.0f) Math.ceil(y.toDouble()).toFloat() else Math.floor(y.toDouble()).toFloat()
       retval.z = if (z < 0.0f) Math.ceil(z.toDouble()).toFloat() else Math.floor(z.toDouble()).toFloat()
       retval.w = if (w < 0.0f) Math.ceil(w.toDouble()).toFloat() else Math.floor(w.toDouble()).toFloat()
       return retval
   }
   fun negate() {
       x = - x
       y = - y
       z = - z
       w = - w
   }
   fun negated(): Vector4 {
       var retval = Vector4(this)
       retval.x = - x
       retval.y = - y
       retval.z = - z
       retval.w = - w
       return retval
   }
   fun length(): Float {
       return Math.sqrt(
           (x * x).toDouble() +
           (y * y).toDouble() +
           (z * z).toDouble() +
           (w * w).toDouble() 
       ).toFloat()
   }
   fun manhattanLength(): Float {
       return (
           Math.abs(x) +
           Math.abs(y) +
           Math.abs(z) +
           Math.abs(w) 
       )
   }
   fun normalize(): Vector4 {
       val len = length()
       return dividedScalar(if (len <= 0.0f) 1.0f else len)
   }
   fun setLength(to: Float) {
        normalize()
        multiplyScalar(length())
   }
   // TODO continue from lerp
}
