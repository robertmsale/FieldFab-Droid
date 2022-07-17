import androidx.core.math.MathUtils
import com.just1guy.fieldfab.math.distance
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.math.sqrt

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
   fun dotProduct(v: Vector3): Float {
       var retval = 0F
       retval += x * v.x
       retval += y * v.y
       retval += z * v.z
       return retval
   }
   fun crossProduct(v: Vector3): Float {
       var retval = x * v.x
       retval -= y * v.y
       retval -= z * v.z
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
       x = kotlin.math.floor(x.toDouble()).toFloat()
       y = kotlin.math.floor(y.toDouble()).toFloat()
       z = kotlin.math.floor(z.toDouble()).toFloat()
   }
   fun floored(): Vector3 {
       var retval = Vector3(this)
       retval.x = kotlin.math.floor(x.toDouble()).toFloat()
       retval.y = kotlin.math.floor(y.toDouble()).toFloat()
       retval.z = kotlin.math.floor(z.toDouble()).toFloat()
       return retval
   }
   fun ceil() {
       x = kotlin.math.ceil(x.toDouble()).toFloat()
       y = kotlin.math.ceil(y.toDouble()).toFloat()
       z = kotlin.math.ceil(z.toDouble()).toFloat()
   }
   fun ceiled(): Vector3 {
       var retval = Vector3(this)
       retval.x = kotlin.math.ceil(x.toDouble()).toFloat()
       retval.y = kotlin.math.ceil(y.toDouble()).toFloat()
       retval.z = kotlin.math.ceil(z.toDouble()).toFloat()
       return retval
   }
   fun round() {
       x = x.roundToLong().toFloat()
       y = y.roundToLong().toFloat()
       z = z.roundToLong().toFloat()
   }
   fun rounded(): Vector3 {
       var retval = Vector3(this)
       retval.x = x.roundToLong().toFloat()
       retval.y = y.roundToLong().toFloat()
       retval.z = z.roundToLong().toFloat()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.0f) kotlin.math.ceil(x.toDouble())
           .toFloat() else kotlin.math.floor(x.toDouble()).toFloat()
       y = if (y < 0.0f) kotlin.math.ceil(y.toDouble())
           .toFloat() else kotlin.math.floor(y.toDouble()).toFloat()
       z = if (z < 0.0f) kotlin.math.ceil(z.toDouble())
           .toFloat() else kotlin.math.floor(z.toDouble()).toFloat()
   }
   fun roundedToZero(): Vector3 {
       var retval = Vector3(this)
       retval.x = if (x < 0.0f) kotlin.math.ceil(x.toDouble())
           .toFloat() else kotlin.math.floor(x.toDouble()).toFloat()
       retval.y = if (y < 0.0f) kotlin.math.ceil(y.toDouble())
           .toFloat() else kotlin.math.floor(y.toDouble()).toFloat()
       retval.z = if (z < 0.0f) kotlin.math.ceil(z.toDouble())
           .toFloat() else kotlin.math.floor(z.toDouble()).toFloat()
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
   fun lengthSquared(): Float {
       return (
           (x * x).toDouble() +
           (y * y).toDouble() +
           (z * z).toDouble() 
       ).toFloat()
   }
   fun length(): Float {
       return sqrt(
           (x * x).toDouble() +
           (y * y).toDouble() +
           (z * z).toDouble() 
       ).toFloat()
   }
   fun manhattanLength(): Float {
       return (
           abs(x) +
           abs(y) +
           abs(z)
       )
   }
   fun distanceSquared(to: Vector3): Float {
       return (
           (x.distance(to.x) * x.distance(to.x)) +
           (y.distance(to.y) * y.distance(to.y)) +
           (z.distance(to.z) * z.distance(to.z)) 
       )
   }
   fun distance(to: Vector3): Float = kotlin.math.sqrt(distanceSquared(to))
   fun normalize(): Vector3 {
       val len = length()
       return dividedScalar(if (len <= 0.0f) 1.0f else len)
   }
   fun setLength(to: Float) {
        normalize()
        multiplyScalar(length())
   }
   fun lerp(v: Vector3, alpha: Float) {
           x += (v.x - x) * alpha
           y += (v.y - y) * alpha
           z += (v.z - z) * alpha
   }
   fun lerped(v: Vector3, alpha: Float): Vector3 {
       var retval = Vector3(this)
       retval.lerp(v, alpha)
       return retval
   }
}
