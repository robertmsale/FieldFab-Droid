package com.just1guy.fieldfab.datapersistence

data class TabSidesData(var top: TabData, var bottom: TabData, var left: TabData, var right: TabData) {
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
}
