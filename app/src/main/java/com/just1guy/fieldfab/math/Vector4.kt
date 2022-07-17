import androidx.core.math.MathUtils
import com.just1guy.fieldfab.math.distance
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.math.sqrt

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
   fun dotProduct(v: Vector4): Float {
       var retval = 0F
       retval += x * v.x
       retval += y * v.y
       retval += z * v.z
       retval += w * v.w
       return retval
   }
   fun crossProduct(v: Vector4): Float {
       var retval = x * v.x
       retval -= y * v.y
       retval -= z * v.z
       retval -= w * v.w
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
       x = kotlin.math.floor(x.toDouble()).toFloat()
       y = kotlin.math.floor(y.toDouble()).toFloat()
       z = kotlin.math.floor(z.toDouble()).toFloat()
       w = kotlin.math.floor(w.toDouble()).toFloat()
   }
   fun floored(): Vector4 {
       var retval = Vector4(this)
       retval.x = kotlin.math.floor(x.toDouble()).toFloat()
       retval.y = kotlin.math.floor(y.toDouble()).toFloat()
       retval.z = kotlin.math.floor(z.toDouble()).toFloat()
       retval.w = kotlin.math.floor(w.toDouble()).toFloat()
       return retval
   }
   fun ceil() {
       x = kotlin.math.ceil(x.toDouble()).toFloat()
       y = kotlin.math.ceil(y.toDouble()).toFloat()
       z = kotlin.math.ceil(z.toDouble()).toFloat()
       w = kotlin.math.ceil(w.toDouble()).toFloat()
   }
   fun ceiled(): Vector4 {
       var retval = Vector4(this)
       retval.x = kotlin.math.ceil(x.toDouble()).toFloat()
       retval.y = kotlin.math.ceil(y.toDouble()).toFloat()
       retval.z = kotlin.math.ceil(z.toDouble()).toFloat()
       retval.w = kotlin.math.ceil(w.toDouble()).toFloat()
       return retval
   }
   fun round() {
       x = x.roundToLong().toFloat()
       y = y.roundToLong().toFloat()
       z = z.roundToLong().toFloat()
       w = w.roundToLong().toFloat()
   }
   fun rounded(): Vector4 {
       var retval = Vector4(this)
       retval.x = x.roundToLong().toFloat()
       retval.y = y.roundToLong().toFloat()
       retval.z = z.roundToLong().toFloat()
       retval.w = w.roundToLong().toFloat()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.0f) kotlin.math.ceil(x.toDouble())
           .toFloat() else kotlin.math.floor(x.toDouble()).toFloat()
       y = if (y < 0.0f) kotlin.math.ceil(y.toDouble())
           .toFloat() else kotlin.math.floor(y.toDouble()).toFloat()
       z = if (z < 0.0f) kotlin.math.ceil(z.toDouble())
           .toFloat() else kotlin.math.floor(z.toDouble()).toFloat()
       w = if (w < 0.0f) kotlin.math.ceil(w.toDouble())
           .toFloat() else kotlin.math.floor(w.toDouble()).toFloat()
   }
   fun roundedToZero(): Vector4 {
       var retval = Vector4(this)
       retval.x = if (x < 0.0f) kotlin.math.ceil(x.toDouble())
           .toFloat() else kotlin.math.floor(x.toDouble()).toFloat()
       retval.y = if (y < 0.0f) kotlin.math.ceil(y.toDouble())
           .toFloat() else kotlin.math.floor(y.toDouble()).toFloat()
       retval.z = if (z < 0.0f) kotlin.math.ceil(z.toDouble())
           .toFloat() else kotlin.math.floor(z.toDouble()).toFloat()
       retval.w = if (w < 0.0f) kotlin.math.ceil(w.toDouble())
           .toFloat() else kotlin.math.floor(w.toDouble()).toFloat()
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
   fun lengthSquared(): Float {
       return (
           (x * x).toDouble() +
           (y * y).toDouble() +
           (z * z).toDouble() +
           (w * w).toDouble() 
       ).toFloat()
   }
   fun length(): Float {
       return sqrt(
           (x * x).toDouble() +
           (y * y).toDouble() +
           (z * z).toDouble() +
           (w * w).toDouble() 
       ).toFloat()
   }
   fun manhattanLength(): Float {
       return (
           abs(x) +
           abs(y) +
           abs(z) +
           abs(w)
       )
   }
   fun distanceSquared(to: Vector4): Float {
       return (
           (x.distance(to.x) * x.distance(to.x)) +
           (y.distance(to.y) * y.distance(to.y)) +
           (z.distance(to.z) * z.distance(to.z)) +
           (w.distance(to.w) * w.distance(to.w)) 
       )
   }
   fun distance(to: Vector4): Float = kotlin.math.sqrt(distanceSquared(to))
   fun normalize(): Vector4 {
       val len = length()
       return dividedScalar(if (len <= 0.0f) 1.0f else len)
   }
   fun setLength(to: Float) {
        normalize()
        multiplyScalar(length())
   }
   fun lerp(v: Vector4, alpha: Float) {
           x += (v.x - x) * alpha
           y += (v.y - y) * alpha
           z += (v.z - z) * alpha
           w += (v.w - w) * alpha
   }
   fun lerped(v: Vector4, alpha: Float): Vector4 {
       var retval = Vector4(this)
       retval.lerp(v, alpha)
       return retval
   }
}
