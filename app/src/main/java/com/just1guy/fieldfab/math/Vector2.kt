import androidx.core.math.MathUtils
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
       x = Math.floor(x.toDouble()).toFloat()
       y = Math.floor(y.toDouble()).toFloat()
   }
   fun floored(): Vector2 {
       var retval = Vector2(this)
       retval.x = Math.floor(x.toDouble()).toFloat()
       retval.y = Math.floor(y.toDouble()).toFloat()
       return retval
   }
   fun ceil() {
       x = Math.ceil(x.toDouble()).toFloat()
       y = Math.ceil(y.toDouble()).toFloat()
   }
   fun ceiled(): Vector2 {
       var retval = Vector2(this)
       retval.x = Math.ceil(x.toDouble()).toFloat()
       retval.y = Math.ceil(y.toDouble()).toFloat()
       return retval
   }
   fun round() {
       x = Math.round(x).toFloat()
       y = Math.round(y).toFloat()
   }
   fun rounded(): Vector2 {
       var retval = Vector2(this)
       retval.x = Math.round(x).toFloat()
       retval.y = Math.round(y).toFloat()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.0f) Math.ceil(x.toDouble()).toFloat() else Math.floor(x.toDouble()).toFloat()
       y = if (y < 0.0f) Math.ceil(y.toDouble()).toFloat() else Math.floor(y.toDouble()).toFloat()
   }
   fun roundedToZero(): Vector2 {
       var retval = Vector2(this)
       retval.x = if (x < 0.0f) Math.ceil(x.toDouble()).toFloat() else Math.floor(x.toDouble()).toFloat()
       retval.y = if (y < 0.0f) Math.ceil(y.toDouble()).toFloat() else Math.floor(y.toDouble()).toFloat()
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
   fun length(): Float {
       return Math.sqrt(
           (x * x).toDouble() +
           (y * y).toDouble() 
       ).toFloat()
   }
   fun manhattanLength(): Float {
       return (
           Math.abs(x) +
           Math.abs(y) 
       )
   }
   fun normalize(): Vector2 {
       val len = length()
       return dividedScalar(if (len <= 0.0f) 1.0f else len)
   }
   fun setLength(to: Float) {
        normalize()
        multiplyScalar(length())
   }
   // TODO continue from lerp
}
