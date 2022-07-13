package com.just1guy.fieldfab.datapersistence

data class TabData(var type: Int, var length: Double) {
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
        fun getLength(of: TabData): TabLength {
            return when (of.length) {
                1.0 -> TabLength.INCH
                0.5 -> TabLength.HALF
                0.375 -> TabLength.THREEEIGHTH
                else -> TabLength.NONE
            }
        }
    }
}
