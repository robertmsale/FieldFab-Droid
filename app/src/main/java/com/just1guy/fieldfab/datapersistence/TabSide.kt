package com.just1guy.fieldfab.datapersistence

enum class TabSide(val side: Int) {
    TOP(0) {override fun sideName(): String {return "Top"}},
    BOTTOM(1) {override fun sideName(): String {return "Bottom"}},
    LEFT(2) {override fun sideName(): String {return "Left"}},
    RIGHT(3) {override fun sideName(): String {return "Right"}};

    abstract fun sideName(): String
}