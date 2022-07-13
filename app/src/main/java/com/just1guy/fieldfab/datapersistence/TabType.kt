package com.just1guy.fieldfab.datapersistence

enum class TabType(val t: Int) {
    STRAIGHT(0) { override fun toText(): String {return "Straight"}},
    TAPERED(1) { override fun toText(): String {return "Tapered"}},
    DRIVE(2) { override fun toText(): String {return "Drive"}},
    FOLDIN(3) { override fun toText(): String {return "Fold In"}},
    FOLDOUT(4) { override fun toText(): String {return "Fold Out"}},
    SLOCK(5) { override fun toText(): String {return "S-Lock"}},
    NONE(6) { override fun toText(): String {return "None"}};
    abstract fun toText(): String
}