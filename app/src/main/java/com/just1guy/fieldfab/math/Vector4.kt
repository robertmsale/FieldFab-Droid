import androidx.core.math.MathUtils
import com.just1guy.fieldfab.math.distance
@kotlinx.serialization.Serializable
data class Vector4Float (
   var x: Float = 0.toFloat(),
   var y: Float = 0.toFloat(),
   var z: Float = 0.toFloat(),
   var w: Float = 0.toFloat()
) {
   constructor(other: Vector4Float): this(
   other.x,
   other.y,
   other.z,
   other.w
   ) {}

   fun copy(): Vector4Float = Vector4Float(
       x,
       y,
       z,
       w,
   )

   fun addScalar(s: Float) {
       x += s
       y += s
       z += s
       w += s
   }
   fun addedScalar(s: Float): Vector4Float {
       var retval = Vector4Float(this)
       retval.x += s
       retval.y += s
       retval.z += s
       retval.w += s
       return retval
   }
   fun add(v: Vector4Float) {
       x += v.x
       y += v.y
       z += v.z
       w += v.w
   }
   fun added(v: Vector4Float): Vector4Float {
       var retval = Vector4Float(this)
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
   fun subbedScalar(s: Float): Vector4Float {
       var retval = Vector4Float(this)
       retval.x -= s
       retval.y -= s
       retval.z -= s
       retval.w -= s
       return retval
   }
   fun subtract(v: Vector4Float) {
       x -= v.x
       y -= v.y
       z -= v.z
       w -= v.w
   }
   fun subtracted(v: Vector4Float): Vector4Float {
       var retval = Vector4Float(this)
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
   fun multipliedScalar(s: Float): Vector4Float {
       var retval = Vector4Float(this)
       retval.x *= s
       retval.y *= s
       retval.z *= s
       retval.w *= s
       return retval
   }
   fun multiply(v: Vector4Float) {
       x *= v.x
       y *= v.y
       z *= v.z
       w *= v.w
   }
   fun multiplied(v: Vector4Float): Vector4Float {
       var retval = Vector4Float(this)
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
   fun dividedScalar(s: Float): Vector4Float {
       var retval = Vector4Float(this)
       retval.x /= s
       retval.y /= s
       retval.z /= s
       retval.w /= s
       return retval
   }
   fun divide(v: Vector4Float) {
       x /= v.x
       y /= v.y
       z /= v.z
       w /= v.w
   }
   fun divided(v: Vector4Float): Vector4Float {
       var retval = Vector4Float(this)
       retval.x /= v.x
       retval.y /= v.y
       retval.z /= v.z
       retval.w /= v.w
       return retval
   }
   fun dotProduct(v: Vector4Float): Float {
       var retval = 0.toFloat()
       retval += x * v.x
       retval += y * v.y
       retval += z * v.z
       retval += w * v.w
       return retval
   }
   fun crossProduct(v: Vector4Float): Float {
       var retval = x * v.x
       retval -= y * v.y
       retval -= z * v.z
       retval -= w * v.w
       return retval
   }
   fun zero() {
       x = 0.toFloat()
       y = 0.toFloat()
       z = 0.toFloat()
       w = 0.toFloat()
   }
   fun zeroed(
       x: Boolean = false,
       y: Boolean = false,
       z: Boolean = false,
       w: Boolean = false,
   ): Vector4Float = copy().multiplied(Vector4Float(
       if (x) 0.toFloat() else 1.toFloat(),
       if (y) 0.toFloat() else 1.toFloat(),
       if (z) 0.toFloat() else 1.toFloat(),
       if (w) 0.toFloat() else 1.toFloat(),
   ))

   fun clamp(min: Vector4Float, max: Vector4Float) {
       x = MathUtils.clamp(x, min.x, max.x)
       y = MathUtils.clamp(y, min.y, max.y)
       z = MathUtils.clamp(z, min.z, max.z)
       w = MathUtils.clamp(w, min.w, max.w)
   }
   fun clamped(min: Vector4Float, max: Vector4Float): Vector4Float {
       var retval = Vector4Float(this)
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
   fun floored(): Vector4Float {
       var retval = Vector4Float(this)
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
   fun ceiled(): Vector4Float {
       var retval = Vector4Float(this)
       retval.x = Math.ceil(x.toDouble()).toFloat()
       retval.y = Math.ceil(y.toDouble()).toFloat()
       retval.z = Math.ceil(z.toDouble()).toFloat()
       retval.w = Math.ceil(w.toDouble()).toFloat()
       return retval
   }
   fun round() {
       x = Math.round(x.toDouble()).toFloat()
       y = Math.round(y.toDouble()).toFloat()
       z = Math.round(z.toDouble()).toFloat()
       w = Math.round(w.toDouble()).toFloat()
   }
   fun rounded(): Vector4Float {
       var retval = Vector4Float(this)
       retval.x = Math.round(x.toDouble()).toFloat()
       retval.y = Math.round(y.toDouble()).toFloat()
       retval.z = Math.round(z.toDouble()).toFloat()
       retval.w = Math.round(w.toDouble()).toFloat()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.toFloat()) Math.ceil(x.toDouble()).toFloat() else Math.floor(x.toDouble()).toFloat()
       y = if (y < 0.toFloat()) Math.ceil(y.toDouble()).toFloat() else Math.floor(y.toDouble()).toFloat()
       z = if (z < 0.toFloat()) Math.ceil(z.toDouble()).toFloat() else Math.floor(z.toDouble()).toFloat()
       w = if (w < 0.toFloat()) Math.ceil(w.toDouble()).toFloat() else Math.floor(w.toDouble()).toFloat()
   }
   fun roundedToZero(): Vector4Float {
       var retval = Vector4Float(this)
       retval.x = if (x < 0.toFloat()) Math.ceil(x.toDouble()).toFloat() else Math.floor(x.toDouble()).toFloat()
       retval.y = if (y < 0.toFloat()) Math.ceil(y.toDouble()).toFloat() else Math.floor(y.toDouble()).toFloat()
       retval.z = if (z < 0.toFloat()) Math.ceil(z.toDouble()).toFloat() else Math.floor(z.toDouble()).toFloat()
       retval.w = if (w < 0.toFloat()) Math.ceil(w.toDouble()).toFloat() else Math.floor(w.toDouble()).toFloat()
       return retval
   }
   fun negate() {
       x = - x
       y = - y
       z = - z
       w = - w
   }
   fun negated(): Vector4Float {
       var retval = Vector4Float(this)
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
   fun distanceSquared(to: Vector4Float): Float {
       return (
           (x.distance(to.x) * x.distance(to.x)) +
           (y.distance(to.y) * y.distance(to.y)) +
           (z.distance(to.z) * z.distance(to.z)) +
           (w.distance(to.w) * w.distance(to.w)) 
       )
   }
   fun distance(to: Vector4Float): Float = kotlin.math.sqrt(distanceSquared(to).toDouble()).toFloat()
   fun normalize(): Vector4Float {
       val len = length()
       return dividedScalar(if (len <= 0.toFloat()) 0.toFloat() else len)
   }
   fun setLength(to: Float) {
        normalize()
        multiplyScalar(length())
   }
   fun lerp(v: Vector4Float, alpha: Float) {
           x += (v.x - x) * alpha
           y += (v.y - y) * alpha
           z += (v.z - z) * alpha
           w += (v.w - w) * alpha
   }
   fun lerped(v: Vector4Float, alpha: Float): Vector4Float {
       var retval = Vector4Float(this)
       retval.lerp(v, alpha)
       return retval
   }
   operator fun plus(v: Vector4Float): Vector4Float {
      return this.added(v)
   }
   operator fun minus(v: Vector4Float): Vector4Float {
      return this.subtracted(v)
   }
   operator fun times(v: Vector4Float): Vector4Float {
      return this.multiplied(v)
   }
   operator fun div(v: Vector4Float): Vector4Float {
      return this.divided(v)
   }
}
@kotlinx.serialization.Serializable
data class Vector4Double (
   var x: Double = 0.toDouble(),
   var y: Double = 0.toDouble(),
   var z: Double = 0.toDouble(),
   var w: Double = 0.toDouble()
) {
   constructor(other: Vector4Double): this(
   other.x,
   other.y,
   other.z,
   other.w
   ) {}

   fun copy(): Vector4Double = Vector4Double(
       x,
       y,
       z,
       w,
   )

   fun addScalar(s: Double) {
       x += s
       y += s
       z += s
       w += s
   }
   fun addedScalar(s: Double): Vector4Double {
       var retval = Vector4Double(this)
       retval.x += s
       retval.y += s
       retval.z += s
       retval.w += s
       return retval
   }
   fun add(v: Vector4Double) {
       x += v.x
       y += v.y
       z += v.z
       w += v.w
   }
   fun added(v: Vector4Double): Vector4Double {
       var retval = Vector4Double(this)
       retval.x += v.x
       retval.y += v.y
       retval.z += v.z
       retval.w += v.w
       return retval
   }
   fun subScalar(s: Double) {
       x -= s
       y -= s
       z -= s
       w -= s
   }
   fun subbedScalar(s: Double): Vector4Double {
       var retval = Vector4Double(this)
       retval.x -= s
       retval.y -= s
       retval.z -= s
       retval.w -= s
       return retval
   }
   fun subtract(v: Vector4Double) {
       x -= v.x
       y -= v.y
       z -= v.z
       w -= v.w
   }
   fun subtracted(v: Vector4Double): Vector4Double {
       var retval = Vector4Double(this)
       retval.x -= v.x
       retval.y -= v.y
       retval.z -= v.z
       retval.w -= v.w
       return retval
   }
   fun multiplyScalar(s: Double) {
       x *= s
       y *= s
       z *= s
       w *= s
   }
   fun multipliedScalar(s: Double): Vector4Double {
       var retval = Vector4Double(this)
       retval.x *= s
       retval.y *= s
       retval.z *= s
       retval.w *= s
       return retval
   }
   fun multiply(v: Vector4Double) {
       x *= v.x
       y *= v.y
       z *= v.z
       w *= v.w
   }
   fun multiplied(v: Vector4Double): Vector4Double {
       var retval = Vector4Double(this)
       retval.x *= v.x
       retval.y *= v.y
       retval.z *= v.z
       retval.w *= v.w
       return retval
   }
   fun divideScalar(s: Double) {
       x /= s
       y /= s
       z /= s
       w /= s
   }
   fun dividedScalar(s: Double): Vector4Double {
       var retval = Vector4Double(this)
       retval.x /= s
       retval.y /= s
       retval.z /= s
       retval.w /= s
       return retval
   }
   fun divide(v: Vector4Double) {
       x /= v.x
       y /= v.y
       z /= v.z
       w /= v.w
   }
   fun divided(v: Vector4Double): Vector4Double {
       var retval = Vector4Double(this)
       retval.x /= v.x
       retval.y /= v.y
       retval.z /= v.z
       retval.w /= v.w
       return retval
   }
   fun dotProduct(v: Vector4Double): Double {
       var retval = 0.toDouble()
       retval += x * v.x
       retval += y * v.y
       retval += z * v.z
       retval += w * v.w
       return retval
   }
   fun crossProduct(v: Vector4Double): Double {
       var retval = x * v.x
       retval -= y * v.y
       retval -= z * v.z
       retval -= w * v.w
       return retval
   }
   fun zero() {
       x = 0.toDouble()
       y = 0.toDouble()
       z = 0.toDouble()
       w = 0.toDouble()
   }
   fun zeroed(
       x: Boolean = false,
       y: Boolean = false,
       z: Boolean = false,
       w: Boolean = false,
   ): Vector4Double = copy().multiplied(Vector4Double(
       if (x) 0.toDouble() else 1.toDouble(),
       if (y) 0.toDouble() else 1.toDouble(),
       if (z) 0.toDouble() else 1.toDouble(),
       if (w) 0.toDouble() else 1.toDouble(),
   ))

   fun clamp(min: Vector4Double, max: Vector4Double) {
       x = MathUtils.clamp(x, min.x, max.x)
       y = MathUtils.clamp(y, min.y, max.y)
       z = MathUtils.clamp(z, min.z, max.z)
       w = MathUtils.clamp(w, min.w, max.w)
   }
   fun clamped(min: Vector4Double, max: Vector4Double): Vector4Double {
       var retval = Vector4Double(this)
       retval.x = MathUtils.clamp(x, min.x, max.x)
       retval.y = MathUtils.clamp(y, min.y, max.y)
       retval.z = MathUtils.clamp(z, min.z, max.z)
       retval.w = MathUtils.clamp(w, min.w, max.w)
       return retval
   }
   fun floor() {
       x = Math.floor(x.toDouble()).toDouble()
       y = Math.floor(y.toDouble()).toDouble()
       z = Math.floor(z.toDouble()).toDouble()
       w = Math.floor(w.toDouble()).toDouble()
   }
   fun floored(): Vector4Double {
       var retval = Vector4Double(this)
       retval.x = Math.floor(x.toDouble()).toDouble()
       retval.y = Math.floor(y.toDouble()).toDouble()
       retval.z = Math.floor(z.toDouble()).toDouble()
       retval.w = Math.floor(w.toDouble()).toDouble()
       return retval
   }
   fun ceil() {
       x = Math.ceil(x.toDouble()).toDouble()
       y = Math.ceil(y.toDouble()).toDouble()
       z = Math.ceil(z.toDouble()).toDouble()
       w = Math.ceil(w.toDouble()).toDouble()
   }
   fun ceiled(): Vector4Double {
       var retval = Vector4Double(this)
       retval.x = Math.ceil(x.toDouble()).toDouble()
       retval.y = Math.ceil(y.toDouble()).toDouble()
       retval.z = Math.ceil(z.toDouble()).toDouble()
       retval.w = Math.ceil(w.toDouble()).toDouble()
       return retval
   }
   fun round() {
       x = Math.round(x.toDouble()).toDouble()
       y = Math.round(y.toDouble()).toDouble()
       z = Math.round(z.toDouble()).toDouble()
       w = Math.round(w.toDouble()).toDouble()
   }
   fun rounded(): Vector4Double {
       var retval = Vector4Double(this)
       retval.x = Math.round(x.toDouble()).toDouble()
       retval.y = Math.round(y.toDouble()).toDouble()
       retval.z = Math.round(z.toDouble()).toDouble()
       retval.w = Math.round(w.toDouble()).toDouble()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.toDouble()) Math.ceil(x.toDouble()).toDouble() else Math.floor(x.toDouble()).toDouble()
       y = if (y < 0.toDouble()) Math.ceil(y.toDouble()).toDouble() else Math.floor(y.toDouble()).toDouble()
       z = if (z < 0.toDouble()) Math.ceil(z.toDouble()).toDouble() else Math.floor(z.toDouble()).toDouble()
       w = if (w < 0.toDouble()) Math.ceil(w.toDouble()).toDouble() else Math.floor(w.toDouble()).toDouble()
   }
   fun roundedToZero(): Vector4Double {
       var retval = Vector4Double(this)
       retval.x = if (x < 0.toDouble()) Math.ceil(x.toDouble()).toDouble() else Math.floor(x.toDouble()).toDouble()
       retval.y = if (y < 0.toDouble()) Math.ceil(y.toDouble()).toDouble() else Math.floor(y.toDouble()).toDouble()
       retval.z = if (z < 0.toDouble()) Math.ceil(z.toDouble()).toDouble() else Math.floor(z.toDouble()).toDouble()
       retval.w = if (w < 0.toDouble()) Math.ceil(w.toDouble()).toDouble() else Math.floor(w.toDouble()).toDouble()
       return retval
   }
   fun negate() {
       x = - x
       y = - y
       z = - z
       w = - w
   }
   fun negated(): Vector4Double {
       var retval = Vector4Double(this)
       retval.x = - x
       retval.y = - y
       retval.z = - z
       retval.w = - w
       return retval
   }
   fun lengthSquared(): Double {
       return (
           (x * x).toDouble() +
           (y * y).toDouble() +
           (z * z).toDouble() +
           (w * w).toDouble() 
       ).toDouble()
   }
   fun length(): Double {
       return Math.sqrt(
           (x * x).toDouble() +
           (y * y).toDouble() +
           (z * z).toDouble() +
           (w * w).toDouble() 
       ).toDouble()
   }
   fun manhattanLength(): Double {
       return (
           Math.abs(x) +
           Math.abs(y) +
           Math.abs(z) +
           Math.abs(w) 
       )
   }
   fun distanceSquared(to: Vector4Double): Double {
       return (
           (x.distance(to.x) * x.distance(to.x)) +
           (y.distance(to.y) * y.distance(to.y)) +
           (z.distance(to.z) * z.distance(to.z)) +
           (w.distance(to.w) * w.distance(to.w)) 
       )
   }
   fun distance(to: Vector4Double): Double = kotlin.math.sqrt(distanceSquared(to).toDouble()).toDouble()
   fun normalize(): Vector4Double {
       val len = length()
       return dividedScalar(if (len <= 0.toDouble()) 0.toDouble() else len)
   }
   fun setLength(to: Double) {
        normalize()
        multiplyScalar(length())
   }
   fun lerp(v: Vector4Double, alpha: Double) {
           x += (v.x - x) * alpha
           y += (v.y - y) * alpha
           z += (v.z - z) * alpha
           w += (v.w - w) * alpha
   }
   fun lerped(v: Vector4Double, alpha: Double): Vector4Double {
       var retval = Vector4Double(this)
       retval.lerp(v, alpha)
       return retval
   }
   operator fun plus(v: Vector4Double): Vector4Double {
      return this.added(v)
   }
   operator fun minus(v: Vector4Double): Vector4Double {
      return this.subtracted(v)
   }
   operator fun times(v: Vector4Double): Vector4Double {
      return this.multiplied(v)
   }
   operator fun div(v: Vector4Double): Vector4Double {
      return this.divided(v)
   }
}
@kotlinx.serialization.Serializable
data class Vector4Int (
   var x: Int = 0.toInt(),
   var y: Int = 0.toInt(),
   var z: Int = 0.toInt(),
   var w: Int = 0.toInt()
) {
   constructor(other: Vector4Int): this(
   other.x,
   other.y,
   other.z,
   other.w
   ) {}

   fun copy(): Vector4Int = Vector4Int(
       x,
       y,
       z,
       w,
   )

   fun addScalar(s: Int) {
       x += s
       y += s
       z += s
       w += s
   }
   fun addedScalar(s: Int): Vector4Int {
       var retval = Vector4Int(this)
       retval.x += s
       retval.y += s
       retval.z += s
       retval.w += s
       return retval
   }
   fun add(v: Vector4Int) {
       x += v.x
       y += v.y
       z += v.z
       w += v.w
   }
   fun added(v: Vector4Int): Vector4Int {
       var retval = Vector4Int(this)
       retval.x += v.x
       retval.y += v.y
       retval.z += v.z
       retval.w += v.w
       return retval
   }
   fun subScalar(s: Int) {
       x -= s
       y -= s
       z -= s
       w -= s
   }
   fun subbedScalar(s: Int): Vector4Int {
       var retval = Vector4Int(this)
       retval.x -= s
       retval.y -= s
       retval.z -= s
       retval.w -= s
       return retval
   }
   fun subtract(v: Vector4Int) {
       x -= v.x
       y -= v.y
       z -= v.z
       w -= v.w
   }
   fun subtracted(v: Vector4Int): Vector4Int {
       var retval = Vector4Int(this)
       retval.x -= v.x
       retval.y -= v.y
       retval.z -= v.z
       retval.w -= v.w
       return retval
   }
   fun multiplyScalar(s: Int) {
       x *= s
       y *= s
       z *= s
       w *= s
   }
   fun multipliedScalar(s: Int): Vector4Int {
       var retval = Vector4Int(this)
       retval.x *= s
       retval.y *= s
       retval.z *= s
       retval.w *= s
       return retval
   }
   fun multiply(v: Vector4Int) {
       x *= v.x
       y *= v.y
       z *= v.z
       w *= v.w
   }
   fun multiplied(v: Vector4Int): Vector4Int {
       var retval = Vector4Int(this)
       retval.x *= v.x
       retval.y *= v.y
       retval.z *= v.z
       retval.w *= v.w
       return retval
   }
   fun divideScalar(s: Int) {
       x /= s
       y /= s
       z /= s
       w /= s
   }
   fun dividedScalar(s: Int): Vector4Int {
       var retval = Vector4Int(this)
       retval.x /= s
       retval.y /= s
       retval.z /= s
       retval.w /= s
       return retval
   }
   fun divide(v: Vector4Int) {
       x /= v.x
       y /= v.y
       z /= v.z
       w /= v.w
   }
   fun divided(v: Vector4Int): Vector4Int {
       var retval = Vector4Int(this)
       retval.x /= v.x
       retval.y /= v.y
       retval.z /= v.z
       retval.w /= v.w
       return retval
   }
   fun dotProduct(v: Vector4Int): Int {
       var retval = 0.toInt()
       retval += x * v.x
       retval += y * v.y
       retval += z * v.z
       retval += w * v.w
       return retval
   }
   fun crossProduct(v: Vector4Int): Int {
       var retval = x * v.x
       retval -= y * v.y
       retval -= z * v.z
       retval -= w * v.w
       return retval
   }
   fun zero() {
       x = 0.toInt()
       y = 0.toInt()
       z = 0.toInt()
       w = 0.toInt()
   }
   fun zeroed(
       x: Boolean = false,
       y: Boolean = false,
       z: Boolean = false,
       w: Boolean = false,
   ): Vector4Int = copy().multiplied(Vector4Int(
       if (x) 0.toInt() else 1.toInt(),
       if (y) 0.toInt() else 1.toInt(),
       if (z) 0.toInt() else 1.toInt(),
       if (w) 0.toInt() else 1.toInt(),
   ))

   fun clamp(min: Vector4Int, max: Vector4Int) {
       x = MathUtils.clamp(x, min.x, max.x)
       y = MathUtils.clamp(y, min.y, max.y)
       z = MathUtils.clamp(z, min.z, max.z)
       w = MathUtils.clamp(w, min.w, max.w)
   }
   fun clamped(min: Vector4Int, max: Vector4Int): Vector4Int {
       var retval = Vector4Int(this)
       retval.x = MathUtils.clamp(x, min.x, max.x)
       retval.y = MathUtils.clamp(y, min.y, max.y)
       retval.z = MathUtils.clamp(z, min.z, max.z)
       retval.w = MathUtils.clamp(w, min.w, max.w)
       return retval
   }
   fun floor() {
       x = Math.floor(x.toDouble()).toInt()
       y = Math.floor(y.toDouble()).toInt()
       z = Math.floor(z.toDouble()).toInt()
       w = Math.floor(w.toDouble()).toInt()
   }
   fun floored(): Vector4Int {
       var retval = Vector4Int(this)
       retval.x = Math.floor(x.toDouble()).toInt()
       retval.y = Math.floor(y.toDouble()).toInt()
       retval.z = Math.floor(z.toDouble()).toInt()
       retval.w = Math.floor(w.toDouble()).toInt()
       return retval
   }
   fun ceil() {
       x = Math.ceil(x.toDouble()).toInt()
       y = Math.ceil(y.toDouble()).toInt()
       z = Math.ceil(z.toDouble()).toInt()
       w = Math.ceil(w.toDouble()).toInt()
   }
   fun ceiled(): Vector4Int {
       var retval = Vector4Int(this)
       retval.x = Math.ceil(x.toDouble()).toInt()
       retval.y = Math.ceil(y.toDouble()).toInt()
       retval.z = Math.ceil(z.toDouble()).toInt()
       retval.w = Math.ceil(w.toDouble()).toInt()
       return retval
   }
   fun round() {
       x = Math.round(x.toDouble()).toInt()
       y = Math.round(y.toDouble()).toInt()
       z = Math.round(z.toDouble()).toInt()
       w = Math.round(w.toDouble()).toInt()
   }
   fun rounded(): Vector4Int {
       var retval = Vector4Int(this)
       retval.x = Math.round(x.toDouble()).toInt()
       retval.y = Math.round(y.toDouble()).toInt()
       retval.z = Math.round(z.toDouble()).toInt()
       retval.w = Math.round(w.toDouble()).toInt()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.toInt()) Math.ceil(x.toDouble()).toInt() else Math.floor(x.toDouble()).toInt()
       y = if (y < 0.toInt()) Math.ceil(y.toDouble()).toInt() else Math.floor(y.toDouble()).toInt()
       z = if (z < 0.toInt()) Math.ceil(z.toDouble()).toInt() else Math.floor(z.toDouble()).toInt()
       w = if (w < 0.toInt()) Math.ceil(w.toDouble()).toInt() else Math.floor(w.toDouble()).toInt()
   }
   fun roundedToZero(): Vector4Int {
       var retval = Vector4Int(this)
       retval.x = if (x < 0.toInt()) Math.ceil(x.toDouble()).toInt() else Math.floor(x.toDouble()).toInt()
       retval.y = if (y < 0.toInt()) Math.ceil(y.toDouble()).toInt() else Math.floor(y.toDouble()).toInt()
       retval.z = if (z < 0.toInt()) Math.ceil(z.toDouble()).toInt() else Math.floor(z.toDouble()).toInt()
       retval.w = if (w < 0.toInt()) Math.ceil(w.toDouble()).toInt() else Math.floor(w.toDouble()).toInt()
       return retval
   }
   fun negate() {
       x = - x
       y = - y
       z = - z
       w = - w
   }
   fun negated(): Vector4Int {
       var retval = Vector4Int(this)
       retval.x = - x
       retval.y = - y
       retval.z = - z
       retval.w = - w
       return retval
   }
   fun lengthSquared(): Int {
       return (
           (x * x).toDouble() +
           (y * y).toDouble() +
           (z * z).toDouble() +
           (w * w).toDouble() 
       ).toInt()
   }
   fun length(): Int {
       return Math.sqrt(
           (x * x).toDouble() +
           (y * y).toDouble() +
           (z * z).toDouble() +
           (w * w).toDouble() 
       ).toInt()
   }
   fun manhattanLength(): Int {
       return (
           Math.abs(x) +
           Math.abs(y) +
           Math.abs(z) +
           Math.abs(w) 
       )
   }
   fun distanceSquared(to: Vector4Int): Int {
       return (
           (x.distance(to.x) * x.distance(to.x)) +
           (y.distance(to.y) * y.distance(to.y)) +
           (z.distance(to.z) * z.distance(to.z)) +
           (w.distance(to.w) * w.distance(to.w)) 
       )
   }
   fun distance(to: Vector4Int): Int = kotlin.math.sqrt(distanceSquared(to).toDouble()).toInt()
   fun normalize(): Vector4Int {
       val len = length()
       return dividedScalar(if (len <= 0.toInt()) 0.toInt() else len)
   }
   fun setLength(to: Int) {
        normalize()
        multiplyScalar(length())
   }
   fun lerp(v: Vector4Int, alpha: Int) {
           x += (v.x - x) * alpha
           y += (v.y - y) * alpha
           z += (v.z - z) * alpha
           w += (v.w - w) * alpha
   }
   fun lerped(v: Vector4Int, alpha: Int): Vector4Int {
       var retval = Vector4Int(this)
       retval.lerp(v, alpha)
       return retval
   }
   operator fun plus(v: Vector4Int): Vector4Int {
      return this.added(v)
   }
   operator fun minus(v: Vector4Int): Vector4Int {
      return this.subtracted(v)
   }
   operator fun times(v: Vector4Int): Vector4Int {
      return this.multiplied(v)
   }
   operator fun div(v: Vector4Int): Vector4Int {
      return this.divided(v)
   }
}
