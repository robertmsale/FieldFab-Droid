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

abstract class VectorProtocol<T: Number> {
    abstract operator fun get(axis: Axis): T
    abstract operator fun set(axis: Axis, value: T)
    abstract fun copy(): VectorProtocol<T>
    abstract fun getAxes(): List<Axis>

//    fun add(scalar: T) = getAxes().forEach { axis -> this[axis] = this[axis] + scalar }
}
