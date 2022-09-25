package com.just1guy.fieldfab.datapersistence

import kotlinx.serialization.Serializable

@Serializable
data class TabData(
    var type: Int = 6,
    var length: Float = 0.0F
    ) {
    constructor(t: TabType, l: TabLength): this() {
        storedType = t
        storedLength = l
    }
    var storedType: TabType
        get() = when (type) {
            0 -> TabType.STRAIGHT
            1 -> TabType.TAPERED
            2 -> TabType.DRIVE
            3 -> TabType.FOLDIN
            4 -> TabType.FOLDOUT
            5 -> TabType.SLOCK
            else -> TabType.NONE
        }
        set(value) {
            when (value) {
                TabType.STRAIGHT -> type = 0
                TabType.TAPERED -> type = 1
                TabType.DRIVE -> type = 2
                TabType.FOLDIN -> type = 3
                TabType.FOLDOUT -> type = 4
                TabType.SLOCK -> type = 5
                TabType.NONE -> type = 6
            }
        }
    var storedLength: TabLength
        get() = if ((0.0253F..0.0255F).contains(length)) TabLength.INCH
        else if ((0.0126F..0.0128F).contains(length)) TabLength.HALF
        else if ((0.009524F..0.009526F).contains(length)) TabLength.THREEEIGHTH
        else TabLength.NONE
        set(value) = when (value) {
            TabLength.INCH -> length = 0.0254F
            TabLength.HALF -> length = 0.0127F
            TabLength.THREEEIGHTH -> length = 0.009525F
            TabLength.NONE -> length = 0.0F
        }
    val lengthAsAlpha: Float
        get() = when (storedLength) {
            TabLength.INCH -> 1F
            TabLength.HALF -> .5F
            TabLength.THREEEIGHTH -> .375F
            TabLength.NONE -> 0F
        }

    companion object {
        fun getType(of: TabData): TabType {

            return when (of.type) {
                0 -> TabType.STRAIGHT
                1 -> TabType.TAPERED
                2 -> TabType.DRIVE
                3 -> TabType.FOLDIN
                4 -> TabType.FOLDOUT
                5 -> TabType.SLOCK
                else -> TabType.NONE
            }
        }
        fun getLength(of: TabData): TabLength =
            if ((0.0253F..0.0255F).contains(of.length)) TabLength.INCH
            else if ((0.0126F..0.0128F).contains(of.length)) TabLength.HALF
            else if ((0.009524F..0.009526F).contains(of.length)) TabLength.THREEEIGHTH
            else TabLength.NONE
    }
}
