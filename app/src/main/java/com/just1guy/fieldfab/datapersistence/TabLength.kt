package com.just1guy.fieldfab.datapersistence

enum class TabLength(val len: Double) {
    INCH(1.0){
        override fun asText(): String {
            return "Inch"
        }

        override fun asURL(): Int {
            return 3
        }
             },
    HALF(0.5){
        override fun asText(): String {
            return "Half"
        }

        override fun asURL(): Int {
            return 2
        }
             },
    THREEEIGHTH(0.375){
        override fun asText(): String {
            return "Three Eighth"
        }

        override fun asURL(): Int {
            return 1
        }
                      },
    NONE(0.0){
        override fun asText(): String {
            return "None"
        }

        override fun asURL(): Int {
            return 0
        }
    };

    abstract fun asText(): String
    abstract fun asURL(): Int
}