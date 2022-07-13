package com.just1guy.fieldfab.datapersistence

/**
 * The duct data which is immediately deserialized
 */
data class DimensionTabData(
    val name: String,
    val createdOn: String,
    /// TODO: Make TabData Class
    val tabs: Boolean,
    val length: Double,
    val width: Double,
    val depth: Double,
    val offsetX: Double,
    val offsetY: Double,
    val tWidth: Double,
    val tDepth: Double
)
