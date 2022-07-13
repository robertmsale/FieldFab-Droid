package com.just1guy.fieldfab.datapersistence

data class TabsData(
    var front: TabSidesData,
    var back: TabSidesData,
    var left: TabSidesData,
    var right: TabSidesData
) {
    companion object {
        fun toURL(from: TabsData): String {
            TODO()
        }
    }
}
