package com.just1guy.fieldfab.math

import com.just1guy.fieldfab.datapersistence.Duct
import com.just1guy.fieldfab.datapersistence.Duct.Companion.GAUGE
import Vector3Float as V3
import Vector2Float as V2
import com.just1guy.fieldfab.datapersistence.DuctFace

class FaceGeometry {
	companion object {
		fun getFaceVerts(p: Duct.Perspective, f: DuctFace): List<Int> {
			var indices: List<Duct.FaceIndices> = listOf()
			if (p == Duct.Perspective.OUTER) {
				indices = when (f) {
					DuctFace.FRONT -> listOf(Duct.FaceIndices.FTR, Duct.FaceIndices.FTL, Duct.FaceIndices.FBL, Duct.FaceIndices.FBR)
					DuctFace.BACK -> listOf(Duct.FaceIndices.BTR, Duct.FaceIndices.BTL, Duct.FaceIndices.BBL, Duct.FaceIndices.BBR)
					DuctFace.LEFT -> listOf(Duct.FaceIndices.LTR, Duct.FaceIndices.LTL, Duct.FaceIndices.LBL, Duct.FaceIndices.LBR)
					DuctFace.RIGHT -> listOf(Duct.FaceIndices.RTR, Duct.FaceIndices.RTL, Duct.FaceIndices.RBL, Duct.FaceIndices.RBR)
				}
			} else {
				indices = when (f) {
					DuctFace.FRONT -> listOf(Duct.FaceIndices.FTL, Duct.FaceIndices.FTR, Duct.FaceIndices.FBR, Duct.FaceIndices.FBL)
					DuctFace.BACK -> listOf(Duct.FaceIndices.BTL, Duct.FaceIndices.BTR, Duct.FaceIndices.BBR, Duct.FaceIndices.BBL)
					DuctFace.LEFT -> listOf(Duct.FaceIndices.LTL, Duct.FaceIndices.LTR, Duct.FaceIndices.LBR, Duct.FaceIndices.LBL)
					DuctFace.RIGHT -> listOf(Duct.FaceIndices.RTL, Duct.FaceIndices.RTR, Duct.FaceIndices.RBR, Duct.FaceIndices.RBL)
				}
			}
			return indices.map { Duct.getFaceIndex(it) }
		}
		fun generate(face: DuctFace, outerVerts: List<V3>, crossBrake: Boolean): GeometryComplete {
			val xx = V3(x=1f)
			val zz = V3(z=1f)
			val geo: GeometryComplete?
			val ov = outerVerts
			val f = face
			val iv = outerVerts.map { v ->
				V3(
					v.x - (if (v.x < 0) -Duct.GAUGE else Duct.GAUGE),
					v.y,
					v.z - (if (v.z < 0) -Duct.GAUGE else Duct.GAUGE)
				)
			}
			val id = getFaceVerts(Duct.Perspective.OUTER, face)
			if (crossBrake) {
				val cbdist = 0.004075f
				val oc = {
					val t = ov[id[1]].lerped(ov[id[0]], 0.5f)
					val b = ov[id[3]].lerped(ov[id[2]], 0.5f)
					val c = t.lerped(b, 0.5f)
					val axis = if (f == DuctFace.FRONT || f == DuctFace.BACK) zz else xx
					val mag = V3(cbdist, cbdist, cbdist)
					if (f == DuctFace.FRONT || f == DuctFace.RIGHT) mag.negate()
					c.added(axis).multiplied(mag)
				}()
				val ic = {
					val axis = if (f == DuctFace.FRONT || f == DuctFace.BACK) zz else xx
					val mag = V3(GAUGE, GAUGE, GAUGE)
					if (f == DuctFace.FRONT || f == DuctFace.RIGHT) mag.negate()
					oc.added(axis).multiplied(mag)
				}()
				val verts = listOf(
					ov[id[0]], ov[id[1]], ov[id[2]], ov[id[3]], oc,
					iv[id[0]], iv[id[1]], iv[id[2]], iv[id[3]], ic,
				)
				val indices = listOf(
					// Outer
					0, 1, 4,
					1, 2, 4,
					2, 3, 4,
					3, 0, 4,
					// Inner
					6, 5, 9,
					7, 6, 9,
					8, 7, 9,
					5, 8, 9,
				)
				val positions: List<V3> = {
					val p = mutableListOf<V3>()
					for (i in 0..7) {
						val ii = i*3
						for (j in ii..ii+2) {
							p.add(verts[indices[j]])
						}
					}
					p.addAll(listOf(
						verts[5], verts[6], verts[1],
						verts[5], verts[1], verts[0],
						verts[3], verts[2], verts[7],
						verts[3], verts[7], verts[8],
					))
					p
				}()
				val normals: List<V3> = {
					val n = mutableListOf<V3>()
					for (i in 0..7) {
						val ii: Int = i*3
						val h01 = positions[ii].lerped(positions[ii+1], 0.5f)
						val c = (h01.lerped(positions[ii+2], 0.5f)).normalize()
						n.addAll(listOf(c, c, c))
					}
					val t = (V3(0f, 1f, 0f)).normalize()
					val b = (V3(0f, 1f, 0f)).normalize()
					n.addAll(listOf(
							t, t, t, t, t, t,
							b, b, b, b, b, b,
					))
					n
				}()
				val uv: List<V2> = {
					val p = mutableListOf<V2>()
					val tl = V2(0f, 1f)
					val tr = V2(1f, 1f)
					val bl = V2(0f, 0f)
					val br = V2(1f, 0f)
					val c = V2(0.5f, 0.5f)
					p.addAll(listOf(
							tr, tl, c,
							tl, bl, c,
							bl, br, c,
							br, tr, c,

							tl, tr, c,
							bl, tl, c,
							br, bl, c,
							tr, br, c,

							tr, tl, bl,
							tr, bl, br,
							tr, tl, bl,
							tr, bl, br,

						))
					p
				}()
				val idcfinal = mutableListOf<Int>()
				for (i in 0..35) idcfinal.add(i)
				geo = GeometryComplete(positions, normals, uv, idcfinal)
			} else {
				val verts = listOf(
					ov[id[0]], ov[id[1]], ov[id[2]], ov[id[3]],
					iv[id[0]], iv[id[1]], iv[id[2]], iv[id[3]],
				)
				geo = GeometryBuilder(mutableListOf(
					Quad(verts[0], verts[1], verts[2], verts[3]),
					Quad(verts[5], verts[4], verts[7], verts[6]),
					Quad(verts[4], verts[5], verts[6], verts[0]),
					Quad(verts[3], verts[2], verts[1], verts[7]),
				)).getGeometryParts()
			}
			return geo
		}
	}
}
