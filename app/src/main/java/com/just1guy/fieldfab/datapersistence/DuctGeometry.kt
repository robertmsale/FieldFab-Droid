package com.just1guy.fieldfab.datapersistence

import Vector2
import com.just1guy.fieldfab.math.Quad
import com.just1guy.fieldfab.math.angle
import com.just1guy.fieldfab.math.crossed
import java.lang.Math.*

enum class UVMode { STRETCH_XY, STRETCH_X, STRETCH_Y, SIZE_TO_WORLD_XY, SIZE_TO_WORLD_X }

fun getNormal(v0: V3, v1: V3, v2: V3): V3 {
    val edgev0v1 = v1.subtracted(v0)
    val edgev1v2 = v2.subtracted(v1)

    return edgev0v1.crossed(edgev1v2)
}

class GeometryBuilder(
    val quads: List<Quad>,
    val texSize: Vector2 = Vector2(1F, 1F),
    val uvMode: UVMode = UVMode.STRETCH_XY,
) {
    fun getGeometryParts(): GeometryComplete {
        var positions: List<V3> = listOf()
        var normals: List<V3> = listOf()
        var tcoords: List<Vector2> = listOf()
        var faceIndices: List<UShort> = listOf()

        for (quad in quads) {
            val nvf1 = getNormal(quad.v0, quad.v1, quad.v2)
            val nvf2 = getNormal(quad.v0, quad.v2, quad.v3)

            var uv0 = Vector2(0F, 0F)
            var uv1 = Vector2(0F, 0F)
            var uv2 = Vector2(0F, 0F)
            var uv3 = Vector2(0F, 0F)

            val zero = 0F

            when (uvMode) {
                UVMode.SIZE_TO_WORLD_X -> {
                    val longestUEdge = kotlin.math.max(
                        (quad.v1.subtracted(quad.v0)).length(),
                        (quad.v2.subtracted(quad.v3)).length()
                    )
                    val longestVEdge = kotlin.math.max(
                        (quad.v1.subtracted(quad.v2)).length(),
                        (quad.v0.subtracted(quad.v3)).length()
                    )
                    uv0 = Vector2(longestUEdge, longestVEdge)
                    uv1 = Vector2(zero, longestVEdge)
                    uv2 = Vector2(zero, zero)
                    uv3 = Vector2(longestUEdge, zero)
                }
                UVMode.SIZE_TO_WORLD_XY -> {
                    val v2v0 = quad.v0.subtracted(quad.v2)
                    val v2v1 = quad.v1.subtracted(quad.v2)
                    val v2v3 = quad.v3.subtracted(quad.v2)

                    val v2v0Mag = v2v0.length()
                    val v2v1Mag = v2v1.length()
                    val v2v3Mag = v2v3.length()

                    val v0angle = v2v3.angle(to = v2v0)
                    val v1angle = v2v3.angle(to = v2v1)

                    uv0 = Vector2(x = kotlin.math.cos(v0angle) * v2v0Mag, y = kotlin.math.sin(v0angle) * v2v0Mag)
                    uv1 = Vector2(x = kotlin.math.cos(v1angle) * v2v1Mag, y = kotlin.math.sin(v1angle) * v2v1Mag)
                    uv2 = Vector2(x = zero, y = zero)
                    uv3 = Vector2(x = v2v3Mag, y = zero)
                }
                else -> {
                    uv0 = Vector2(1F, 1F)
                    uv1 = Vector2(0F, 1F)
                    uv2 = Vector2(0F, 0F)
                    uv3 = Vector2(1F, 0F)
                }
            }

            val v0norm = nvf1.added(nvf2)
            val v2norm = nvf1.added(nvf2)

            positions = positions.plus(listOf(quad.v0, quad.v1, quad.v2, quad.v3))
            normals = normals.plus(listOf(v0norm.normalize(), nvf1.normalize(), v2norm.normalize(), nvf2.normalize()))
            tcoords = tcoords.plus(listOf(uv0, uv1, uv2, uv3))
            faceIndices = faceIndices.plus( listOf(
                (positions.count()-4).toUShort(),
                (positions.count()-3).toUShort(),
                (positions.count()-2).toUShort(),
                (positions.count()-4).toUShort(),
                (positions.count()-2).toUShort(),
                (positions.count()-1).toUShort(),
            ))
        }
        return GeometryComplete(positions, normals, tcoords, faceIndices)
    }
}

data class GeometryComplete(
    val vertices: List<V3>,
    val normals: List<V3>,
    val tcoords: List<Vector2>,
    val faceIndices: List<UShort>,
) {

}
