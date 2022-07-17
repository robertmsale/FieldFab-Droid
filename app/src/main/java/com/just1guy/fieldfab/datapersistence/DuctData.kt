package com.just1guy.fieldfab.datapersistence


import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import java.util.*

@Serializable
enum class DuctType {
    FOUR_PIECE { override fun text(): String = "4 Piece" },
    TWO_PIECE { override fun text(): String = "2 Piece" },
    UNIBODY { override fun text(): String = "Unibody" };
    abstract fun text(): String
    companion object {
        fun from(value: String): DuctType = when (value) {
            "2 Piece" -> TWO_PIECE
            "Unibody" -> UNIBODY
            else -> FOUR_PIECE
        }
    }
}

@Serializable
data class DuctData (
    val name: String,
    val id: UUID = UUID.randomUUID(),
    val created: Date = Date(),
    val width: Float,
    val depth: Float,
    val length: Float,
    val offsetx: Float,
    val offsety: Float,
    val twidth: Float,
    val tdepth: Float,
) {
}
