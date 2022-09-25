package com.just1guy.fieldfab.math

import com.just1guy.fieldfab.datapersistence.getNormal
import Vector2Float as Vector2
import Vector3Float as Vector3
import org.rajawali3d.Geometry3D
import org.rajawali3d.Object3D

import java.nio.FloatBuffer
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin

class GeometryBuilder(
	var quads: MutableList<Quad> = mutableListOf(),
	var texSize: Vector2 = Vector2(1.0f , 1.0f),
	var uvMode: UVMode = UVMode.STRETCH_XY,
	) {
	enum class UVMode {
		STRETCH_XY, STRETCH_X, STRETCH_Y, SIZE_TO_WORLD_XY, SIZE_TO_WORLD_X,
	}
	fun addQuads(vararg quad: Quad) { quads.addAll(quad) }

	fun getGeometryParts(): GeometryComplete {
		var positions = mutableListOf<Vector3>()
		var normals = mutableListOf<Vector3>()
		var tcoords = mutableListOf<Vector2>()
		var faceIndices = mutableListOf<Int>()

		for (quad in quads) {
			val nvf1 = getNormal(quad.v0, quad.v1, quad.v2)
			val nvf2 = getNormal(quad.v0, quad.v2, quad.v3)

			var uv0: Vector2
			var uv1: Vector2
			var uv2: Vector2
			var uv3: Vector2

			val zf = 0f
			val nf = 1f

			when (uvMode) {
				UVMode.SIZE_TO_WORLD_X -> {
					val lU = max((quad.v1 - quad.v2).length(), (quad.v2 - quad.v3).length())
					val lV = max((quad.v1 - quad.v2).length(), (quad.v2 - quad.v3).length())
					uv0 = Vector2(lU,lV)
					uv1 = Vector2(zf,lV)
					uv2 = Vector2(zf,zf)
					uv3 = Vector2(lU,zf)
				}
				UVMode.SIZE_TO_WORLD_XY -> {
					val v20 = quad.v0 - quad.v2
					val v21 = quad.v1 - quad.v2
					val v23 = quad.v3 - quad.v2
					val v20mag = v20.length()
					val v21mag = v21.length()
					val v23mag = v23.length()
					val v0angl = v23.angle(v20)
					val v1angl = v23.angle(v21)
					uv0 = Vector2((cos(v0angl) * v20mag), (sin(v0angl) * v20mag))
					uv1 = Vector2((cos(v1angl) * v21mag), (sin(v1angl) * v21mag))
					uv2 = Vector2(zf,zf)
					uv3 = Vector2(v23mag,zf)
				}
				else -> {
					uv0 = Vector2(nf,nf)
					uv1 = Vector2(zf,nf)
					uv2 = Vector2(zf,zf)
					uv3 = Vector2(nf,zf)
				}
			}
			val v0norm = nvf1 + nvf2
			val v2norm = nvf1 + nvf2

			positions.add(quad.v0)
			normals.add(v0norm.normalize())
			tcoords.add(uv0)

			positions.add(quad.v1)
			normals.add(nvf1.normalize())
			tcoords.add(uv1)

			positions.add(quad.v2)
			normals.add(v2norm.normalize())
			tcoords.add(uv2)

			positions.add(quad.v3)
			normals.add(nvf2.normalize())
			tcoords.add(uv3)

			faceIndices.add(positions.size-4)
			faceIndices.add(positions.size-3)
			faceIndices.add(positions.size-2)

			faceIndices.add(positions.size-4)
			faceIndices.add(positions.size-2)
			faceIndices.add(positions.size-1)
		}
		return GeometryComplete(positions, normals, tcoords, faceIndices)
	}
}

data class GeometryComplete(
	val vertices: List<Vector3>,
	val normals: List<Vector3>,
	val tcoords: List<Vector2>,
	val faceIndices: List<Int>
) {
	companion object {
		fun flatMapVec3s(of: List<Vector3>): FloatArray {
			return (of.flatMap { listOf(it.x, it.y, it.z).map {it.toFloat()} }.toFloatArray())
		}
		fun flatMapVec2s(of: List<Vector2>): FloatArray {
			return (of.flatMap { listOf(it.x, it.y).map {it.toFloat()} }.toFloatArray())
		}

	}
	val geometry: Object3D get() {
		val o = Object3D()
		o.setData(
			flatMapVec3s(vertices),
			flatMapVec3s(normals),
			flatMapVec2s(tcoords),
			FloatArray(tcoords.size*2) { 1f },
			faceIndices.toIntArray(),
			(true)
		)
		return o
	}
}
