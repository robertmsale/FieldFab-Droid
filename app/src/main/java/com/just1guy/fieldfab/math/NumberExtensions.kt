package com.just1guy.fieldfab.math

fun Number.add(other: Number): Number = when (this) {
    is Byte -> when (other) {
        is Byte -> this + other
        is Short -> this + other.toByte()
        is Int -> this + other.toByte()
        is Long -> this + other.toByte()
        is Float -> this + other.toByte()
        is Double -> this + other.toByte()
        else -> this
    }
    is Short -> when (other) {
        is Byte -> this + other.toShort()
        is Short -> this + other
        is Int -> this + other.toShort()
        is Long -> this + other.toShort()
        is Float -> this + other.toShort()
        is Double -> this + other.toShort()
        else -> this
    }
    is Int -> when (other) {
        is Byte -> this + other.toInt()
        is Short -> this + other.toInt()
        is Int -> this + other
        is Long -> this + other.toInt()
        is Float -> this + other.toInt()
        is Double -> this + other.toInt()
        else -> this
    }
    is Long -> when (other) {
        is Byte -> this + other.toLong()
        is Short -> this + other.toLong()
        is Int -> this + other.toLong()
        is Long -> this + other
        is Float -> this + other.toLong()
        is Double -> this + other.toLong()
        else -> this
    }
    is Float -> when (other) {
        is Byte -> this + other.toFloat()
        is Short -> this + other.toFloat()
        is Int -> this + other.toFloat()
        is Long -> this + other.toFloat()
        is Float -> this + other
        is Double -> this + other.toFloat()
        else -> this
    }
    is Double -> when (other) {
        is Byte -> this + other.toDouble()
        is Short -> this + other.toDouble()
        is Int -> this + other.toDouble()
        is Long -> this + other.toDouble()
        is Float -> this + other.toDouble()
        is Double -> this + other
        else -> this}
    else -> this
}

fun Number.subtract(other: Number): Number = when (this) {
    is Byte -> when (other) {
        is Byte -> this - other
        is Short -> this - other.toByte()
        is Int -> this - other.toByte()
        is Long -> this - other.toByte()
        is Float -> this - other.toByte()
        is Double -> this - other.toByte()
        else -> this
    }
    is Short -> when (other) {
        is Byte -> this - other.toShort()
        is Short -> this - other
        is Int -> this - other.toShort()
        is Long -> this - other.toShort()
        is Float -> this - other.toShort()
        is Double -> this - other.toShort()
        else -> this
    }
    is Int -> when (other) {
        is Byte -> this - other.toInt()
        is Short -> this - other.toInt()
        is Int -> this - other
        is Long -> this - other.toInt()
        is Float -> this - other.toInt()
        is Double -> this - other.toInt()
        else -> this
    }
    is Long -> when (other) {
        is Byte -> this - other.toLong()
        is Short -> this - other.toLong()
        is Int -> this - other.toLong()
        is Long -> this - other
        is Float -> this - other.toLong()
        is Double -> this - other.toLong()
        else -> this
    }
    is Float -> when (other) {
        is Byte -> this - other.toFloat()
        is Short -> this - other.toFloat()
        is Int -> this - other.toFloat()
        is Long -> this - other.toFloat()
        is Float -> this - other
        is Double -> this - other.toFloat()
        else -> this
    }
    is Double -> when (other) {
        is Byte -> this - other.toDouble()
        is Short -> this - other.toDouble()
        is Int -> this - other.toDouble()
        is Long -> this - other.toDouble()
        is Float -> this - other.toDouble()
        is Double -> this - other
        else -> this}
    else -> this
}

fun Number.multiply(other: Number): Number = when (this) {
    is Byte -> when (other) {
        is Byte -> this * other
        is Short -> this * other.toByte()
        is Int -> this * other.toByte()
        is Long -> this * other.toByte()
        is Float -> this * other.toByte()
        is Double -> this * other.toByte()
        else -> this
    }
    is Short -> when (other) {
        is Byte -> this * other.toShort()
        is Short -> this * other
        is Int -> this * other.toShort()
        is Long -> this * other.toShort()
        is Float -> this * other.toShort()
        is Double -> this * other.toShort()
        else -> this
    }
    is Int -> when (other) {
        is Byte -> this * other.toInt()
        is Short -> this * other.toInt()
        is Int -> this * other
        is Long -> this * other.toInt()
        is Float -> this * other.toInt()
        is Double -> this * other.toInt()
        else -> this
    }
    is Long -> when (other) {
        is Byte -> this * other.toLong()
        is Short -> this * other.toLong()
        is Int -> this * other.toLong()
        is Long -> this * other
        is Float -> this * other.toLong()
        is Double -> this * other.toLong()
        else -> this
    }
    is Float -> when (other) {
        is Byte -> this * other.toFloat()
        is Short -> this * other.toFloat()
        is Int -> this * other.toFloat()
        is Long -> this * other.toFloat()
        is Float -> this * other
        is Double -> this * other.toFloat()
        else -> this
    }
    is Double -> when (other) {
        is Byte -> this * other.toDouble()
        is Short -> this * other.toDouble()
        is Int -> this * other.toDouble()
        is Long -> this * other.toDouble()
        is Float -> this * other.toDouble()
        is Double -> this * other
        else -> this}
    else -> this
}

fun Number.divide(other: Number): Number = when (this) {
    is Byte -> when (other) {
        is Byte -> this / other
        is Short -> this / other.toByte()
        is Int -> this / other.toByte()
        is Long -> this / other.toByte()
        is Float -> this / other.toByte()
        is Double -> this / other.toByte()
        else -> this
    }
    is Short -> when (other) {
        is Byte -> this / other.toShort()
        is Short -> this / other
        is Int -> this / other.toShort()
        is Long -> this / other.toShort()
        is Float -> this / other.toShort()
        is Double -> this / other.toShort()
        else -> this
    }
    is Int -> when (other) {
        is Byte -> this / other.toInt()
        is Short -> this / other.toInt()
        is Int -> this / other
        is Long -> this / other.toInt()
        is Float -> this / other.toInt()
        is Double -> this / other.toInt()
        else -> this
    }
    is Long -> when (other) {
        is Byte -> this / other.toLong()
        is Short -> this / other.toLong()
        is Int -> this / other.toLong()
        is Long -> this / other
        is Float -> this / other.toLong()
        is Double -> this / other.toLong()
        else -> this
    }
    is Float -> when (other) {
        is Byte -> this / other.toFloat()
        is Short -> this / other.toFloat()
        is Int -> this / other.toFloat()
        is Long -> this / other.toFloat()
        is Float -> this / other
        is Double -> this / other.toFloat()
        else -> this
    }
    is Double -> when (other) {
        is Byte -> this / other.toDouble()
        is Short -> this / other.toDouble()
        is Int -> this / other.toDouble()
        is Long -> this / other.toDouble()
        is Float -> this / other.toDouble()
        is Double -> this / other
        else -> this}
    else -> this
}