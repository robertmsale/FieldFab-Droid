import androidx.core.math.MathUtils
import com.just1guy.fieldfab.math.distance
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.math.sqrt

class Vector2 (
   var x: Float,
   var y: Float
) {
   constructor(other: Vector2): this(
   other.x,
   other.y
   ) {}
   fun addScalar(s: Float) {
       x += s
       y += s
   }
   fun addedScalar(s: Float): Vector2 {
       var retval = Vector2(this)
       retval.x += s
       retval.y += s
       return retval
   }
   fun add(v: Vector2) {
       x += v.x
       y += v.y
   }
   fun added(v: Vector2): Vector2 {
       var retval = Vector2(this)
       retval.x += v.x
       retval.y += v.y
       return retval
   }
   fun subScalar(s: Float) {
       x -= s
       y -= s
   }
   fun subbedScalar(s: Float): Vector2 {
       var retval = Vector2(this)
       retval.x -= s
       retval.y -= s
       return retval
   }
   fun subtract(v: Vector2) {
       x -= v.x
       y -= v.y
   }
   fun subtracted(v: Vector2): Vector2 {
       var retval = Vector2(this)
       retval.x -= v.x
       retval.y -= v.y
       return retval
   }
   fun multiplyScalar(s: Float) {
       x *= s
       y *= s
   }
   fun multipliedScalar(s: Float): Vector2 {
       var retval = Vector2(this)
       retval.x *= s
       retval.y *= s
       return retval
   }
   fun multiply(v: Vector2) {
       x *= v.x
       y *= v.y
   }
   fun multiplied(v: Vector2): Vector2 {
       var retval = Vector2(this)
       retval.x *= v.x
       retval.y *= v.y
       return retval
   }
   fun divideScalar(s: Float) {
       x /= s
       y /= s
   }
   fun dividedScalar(s: Float): Vector2 {
       var retval = Vector2(this)
       retval.x /= s
       retval.y /= s
       return retval
   }
   fun divide(v: Vector2) {
       x /= v.x
       y /= v.y
   }
   fun divided(v: Vector2): Vector2 {
       var retval = Vector2(this)
       retval.x /= v.x
       retval.y /= v.y
       return retval
   }
   fun dotProduct(v: Vector2): Float {
       var retval = 0F
       retval += x * v.x
       retval += y * v.y
       return retval
   }
   fun crossProduct(v: Vector2): Float {
       var retval = x * v.x
       retval -= y * v.y
       return retval
   }
   fun zero() {
       x = 0.0f
       y = 0.0f
   }
   fun clamp(min: Vector2, max: Vector2) {
       x = MathUtils.clamp(x, min.x, max.x)
       y = MathUtils.clamp(y, min.y, max.y)
   }
   fun clamped(min: Vector2, max: Vector2): Vector2 {
       var retval = Vector2(this)
       retval.x = MathUtils.clamp(x, min.x, max.x)
       retval.y = MathUtils.clamp(y, min.y, max.y)
       return retval
   }
   fun floor() {
       x = kotlin.math.floor(x.toDouble()).toFloat()
       y = kotlin.math.floor(y.toDouble()).toFloat()
   }
   fun floored(): Vector2 {
       var retval = Vector2(this)
       retval.x = kotlin.math.floor(x.toDouble()).toFloat()
       retval.y = kotlin.math.floor(y.toDouble()).toFloat()
       return retval
   }
   fun ceil() {
       x = kotlin.math.ceil(x.toDouble()).toFloat()
       y = kotlin.math.ceil(y.toDouble()).toFloat()
   }
   fun ceiled(): Vector2 {
       var retval = Vector2(this)
       retval.x = kotlin.math.ceil(x.toDouble()).toFloat()
       retval.y = kotlin.math.ceil(y.toDouble()).toFloat()
       return retval
   }
   fun round() {
       x = x.roundToLong().toFloat()
       y = y.roundToLong().toFloat()
   }
   fun rounded(): Vector2 {
       var retval = Vector2(this)
       retval.x = x.roundToLong().toFloat()
       retval.y = y.roundToLong().toFloat()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.0f) kotlin.math.ceil(x.toDouble())
           .toFloat() else kotlin.math.floor(x.toDouble()).toFloat()
       y = if (y < 0.0f) kotlin.math.ceil(y.toDouble())
           .toFloat() else kotlin.math.floor(y.toDouble()).toFloat()
   }
   fun roundedToZero(): Vector2 {
       var retval = Vector2(this)
       retval.x = if (x < 0.0f) kotlin.math.ceil(x.toDouble())
           .toFloat() else kotlin.math.floor(x.toDouble()).toFloat()
       retval.y = if (y < 0.0f) kotlin.math.ceil(y.toDouble())
           .toFloat() else kotlin.math.floor(y.toDouble()).toFloat()
       return retval
   }
   fun negate() {
       x = - x
       y = - y
   }
   fun negated(): Vector2 {
       var retval = Vector2(this)
       retval.x = - x
       retval.y = - y
       return retval
   }
   fun lengthSquared(): Float {
       return (
           (x * x).toDouble() +
           (y * y).toDouble() 
       ).toFloat()
   }
   fun length(): Float {
       return sqrt(
           (x * x).toDouble() +
           (y * y).toDouble() 
       ).toFloat()
   }
   fun manhattanLength(): Float {
       return (
           abs(x) +
           abs(y)
       )
   }
   fun distanceSquared(to: Vector2): Float {
       return (
           (x.distance(to.x) * x.distance(to.x)) +
           (y.distance(to.y) * y.distance(to.y)) 
       )
   }
   fun distance(to: Vector2): Float = kotlin.math.sqrt(distanceSquared(to))
   fun normalize(): Vector2 {
       val len = length()
       return dividedScalar(if (len <= 0.0f) 1.0f else len)
   }
   fun setLength(to: Float) {
        normalize()
        multiplyScalar(length())
   }
   fun lerp(v: Vector2, alpha: Float) {
           x += (v.x - x) * alpha
           y += (v.y - y) * alpha
   }
   fun lerped(v: Vector2, alpha: Float): Vector2 {
       var retval = Vector2(this)
       retval.lerp(v, alpha)
       return retval
   }
}
