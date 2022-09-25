import androidx.core.math.MathUtils
import com.just1guy.fieldfab.math.distance
@kotlinx.serialization.Serializable
data class Vector2Float (
   var x: Float = 0.toFloat(),
   var y: Float = 0.toFloat()
) {
   constructor(other: Vector2Float): this(
   other.x,
   other.y
   ) {}

   fun copy(): Vector2Float = Vector2Float(
       x,
       y,
   )

   fun addScalar(s: Float) {
       x += s
       y += s
   }
   fun addedScalar(s: Float): Vector2Float {
       var retval = Vector2Float(this)
       retval.x += s
       retval.y += s
       return retval
   }
   fun add(v: Vector2Float) {
       x += v.x
       y += v.y
   }
   fun added(v: Vector2Float): Vector2Float {
       var retval = Vector2Float(this)
       retval.x += v.x
       retval.y += v.y
       return retval
   }
   fun subScalar(s: Float) {
       x -= s
       y -= s
   }
   fun subbedScalar(s: Float): Vector2Float {
       var retval = Vector2Float(this)
       retval.x -= s
       retval.y -= s
       return retval
   }
   fun subtract(v: Vector2Float) {
       x -= v.x
       y -= v.y
   }
   fun subtracted(v: Vector2Float): Vector2Float {
       var retval = Vector2Float(this)
       retval.x -= v.x
       retval.y -= v.y
       return retval
   }
   fun multiplyScalar(s: Float) {
       x *= s
       y *= s
   }
   fun multipliedScalar(s: Float): Vector2Float {
       var retval = Vector2Float(this)
       retval.x *= s
       retval.y *= s
       return retval
   }
   fun multiply(v: Vector2Float) {
       x *= v.x
       y *= v.y
   }
   fun multiplied(v: Vector2Float): Vector2Float {
       var retval = Vector2Float(this)
       retval.x *= v.x
       retval.y *= v.y
       return retval
   }
   fun divideScalar(s: Float) {
       x /= s
       y /= s
   }
   fun dividedScalar(s: Float): Vector2Float {
       var retval = Vector2Float(this)
       retval.x /= s
       retval.y /= s
       return retval
   }
   fun divide(v: Vector2Float) {
       x /= v.x
       y /= v.y
   }
   fun divided(v: Vector2Float): Vector2Float {
       var retval = Vector2Float(this)
       retval.x /= v.x
       retval.y /= v.y
       return retval
   }
   fun dotProduct(v: Vector2Float): Float {
       var retval = 0.toFloat()
       retval += x * v.x
       retval += y * v.y
       return retval
   }
   fun crossProduct(v: Vector2Float): Float {
       var retval = x * v.x
       retval -= y * v.y
       return retval
   }
   fun zero() {
       x = 0.toFloat()
       y = 0.toFloat()
   }
   fun zeroed(
       x: Boolean = false,
       y: Boolean = false,
   ): Vector2Float = copy().multiplied(Vector2Float(
       if (x) 0.toFloat() else 1.toFloat(),
       if (y) 0.toFloat() else 1.toFloat(),
   ))

   fun clamp(min: Vector2Float, max: Vector2Float) {
       x = MathUtils.clamp(x, min.x, max.x)
       y = MathUtils.clamp(y, min.y, max.y)
   }
   fun clamped(min: Vector2Float, max: Vector2Float): Vector2Float {
       var retval = Vector2Float(this)
       retval.x = MathUtils.clamp(x, min.x, max.x)
       retval.y = MathUtils.clamp(y, min.y, max.y)
       return retval
   }
   fun floor() {
       x = Math.floor(x.toDouble()).toFloat()
       y = Math.floor(y.toDouble()).toFloat()
   }
   fun floored(): Vector2Float {
       var retval = Vector2Float(this)
       retval.x = Math.floor(x.toDouble()).toFloat()
       retval.y = Math.floor(y.toDouble()).toFloat()
       return retval
   }
   fun ceil() {
       x = Math.ceil(x.toDouble()).toFloat()
       y = Math.ceil(y.toDouble()).toFloat()
   }
   fun ceiled(): Vector2Float {
       var retval = Vector2Float(this)
       retval.x = Math.ceil(x.toDouble()).toFloat()
       retval.y = Math.ceil(y.toDouble()).toFloat()
       return retval
   }
   fun round() {
       x = Math.round(x.toDouble()).toFloat()
       y = Math.round(y.toDouble()).toFloat()
   }
   fun rounded(): Vector2Float {
       var retval = Vector2Float(this)
       retval.x = Math.round(x.toDouble()).toFloat()
       retval.y = Math.round(y.toDouble()).toFloat()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.toFloat()) Math.ceil(x.toDouble()).toFloat() else Math.floor(x.toDouble()).toFloat()
       y = if (y < 0.toFloat()) Math.ceil(y.toDouble()).toFloat() else Math.floor(y.toDouble()).toFloat()
   }
   fun roundedToZero(): Vector2Float {
       var retval = Vector2Float(this)
       retval.x = if (x < 0.toFloat()) Math.ceil(x.toDouble()).toFloat() else Math.floor(x.toDouble()).toFloat()
       retval.y = if (y < 0.toFloat()) Math.ceil(y.toDouble()).toFloat() else Math.floor(y.toDouble()).toFloat()
       return retval
   }
   fun negate() {
       x = - x
       y = - y
   }
   fun negated(): Vector2Float {
       var retval = Vector2Float(this)
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
   fun distanceSquared(to: Vector2Float): Float {
       return (
           (x.distance(to.x) * x.distance(to.x)) +
           (y.distance(to.y) * y.distance(to.y)) 
       )
   }
   fun distance(to: Vector2Float): Float = kotlin.math.sqrt(distanceSquared(to).toDouble()).toFloat()
   fun normalize(): Vector2Float {
       val len = length()
       return dividedScalar(if (len <= 0.toFloat()) 0.toFloat() else len)
   }
   fun setLength(to: Float) {
        normalize()
        multiplyScalar(length())
   }
   fun lerp(v: Vector2Float, alpha: Float) {
           x += (v.x - x) * alpha
           y += (v.y - y) * alpha
   }
   fun lerped(v: Vector2Float, alpha: Float): Vector2Float {
       var retval = Vector2Float(this)
       retval.lerp(v, alpha)
       return retval
   }
   operator fun plus(v: Vector2Float): Vector2Float {
      return this.added(v)
   }
   operator fun minus(v: Vector2Float): Vector2Float {
      return this.subtracted(v)
   }
   operator fun times(v: Vector2Float): Vector2Float {
      return this.multiplied(v)
   }
   operator fun div(v: Vector2Float): Vector2Float {
      return this.divided(v)
   }
}
@kotlinx.serialization.Serializable
data class Vector2Double (
   var x: Double = 0.toDouble(),
   var y: Double = 0.toDouble()
) {
   constructor(other: Vector2Double): this(
   other.x,
   other.y
   ) {}

   fun copy(): Vector2Double = Vector2Double(
       x,
       y,
   )

   fun addScalar(s: Double) {
       x += s
       y += s
   }
   fun addedScalar(s: Double): Vector2Double {
       var retval = Vector2Double(this)
       retval.x += s
       retval.y += s
       return retval
   }
   fun add(v: Vector2Double) {
       x += v.x
       y += v.y
   }
   fun added(v: Vector2Double): Vector2Double {
       var retval = Vector2Double(this)
       retval.x += v.x
       retval.y += v.y
       return retval
   }
   fun subScalar(s: Double) {
       x -= s
       y -= s
   }
   fun subbedScalar(s: Double): Vector2Double {
       var retval = Vector2Double(this)
       retval.x -= s
       retval.y -= s
       return retval
   }
   fun subtract(v: Vector2Double) {
       x -= v.x
       y -= v.y
   }
   fun subtracted(v: Vector2Double): Vector2Double {
       var retval = Vector2Double(this)
       retval.x -= v.x
       retval.y -= v.y
       return retval
   }
   fun multiplyScalar(s: Double) {
       x *= s
       y *= s
   }
   fun multipliedScalar(s: Double): Vector2Double {
       var retval = Vector2Double(this)
       retval.x *= s
       retval.y *= s
       return retval
   }
   fun multiply(v: Vector2Double) {
       x *= v.x
       y *= v.y
   }
   fun multiplied(v: Vector2Double): Vector2Double {
       var retval = Vector2Double(this)
       retval.x *= v.x
       retval.y *= v.y
       return retval
   }
   fun divideScalar(s: Double) {
       x /= s
       y /= s
   }
   fun dividedScalar(s: Double): Vector2Double {
       var retval = Vector2Double(this)
       retval.x /= s
       retval.y /= s
       return retval
   }
   fun divide(v: Vector2Double) {
       x /= v.x
       y /= v.y
   }
   fun divided(v: Vector2Double): Vector2Double {
       var retval = Vector2Double(this)
       retval.x /= v.x
       retval.y /= v.y
       return retval
   }
   fun dotProduct(v: Vector2Double): Double {
       var retval = 0.toDouble()
       retval += x * v.x
       retval += y * v.y
       return retval
   }
   fun crossProduct(v: Vector2Double): Double {
       var retval = x * v.x
       retval -= y * v.y
       return retval
   }
   fun zero() {
       x = 0.toDouble()
       y = 0.toDouble()
   }
   fun zeroed(
       x: Boolean = false,
       y: Boolean = false,
   ): Vector2Double = copy().multiplied(Vector2Double(
       if (x) 0.toDouble() else 1.toDouble(),
       if (y) 0.toDouble() else 1.toDouble(),
   ))

   fun clamp(min: Vector2Double, max: Vector2Double) {
       x = MathUtils.clamp(x, min.x, max.x)
       y = MathUtils.clamp(y, min.y, max.y)
   }
   fun clamped(min: Vector2Double, max: Vector2Double): Vector2Double {
       var retval = Vector2Double(this)
       retval.x = MathUtils.clamp(x, min.x, max.x)
       retval.y = MathUtils.clamp(y, min.y, max.y)
       return retval
   }
   fun floor() {
       x = Math.floor(x.toDouble()).toDouble()
       y = Math.floor(y.toDouble()).toDouble()
   }
   fun floored(): Vector2Double {
       var retval = Vector2Double(this)
       retval.x = Math.floor(x.toDouble()).toDouble()
       retval.y = Math.floor(y.toDouble()).toDouble()
       return retval
   }
   fun ceil() {
       x = Math.ceil(x.toDouble()).toDouble()
       y = Math.ceil(y.toDouble()).toDouble()
   }
   fun ceiled(): Vector2Double {
       var retval = Vector2Double(this)
       retval.x = Math.ceil(x.toDouble()).toDouble()
       retval.y = Math.ceil(y.toDouble()).toDouble()
       return retval
   }
   fun round() {
       x = Math.round(x.toDouble()).toDouble()
       y = Math.round(y.toDouble()).toDouble()
   }
   fun rounded(): Vector2Double {
       var retval = Vector2Double(this)
       retval.x = Math.round(x.toDouble()).toDouble()
       retval.y = Math.round(y.toDouble()).toDouble()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.toDouble()) Math.ceil(x.toDouble()).toDouble() else Math.floor(x.toDouble()).toDouble()
       y = if (y < 0.toDouble()) Math.ceil(y.toDouble()).toDouble() else Math.floor(y.toDouble()).toDouble()
   }
   fun roundedToZero(): Vector2Double {
       var retval = Vector2Double(this)
       retval.x = if (x < 0.toDouble()) Math.ceil(x.toDouble()).toDouble() else Math.floor(x.toDouble()).toDouble()
       retval.y = if (y < 0.toDouble()) Math.ceil(y.toDouble()).toDouble() else Math.floor(y.toDouble()).toDouble()
       return retval
   }
   fun negate() {
       x = - x
       y = - y
   }
   fun negated(): Vector2Double {
       var retval = Vector2Double(this)
       retval.x = - x
       retval.y = - y
       return retval
   }
   fun lengthSquared(): Double {
       return (
           (x * x).toDouble() +
           (y * y).toDouble() 
       ).toDouble()
   }
   fun length(): Double {
       return Math.sqrt(
           (x * x).toDouble() +
           (y * y).toDouble() 
       ).toDouble()
   }
   fun manhattanLength(): Double {
       return (
           Math.abs(x) +
           Math.abs(y) 
       )
   }
   fun distanceSquared(to: Vector2Double): Double {
       return (
           (x.distance(to.x) * x.distance(to.x)) +
           (y.distance(to.y) * y.distance(to.y)) 
       )
   }
   fun distance(to: Vector2Double): Double = kotlin.math.sqrt(distanceSquared(to).toDouble()).toDouble()
   fun normalize(): Vector2Double {
       val len = length()
       return dividedScalar(if (len <= 0.toDouble()) 0.toDouble() else len)
   }
   fun setLength(to: Double) {
        normalize()
        multiplyScalar(length())
   }
   fun lerp(v: Vector2Double, alpha: Double) {
           x += (v.x - x) * alpha
           y += (v.y - y) * alpha
   }
   fun lerped(v: Vector2Double, alpha: Double): Vector2Double {
       var retval = Vector2Double(this)
       retval.lerp(v, alpha)
       return retval
   }
   operator fun plus(v: Vector2Double): Vector2Double {
      return this.added(v)
   }
   operator fun minus(v: Vector2Double): Vector2Double {
      return this.subtracted(v)
   }
   operator fun times(v: Vector2Double): Vector2Double {
      return this.multiplied(v)
   }
   operator fun div(v: Vector2Double): Vector2Double {
      return this.divided(v)
   }
}
@kotlinx.serialization.Serializable
data class Vector2Int (
   var x: Int = 0.toInt(),
   var y: Int = 0.toInt()
) {
   constructor(other: Vector2Int): this(
   other.x,
   other.y
   ) {}

   fun copy(): Vector2Int = Vector2Int(
       x,
       y,
   )

   fun addScalar(s: Int) {
       x += s
       y += s
   }
   fun addedScalar(s: Int): Vector2Int {
       var retval = Vector2Int(this)
       retval.x += s
       retval.y += s
       return retval
   }
   fun add(v: Vector2Int) {
       x += v.x
       y += v.y
   }
   fun added(v: Vector2Int): Vector2Int {
       var retval = Vector2Int(this)
       retval.x += v.x
       retval.y += v.y
       return retval
   }
   fun subScalar(s: Int) {
       x -= s
       y -= s
   }
   fun subbedScalar(s: Int): Vector2Int {
       var retval = Vector2Int(this)
       retval.x -= s
       retval.y -= s
       return retval
   }
   fun subtract(v: Vector2Int) {
       x -= v.x
       y -= v.y
   }
   fun subtracted(v: Vector2Int): Vector2Int {
       var retval = Vector2Int(this)
       retval.x -= v.x
       retval.y -= v.y
       return retval
   }
   fun multiplyScalar(s: Int) {
       x *= s
       y *= s
   }
   fun multipliedScalar(s: Int): Vector2Int {
       var retval = Vector2Int(this)
       retval.x *= s
       retval.y *= s
       return retval
   }
   fun multiply(v: Vector2Int) {
       x *= v.x
       y *= v.y
   }
   fun multiplied(v: Vector2Int): Vector2Int {
       var retval = Vector2Int(this)
       retval.x *= v.x
       retval.y *= v.y
       return retval
   }
   fun divideScalar(s: Int) {
       x /= s
       y /= s
   }
   fun dividedScalar(s: Int): Vector2Int {
       var retval = Vector2Int(this)
       retval.x /= s
       retval.y /= s
       return retval
   }
   fun divide(v: Vector2Int) {
       x /= v.x
       y /= v.y
   }
   fun divided(v: Vector2Int): Vector2Int {
       var retval = Vector2Int(this)
       retval.x /= v.x
       retval.y /= v.y
       return retval
   }
   fun dotProduct(v: Vector2Int): Int {
       var retval = 0.toInt()
       retval += x * v.x
       retval += y * v.y
       return retval
   }
   fun crossProduct(v: Vector2Int): Int {
       var retval = x * v.x
       retval -= y * v.y
       return retval
   }
   fun zero() {
       x = 0.toInt()
       y = 0.toInt()
   }
   fun zeroed(
       x: Boolean = false,
       y: Boolean = false,
   ): Vector2Int = copy().multiplied(Vector2Int(
       if (x) 0.toInt() else 1.toInt(),
       if (y) 0.toInt() else 1.toInt(),
   ))

   fun clamp(min: Vector2Int, max: Vector2Int) {
       x = MathUtils.clamp(x, min.x, max.x)
       y = MathUtils.clamp(y, min.y, max.y)
   }
   fun clamped(min: Vector2Int, max: Vector2Int): Vector2Int {
       var retval = Vector2Int(this)
       retval.x = MathUtils.clamp(x, min.x, max.x)
       retval.y = MathUtils.clamp(y, min.y, max.y)
       return retval
   }
   fun floor() {
       x = Math.floor(x.toDouble()).toInt()
       y = Math.floor(y.toDouble()).toInt()
   }
   fun floored(): Vector2Int {
       var retval = Vector2Int(this)
       retval.x = Math.floor(x.toDouble()).toInt()
       retval.y = Math.floor(y.toDouble()).toInt()
       return retval
   }
   fun ceil() {
       x = Math.ceil(x.toDouble()).toInt()
       y = Math.ceil(y.toDouble()).toInt()
   }
   fun ceiled(): Vector2Int {
       var retval = Vector2Int(this)
       retval.x = Math.ceil(x.toDouble()).toInt()
       retval.y = Math.ceil(y.toDouble()).toInt()
       return retval
   }
   fun round() {
       x = Math.round(x.toDouble()).toInt()
       y = Math.round(y.toDouble()).toInt()
   }
   fun rounded(): Vector2Int {
       var retval = Vector2Int(this)
       retval.x = Math.round(x.toDouble()).toInt()
       retval.y = Math.round(y.toDouble()).toInt()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.toInt()) Math.ceil(x.toDouble()).toInt() else Math.floor(x.toDouble()).toInt()
       y = if (y < 0.toInt()) Math.ceil(y.toDouble()).toInt() else Math.floor(y.toDouble()).toInt()
   }
   fun roundedToZero(): Vector2Int {
       var retval = Vector2Int(this)
       retval.x = if (x < 0.toInt()) Math.ceil(x.toDouble()).toInt() else Math.floor(x.toDouble()).toInt()
       retval.y = if (y < 0.toInt()) Math.ceil(y.toDouble()).toInt() else Math.floor(y.toDouble()).toInt()
       return retval
   }
   fun negate() {
       x = - x
       y = - y
   }
   fun negated(): Vector2Int {
       var retval = Vector2Int(this)
       retval.x = - x
       retval.y = - y
       return retval
   }
   fun lengthSquared(): Int {
       return (
           (x * x).toDouble() +
           (y * y).toDouble() 
       ).toInt()
   }
   fun length(): Int {
       return Math.sqrt(
           (x * x).toDouble() +
           (y * y).toDouble() 
       ).toInt()
   }
   fun manhattanLength(): Int {
       return (
           Math.abs(x) +
           Math.abs(y) 
       )
   }
   fun distanceSquared(to: Vector2Int): Int {
       return (
           (x.distance(to.x) * x.distance(to.x)) +
           (y.distance(to.y) * y.distance(to.y)) 
       )
   }
   fun distance(to: Vector2Int): Int = kotlin.math.sqrt(distanceSquared(to).toDouble()).toInt()
   fun normalize(): Vector2Int {
       val len = length()
       return dividedScalar(if (len <= 0.toInt()) 0.toInt() else len)
   }
   fun setLength(to: Int) {
        normalize()
        multiplyScalar(length())
   }
   fun lerp(v: Vector2Int, alpha: Int) {
           x += (v.x - x) * alpha
           y += (v.y - y) * alpha
   }
   fun lerped(v: Vector2Int, alpha: Int): Vector2Int {
       var retval = Vector2Int(this)
       retval.lerp(v, alpha)
       return retval
   }
   operator fun plus(v: Vector2Int): Vector2Int {
      return this.added(v)
   }
   operator fun minus(v: Vector2Int): Vector2Int {
      return this.subtracted(v)
   }
   operator fun times(v: Vector2Int): Vector2Int {
      return this.multiplied(v)
   }
   operator fun div(v: Vector2Int): Vector2Int {
      return this.divided(v)
   }
}
