package com.just1guy.fieldfab.datapersistence

/**
 *
 */
data class DimensionData(
    val length: Double,
    val width: Double,
    val depth: Double,
    val offsetX: Double,
    val offsetY: Double,
    val tWidth: Double,
    val tDepth: Double
) {
    /// Static factory method
    companion object {
        fun from(data: DimensionTabData): DimensionData {
            return DimensionData(data.length,data.width,data.depth,data.offsetX,data.offsetY,data.tWidth,data.tDepth)
        }
    }
}