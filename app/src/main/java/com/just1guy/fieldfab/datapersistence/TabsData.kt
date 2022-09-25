package com.just1guy.fieldfab.datapersistence

import kotlinx.serialization.Serializable

@Serializable
data class TabsData(
    var front: TabSidesData = TabSidesData(),
    var back: TabSidesData = TabSidesData(),
    var left: TabSidesData = TabSidesData(),
    var right: TabSidesData = TabSidesData()
) {
    operator fun get(edge: String): TabSidesData = when (edge) {
        "f", "front", "Front" -> front
        "b", "back", "Back" -> back
        "l", "left", "Left" -> left
        else -> right
    }
    operator fun set(edge: String, value: TabSidesData) = when (edge) {
        "f", "front", "Front" -> front = value
        "b", "back", "Back" -> back = value
        "l", "left", "Left" -> left = value
        else -> right = value
    }
    companion object {
        fun toURL(from: TabsData): String {
            TODO()
        }
    }
}
