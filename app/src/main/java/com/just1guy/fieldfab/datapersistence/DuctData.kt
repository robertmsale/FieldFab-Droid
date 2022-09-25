package com.just1guy.fieldfab.datapersistence


import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import java.util.*
//import javax.persistence.Entity
//import javax.persistence.GeneratedValue
//import javax.persistence.Id

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
	val id: Long? = null,
	val created: Instant = Clock.System.now(),
	val width: Float,
	val depth: Float,
	val length: Float,
	val offsetx: Float,
	val offsety: Float,
	val twidth: Float,
	val tdepth: Float,
	val tabsData: TabsData = TabsData()
) {
}
