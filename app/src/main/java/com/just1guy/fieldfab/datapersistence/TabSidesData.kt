package com.just1guy.fieldfab.datapersistence

import kotlinx.serialization.Serializable

@Serializable
data class TabSidesData(
    var top: TabData = TabData(),
    var bottom: TabData = TabData(),
    var left: TabData = TabData(),
    var right: TabData = TabData()) {
    operator fun get(side: TabSide): TabData {
        return when (side) {
            TabSide.TOP -> top
            TabSide.BOTTOM -> bottom
            TabSide.LEFT -> left
            TabSide.RIGHT -> right
        }
    }
    operator fun set(side: TabSide, to: TabData) {
        when (side) {
            TabSide.TOP -> top = to
            TabSide.BOTTOM -> bottom = to
            TabSide.LEFT -> left = to
            TabSide.RIGHT -> right = to
        }
    }
    operator fun get(side: String): TabData {
        return when (side) {
            "t", "top", "Top" -> top
            "b", "bottom", "Bottom" -> bottom
            "l", "left", "Left" -> left
            else -> right
        }
    }
    operator fun set(side: String, to: TabData) {
        when (side) {
            "t", "top", "Top" -> top = to
            "b", "bottom", "Bottom" -> bottom = to
            "l", "left", "Left" -> left = to
            "r", "right", "Right" -> right = to
        }
    }
}
