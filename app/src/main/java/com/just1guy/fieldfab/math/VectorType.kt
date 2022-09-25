package com.just1guy.fieldfab.math

abstract class Axis {
    class X: Axis()
    class Y: Axis()
    class Z: Axis()
    class W: Axis()

    companion object {
        val x: X = X()
        val y: Y = Y()
        val z: Z = Z()
        val w: W = W()
    }
}

interface VectorProtocol<T: Number> {
    var values: List<T>
    operator fun get(axis: Axis): T
    operator fun set(axis: Axis, value: T)
    fun copy(): VectorProtocol<T>
    fun getAxes(): List<Axis>

    fun add(scalar: T)
    fun added(scalar: T): VectorProtocol<T>
    fun add(vec: VectorProtocol<T>)
    fun added(vec: VectorProtocol<T>): VectorProtocol<T>

    fun sub(scalar: T)
    fun subbed(scalar: T): VectorProtocol<T>
    fun sub(vec: VectorProtocol<T>)
    fun subbed(vec: VectorProtocol<T>): VectorProtocol<T>

    fun multiply(scalar: T)
    fun multiplied(scalar: T): VectorProtocol<T>
    fun multiply(vec: VectorProtocol<T>)
    fun multiplied(vec: VectorProtocol<T>): VectorProtocol<T>

    fun divide(scalar: T)
    fun divided(scalar: T): VectorProtocol<T>
    fun divide(vec: VectorProtocol<T>)
    fun divided(vec: VectorProtocol<T>): VectorProtocol<T>

    fun zero()
    fun zero(axes: Set<String>)
    fun zero(axes: List<Axis>)

    fun zeroed(): VectorProtocol<T>
    fun zeroed(axes: Set<String>): VectorProtocol<T>
    fun zeroed(axes: List<Axis>): VectorProtocol<T>

    fun clamp(min: VectorProtocol<T>, max: VectorProtocol<T>)
    fun clamped(min: VectorProtocol<T>, max: VectorProtocol<T>): VectorProtocol<T>

    fun floor()
    fun floored(): VectorProtocol<T>

    fun ceil()
    fun ceiled(): VectorProtocol<T>

    fun round()
    fun rounded(): VectorProtocol<T>

    fun roundToZero()
    fun roundedToZero(): VectorProtocol<T>

    fun negate()
    fun negated(): VectorProtocol<T>

    fun lengthSquared(): T
    fun length(): T
    fun manhattanLength(): T

    fun distanceSquared(to: VectorProtocol<T>): T
    fun distance(to: VectorProtocol<T>): T
    fun normalize(): VectorProtocol<T>

    fun setLength(to: T)
    fun withLengthSet(to: T): VectorProtocol<T>

    fun lerp(to: VectorProtocol<T>, alpha: T)
    fun lerped(to: VectorProtocol<T>, alpha: T): VectorProtocol<T>

    operator fun plus(scalar: T): VectorProtocol<T>
    operator fun plus(vec: VectorProtocol<T>): VectorProtocol<T>
    operator fun minus(scalar: T): VectorProtocol<T>
    operator fun minus(vec: VectorProtocol<T>): VectorProtocol<T>
    operator fun times(scalar: T): VectorProtocol<T>
    operator fun times(vec: VectorProtocol<T>): VectorProtocol<T>
    operator fun div(scalar: T): VectorProtocol<T>
    operator fun div(vec: VectorProtocol<T>): VectorProtocol<T>
    operator fun plusAssign(scalar: T)
    operator fun plusAssign(vec: VectorProtocol<T>)
    operator fun minusAssign(scalar: T)
    operator fun minusAssign(vec: VectorProtocol<T>)
    operator fun timesAssign(scalar: T)
    operator fun timesAssign(vec: VectorProtocol<T>)
    operator fun divAssign(scalar: T)
    operator fun divAssign(vec: VectorProtocol<T>)
}
