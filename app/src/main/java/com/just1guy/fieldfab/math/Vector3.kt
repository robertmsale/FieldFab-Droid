import androidx.core.math.MathUtils
import com.just1guy.fieldfab.math.distance
@kotlinx.serialization.Serializable
data class Vector3Float (
   var x: Float = 0.toFloat(),
   var y: Float = 0.toFloat(),
   var z: Float = 0.toFloat()
) {
   constructor(other: Vector3Float): this(
   other.x,
   other.y,
   other.z
   ) {}

   fun copy(): Vector3Float = Vector3Float(
       x,
       y,
       z,
   )

   fun addScalar(s: Float) {
       x += s
       y += s
       z += s
   }
   fun addedScalar(s: Float): Vector3Float {
       var retval = Vector3Float(this)
       retval.x += s
       retval.y += s
       retval.z += s
       return retval
   }
   fun add(v: Vector3Float) {
       x += v.x
       y += v.y
       z += v.z
   }
   fun added(v: Vector3Float): Vector3Float {
       var retval = Vector3Float(this)
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
   fun subbedScalar(s: Float): Vector3Float {
       var retval = Vector3Float(this)
       retval.x -= s
       retval.y -= s
       retval.z -= s
       return retval
   }
   fun subtract(v: Vector3Float) {
       x -= v.x
       y -= v.y
       z -= v.z
   }
   fun subtracted(v: Vector3Float): Vector3Float {
       var retval = Vector3Float(this)
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
   fun multipliedScalar(s: Float): Vector3Float {
       var retval = Vector3Float(this)
       retval.x *= s
       retval.y *= s
       retval.z *= s
       return retval
   }
   fun multiply(v: Vector3Float) {
       x *= v.x
       y *= v.y
       z *= v.z
   }
   fun multiplied(v: Vector3Float): Vector3Float {
       var retval = Vector3Float(this)
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
   fun dividedScalar(s: Float): Vector3Float {
       var retval = Vector3Float(this)
       retval.x /= s
       retval.y /= s
       retval.z /= s
       return retval
   }
   fun divide(v: Vector3Float) {
       x /= v.x
       y /= v.y
       z /= v.z
   }
   fun divided(v: Vector3Float): Vector3Float {
       var retval = Vector3Float(this)
       retval.x /= v.x
       retval.y /= v.y
       retval.z /= v.z
       return retval
   }
   fun dotProduct(v: Vector3Float): Float {
       var retval = 0.toFloat()
       retval += x * v.x
       retval += y * v.y
       retval += z * v.z
       return retval
   }
   fun crossProduct(v: Vector3Float): Float {
       var retval = x * v.x
       retval -= y * v.y
       retval -= z * v.z
       return retval
   }
   fun zero() {
       x = 0.toFloat()
       y = 0.toFloat()
       z = 0.toFloat()
   }
   fun zeroed(
       x: Boolean = false,
       y: Boolean = false,
       z: Boolean = false,
   ): Vector3Float = copy().multiplied(Vector3Float(
       if (x) 0.toFloat() else 1.toFloat(),
       if (y) 0.toFloat() else 1.toFloat(),
       if (z) 0.toFloat() else 1.toFloat(),
   ))

   fun clamp(min: Vector3Float, max: Vector3Float) {
       x = MathUtils.clamp(x, min.x, max.x)
       y = MathUtils.clamp(y, min.y, max.y)
       z = MathUtils.clamp(z, min.z, max.z)
   }
   fun clamped(min: Vector3Float, max: Vector3Float): Vector3Float {
       var retval = Vector3Float(this)
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
   fun floored(): Vector3Float {
       var retval = Vector3Float(this)
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
   fun ceiled(): Vector3Float {
       var retval = Vector3Float(this)
       retval.x = Math.ceil(x.toDouble()).toFloat()
       retval.y = Math.ceil(y.toDouble()).toFloat()
       retval.z = Math.ceil(z.toDouble()).toFloat()
       return retval
   }
   fun round() {
       x = Math.round(x.toDouble()).toFloat()
       y = Math.round(y.toDouble()).toFloat()
       z = Math.round(z.toDouble()).toFloat()
   }
   fun rounded(): Vector3Float {
       var retval = Vector3Float(this)
       retval.x = Math.round(x.toDouble()).toFloat()
       retval.y = Math.round(y.toDouble()).toFloat()
       retval.z = Math.round(z.toDouble()).toFloat()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.toFloat()) Math.ceil(x.toDouble()).toFloat() else Math.floor(x.toDouble()).toFloat()
       y = if (y < 0.toFloat()) Math.ceil(y.toDouble()).toFloat() else Math.floor(y.toDouble()).toFloat()
       z = if (z < 0.toFloat()) Math.ceil(z.toDouble()).toFloat() else Math.floor(z.toDouble()).toFloat()
   }
   fun roundedToZero(): Vector3Float {
       var retval = Vector3Float(this)
       retval.x = if (x < 0.toFloat()) Math.ceil(x.toDouble()).toFloat() else Math.floor(x.toDouble()).toFloat()
       retval.y = if (y < 0.toFloat()) Math.ceil(y.toDouble()).toFloat() else Math.floor(y.toDouble()).toFloat()
       retval.z = if (z < 0.toFloat()) Math.ceil(z.toDouble()).toFloat() else Math.floor(z.toDouble()).toFloat()
       return retval
   }
   fun negate() {
       x = - x
       y = - y
       z = - z
   }
   fun negated(): Vector3Float {
       var retval = Vector3Float(this)
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
   fun distanceSquared(to: Vector3Float): Float {
       return (
           (x.distance(to.x) * x.distance(to.x)) +
           (y.distance(to.y) * y.distance(to.y)) +
           (z.distance(to.z) * z.distance(to.z)) 
       )
   }
   fun distance(to: Vector3Float): Float = kotlin.math.sqrt(distanceSquared(to).toDouble()).toFloat()
   fun normalize(): Vector3Float {
       val len = length()
       return dividedScalar(if (len <= 0.toFloat()) 0.toFloat() else len)
   }
   fun setLength(to: Float) {
        normalize()
        multiplyScalar(length())
   }
   fun lerp(v: Vector3Float, alpha: Float) {
           x += (v.x - x) * alpha
           y += (v.y - y) * alpha
           z += (v.z - z) * alpha
   }
   fun lerped(v: Vector3Float, alpha: Float): Vector3Float {
       var retval = Vector3Float(this)
       retval.lerp(v, alpha)
       return retval
   }
   operator fun plus(v: Vector3Float): Vector3Float {
      return this.added(v)
   }
   operator fun minus(v: Vector3Float): Vector3Float {
      return this.subtracted(v)
   }
   operator fun times(v: Vector3Float): Vector3Float {
      return this.multiplied(v)
   }
   operator fun div(v: Vector3Float): Vector3Float {
      return this.divided(v)
   }
}
@kotlinx.serialization.Serializable
data class Vector3Double (
   var x: Double = 0.toDouble(),
   var y: Double = 0.toDouble(),
   var z: Double = 0.toDouble()
) {
   constructor(other: Vector3Double): this(
   other.x,
   other.y,
   other.z
   ) {}

   fun copy(): Vector3Double = Vector3Double(
       x,
       y,
       z,
   )

   fun addScalar(s: Double) {
       x += s
       y += s
       z += s
   }
   fun addedScalar(s: Double): Vector3Double {
       var retval = Vector3Double(this)
       retval.x += s
       retval.y += s
       retval.z += s
       return retval
   }
   fun add(v: Vector3Double) {
       x += v.x
       y += v.y
       z += v.z
   }
   fun added(v: Vector3Double): Vector3Double {
       var retval = Vector3Double(this)
       retval.x += v.x
       retval.y += v.y
       retval.z += v.z
       return retval
   }
   fun subScalar(s: Double) {
       x -= s
       y -= s
       z -= s
   }
   fun subbedScalar(s: Double): Vector3Double {
       var retval = Vector3Double(this)
       retval.x -= s
       retval.y -= s
       retval.z -= s
       return retval
   }
   fun subtract(v: Vector3Double) {
       x -= v.x
       y -= v.y
       z -= v.z
   }
   fun subtracted(v: Vector3Double): Vector3Double {
       var retval = Vector3Double(this)
       retval.x -= v.x
       retval.y -= v.y
       retval.z -= v.z
       return retval
   }
   fun multiplyScalar(s: Double) {
       x *= s
       y *= s
       z *= s
   }
   fun multipliedScalar(s: Double): Vector3Double {
       var retval = Vector3Double(this)
       retval.x *= s
       retval.y *= s
       retval.z *= s
       return retval
   }
   fun multiply(v: Vector3Double) {
       x *= v.x
       y *= v.y
       z *= v.z
   }
   fun multiplied(v: Vector3Double): Vector3Double {
       var retval = Vector3Double(this)
       retval.x *= v.x
       retval.y *= v.y
       retval.z *= v.z
       return retval
   }
   fun divideScalar(s: Double) {
       x /= s
       y /= s
       z /= s
   }
   fun dividedScalar(s: Double): Vector3Double {
       var retval = Vector3Double(this)
       retval.x /= s
       retval.y /= s
       retval.z /= s
       return retval
   }
   fun divide(v: Vector3Double) {
       x /= v.x
       y /= v.y
       z /= v.z
   }
   fun divided(v: Vector3Double): Vector3Double {
       var retval = Vector3Double(this)
       retval.x /= v.x
       retval.y /= v.y
       retval.z /= v.z
       return retval
   }
   fun dotProduct(v: Vector3Double): Double {
       var retval = 0.toDouble()
       retval += x * v.x
       retval += y * v.y
       retval += z * v.z
       return retval
   }
   fun crossProduct(v: Vector3Double): Double {
       var retval = x * v.x
       retval -= y * v.y
       retval -= z * v.z
       return retval
   }
   fun zero() {
       x = 0.toDouble()
       y = 0.toDouble()
       z = 0.toDouble()
   }
   fun zeroed(
       x: Boolean = false,
       y: Boolean = false,
       z: Boolean = false,
   ): Vector3Double = copy().multiplied(Vector3Double(
       if (x) 0.toDouble() else 1.toDouble(),
       if (y) 0.toDouble() else 1.toDouble(),
       if (z) 0.toDouble() else 1.toDouble(),
   ))

   fun clamp(min: Vector3Double, max: Vector3Double) {
       x = MathUtils.clamp(x, min.x, max.x)
       y = MathUtils.clamp(y, min.y, max.y)
       z = MathUtils.clamp(z, min.z, max.z)
   }
   fun clamped(min: Vector3Double, max: Vector3Double): Vector3Double {
       var retval = Vector3Double(this)
       retval.x = MathUtils.clamp(x, min.x, max.x)
       retval.y = MathUtils.clamp(y, min.y, max.y)
       retval.z = MathUtils.clamp(z, min.z, max.z)
       return retval
   }
   fun floor() {
       x = Math.floor(x.toDouble()).toDouble()
       y = Math.floor(y.toDouble()).toDouble()
       z = Math.floor(z.toDouble()).toDouble()
   }
   fun floored(): Vector3Double {
       var retval = Vector3Double(this)
       retval.x = Math.floor(x.toDouble()).toDouble()
       retval.y = Math.floor(y.toDouble()).toDouble()
       retval.z = Math.floor(z.toDouble()).toDouble()
       return retval
   }
   fun ceil() {
       x = Math.ceil(x.toDouble()).toDouble()
       y = Math.ceil(y.toDouble()).toDouble()
       z = Math.ceil(z.toDouble()).toDouble()
   }
   fun ceiled(): Vector3Double {
       var retval = Vector3Double(this)
       retval.x = Math.ceil(x.toDouble()).toDouble()
       retval.y = Math.ceil(y.toDouble()).toDouble()
       retval.z = Math.ceil(z.toDouble()).toDouble()
       return retval
   }
   fun round() {
       x = Math.round(x.toDouble()).toDouble()
       y = Math.round(y.toDouble()).toDouble()
       z = Math.round(z.toDouble()).toDouble()
   }
   fun rounded(): Vector3Double {
       var retval = Vector3Double(this)
       retval.x = Math.round(x.toDouble()).toDouble()
       retval.y = Math.round(y.toDouble()).toDouble()
       retval.z = Math.round(z.toDouble()).toDouble()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.toDouble()) Math.ceil(x.toDouble()).toDouble() else Math.floor(x.toDouble()).toDouble()
       y = if (y < 0.toDouble()) Math.ceil(y.toDouble()).toDouble() else Math.floor(y.toDouble()).toDouble()
       z = if (z < 0.toDouble()) Math.ceil(z.toDouble()).toDouble() else Math.floor(z.toDouble()).toDouble()
   }
   fun roundedToZero(): Vector3Double {
       var retval = Vector3Double(this)
       retval.x = if (x < 0.toDouble()) Math.ceil(x.toDouble()).toDouble() else Math.floor(x.toDouble()).toDouble()
       retval.y = if (y < 0.toDouble()) Math.ceil(y.toDouble()).toDouble() else Math.floor(y.toDouble()).toDouble()
       retval.z = if (z < 0.toDouble()) Math.ceil(z.toDouble()).toDouble() else Math.floor(z.toDouble()).toDouble()
       return retval
   }
   fun negate() {
       x = - x
       y = - y
       z = - z
   }
   fun negated(): Vector3Double {
       var retval = Vector3Double(this)
       retval.x = - x
       retval.y = - y
       retval.z = - z
       return retval
   }
   fun lengthSquared(): Double {
       return (
           (x * x).toDouble() +
           (y * y).toDouble() +
           (z * z).toDouble() 
       ).toDouble()
   }
   fun length(): Double {
       return Math.sqrt(
           (x * x).toDouble() +
           (y * y).toDouble() +
           (z * z).toDouble() 
       ).toDouble()
   }
   fun manhattanLength(): Double {
       return (
           Math.abs(x) +
           Math.abs(y) +
           Math.abs(z) 
       )
   }
   fun distanceSquared(to: Vector3Double): Double {
       return (
           (x.distance(to.x) * x.distance(to.x)) +
           (y.distance(to.y) * y.distance(to.y)) +
           (z.distance(to.z) * z.distance(to.z)) 
       )
   }
   fun distance(to: Vector3Double): Double = kotlin.math.sqrt(distanceSquared(to).toDouble()).toDouble()
   fun normalize(): Vector3Double {
       val len = length()
       return dividedScalar(if (len <= 0.toDouble()) 0.toDouble() else len)
   }
   fun setLength(to: Double) {
        normalize()
        multiplyScalar(length())
   }
   fun lerp(v: Vector3Double, alpha: Double) {
           x += (v.x - x) * alpha
           y += (v.y - y) * alpha
           z += (v.z - z) * alpha
   }
   fun lerped(v: Vector3Double, alpha: Double): Vector3Double {
       var retval = Vector3Double(this)
       retval.lerp(v, alpha)
       return retval
   }
   operator fun plus(v: Vector3Double): Vector3Double {
      return this.added(v)
   }
   operator fun minus(v: Vector3Double): Vector3Double {
      return this.subtracted(v)
   }
   operator fun times(v: Vector3Double): Vector3Double {
      return this.multiplied(v)
   }
   operator fun div(v: Vector3Double): Vector3Double {
      return this.divided(v)
   }
}
@kotlinx.serialization.Serializable
data class Vector3Int (
   var x: Int = 0.toInt(),
   var y: Int = 0.toInt(),
   var z: Int = 0.toInt()
) {
   constructor(other: Vector3Int): this(
   other.x,
   other.y,
   other.z
   ) {}

   fun copy(): Vector3Int = Vector3Int(
       x,
       y,
       z,
   )

   fun addScalar(s: Int) {
       x += s
       y += s
       z += s
   }
   fun addedScalar(s: Int): Vector3Int {
       var retval = Vector3Int(this)
       retval.x += s
       retval.y += s
       retval.z += s
       return retval
   }
   fun add(v: Vector3Int) {
       x += v.x
       y += v.y
       z += v.z
   }
   fun added(v: Vector3Int): Vector3Int {
       var retval = Vector3Int(this)
       retval.x += v.x
       retval.y += v.y
       retval.z += v.z
       return retval
   }
   fun subScalar(s: Int) {
       x -= s
       y -= s
       z -= s
   }
   fun subbedScalar(s: Int): Vector3Int {
       var retval = Vector3Int(this)
       retval.x -= s
       retval.y -= s
       retval.z -= s
       return retval
   }
   fun subtract(v: Vector3Int) {
       x -= v.x
       y -= v.y
       z -= v.z
   }
   fun subtracted(v: Vector3Int): Vector3Int {
       var retval = Vector3Int(this)
       retval.x -= v.x
       retval.y -= v.y
       retval.z -= v.z
       return retval
   }
   fun multiplyScalar(s: Int) {
       x *= s
       y *= s
       z *= s
   }
   fun multipliedScalar(s: Int): Vector3Int {
       var retval = Vector3Int(this)
       retval.x *= s
       retval.y *= s
       retval.z *= s
       return retval
   }
   fun multiply(v: Vector3Int) {
       x *= v.x
       y *= v.y
       z *= v.z
   }
   fun multiplied(v: Vector3Int): Vector3Int {
       var retval = Vector3Int(this)
       retval.x *= v.x
       retval.y *= v.y
       retval.z *= v.z
       return retval
   }
   fun divideScalar(s: Int) {
       x /= s
       y /= s
       z /= s
   }
   fun dividedScalar(s: Int): Vector3Int {
       var retval = Vector3Int(this)
       retval.x /= s
       retval.y /= s
       retval.z /= s
       return retval
   }
   fun divide(v: Vector3Int) {
       x /= v.x
       y /= v.y
       z /= v.z
   }
   fun divided(v: Vector3Int): Vector3Int {
       var retval = Vector3Int(this)
       retval.x /= v.x
       retval.y /= v.y
       retval.z /= v.z
       return retval
   }
   fun dotProduct(v: Vector3Int): Int {
       var retval = 0.toInt()
       retval += x * v.x
       retval += y * v.y
       retval += z * v.z
       return retval
   }
   fun crossProduct(v: Vector3Int): Int {
       var retval = x * v.x
       retval -= y * v.y
       retval -= z * v.z
       return retval
   }
   fun zero() {
       x = 0.toInt()
       y = 0.toInt()
       z = 0.toInt()
   }
   fun zeroed(
       x: Boolean = false,
       y: Boolean = false,
       z: Boolean = false,
   ): Vector3Int = copy().multiplied(Vector3Int(
       if (x) 0.toInt() else 1.toInt(),
       if (y) 0.toInt() else 1.toInt(),
       if (z) 0.toInt() else 1.toInt(),
   ))

   fun clamp(min: Vector3Int, max: Vector3Int) {
       x = MathUtils.clamp(x, min.x, max.x)
       y = MathUtils.clamp(y, min.y, max.y)
       z = MathUtils.clamp(z, min.z, max.z)
   }
   fun clamped(min: Vector3Int, max: Vector3Int): Vector3Int {
       var retval = Vector3Int(this)
       retval.x = MathUtils.clamp(x, min.x, max.x)
       retval.y = MathUtils.clamp(y, min.y, max.y)
       retval.z = MathUtils.clamp(z, min.z, max.z)
       return retval
   }
   fun floor() {
       x = Math.floor(x.toDouble()).toInt()
       y = Math.floor(y.toDouble()).toInt()
       z = Math.floor(z.toDouble()).toInt()
   }
   fun floored(): Vector3Int {
       var retval = Vector3Int(this)
       retval.x = Math.floor(x.toDouble()).toInt()
       retval.y = Math.floor(y.toDouble()).toInt()
       retval.z = Math.floor(z.toDouble()).toInt()
       return retval
   }
   fun ceil() {
       x = Math.ceil(x.toDouble()).toInt()
       y = Math.ceil(y.toDouble()).toInt()
       z = Math.ceil(z.toDouble()).toInt()
   }
   fun ceiled(): Vector3Int {
       var retval = Vector3Int(this)
       retval.x = Math.ceil(x.toDouble()).toInt()
       retval.y = Math.ceil(y.toDouble()).toInt()
       retval.z = Math.ceil(z.toDouble()).toInt()
       return retval
   }
   fun round() {
       x = Math.round(x.toDouble()).toInt()
       y = Math.round(y.toDouble()).toInt()
       z = Math.round(z.toDouble()).toInt()
   }
   fun rounded(): Vector3Int {
       var retval = Vector3Int(this)
       retval.x = Math.round(x.toDouble()).toInt()
       retval.y = Math.round(y.toDouble()).toInt()
       retval.z = Math.round(z.toDouble()).toInt()
       return retval
   }
   fun roundToZero() {
       x = if (x < 0.toInt()) Math.ceil(x.toDouble()).toInt() else Math.floor(x.toDouble()).toInt()
       y = if (y < 0.toInt()) Math.ceil(y.toDouble()).toInt() else Math.floor(y.toDouble()).toInt()
       z = if (z < 0.toInt()) Math.ceil(z.toDouble()).toInt() else Math.floor(z.toDouble()).toInt()
   }
   fun roundedToZero(): Vector3Int {
       var retval = Vector3Int(this)
       retval.x = if (x < 0.toInt()) Math.ceil(x.toDouble()).toInt() else Math.floor(x.toDouble()).toInt()
       retval.y = if (y < 0.toInt()) Math.ceil(y.toDouble()).toInt() else Math.floor(y.toDouble()).toInt()
       retval.z = if (z < 0.toInt()) Math.ceil(z.toDouble()).toInt() else Math.floor(z.toDouble()).toInt()
       return retval
   }
   fun negate() {
       x = - x
       y = - y
       z = - z
   }
   fun negated(): Vector3Int {
       var retval = Vector3Int(this)
       retval.x = - x
       retval.y = - y
       retval.z = - z
       return retval
   }
   fun lengthSquared(): Int {
       return (
           (x * x).toDouble() +
           (y * y).toDouble() +
           (z * z).toDouble() 
       ).toInt()
   }
   fun length(): Int {
       return Math.sqrt(
           (x * x).toDouble() +
           (y * y).toDouble() +
           (z * z).toDouble() 
       ).toInt()
   }
   fun manhattanLength(): Int {
       return (
           Math.abs(x) +
           Math.abs(y) +
           Math.abs(z) 
       )
   }
   fun distanceSquared(to: Vector3Int): Int {
       return (
           (x.distance(to.x) * x.distance(to.x)) +
           (y.distance(to.y) * y.distance(to.y)) +
           (z.distance(to.z) * z.distance(to.z)) 
       )
   }
   fun distance(to: Vector3Int): Int = kotlin.math.sqrt(distanceSquared(to).toDouble()).toInt()
   fun normalize(): Vector3Int {
       val len = length()
       return dividedScalar(if (len <= 0.toInt()) 0.toInt() else len)
   }
   fun setLength(to: Int) {
        normalize()
        multiplyScalar(length())
   }
   fun lerp(v: Vector3Int, alpha: Int) {
           x += (v.x - x) * alpha
           y += (v.y - y) * alpha
           z += (v.z - z) * alpha
   }
   fun lerped(v: Vector3Int, alpha: Int): Vector3Int {
       var retval = Vector3Int(this)
       retval.lerp(v, alpha)
       return retval
   }
   operator fun plus(v: Vector3Int): Vector3Int {
      return this.added(v)
   }
   operator fun minus(v: Vector3Int): Vector3Int {
      return this.subtracted(v)
   }
   operator fun times(v: Vector3Int): Vector3Int {
      return this.multiplied(v)
   }
   operator fun div(v: Vector3Int): Vector3Int {
      return this.divided(v)
   }
}
