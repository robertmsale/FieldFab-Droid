package com.just1guy.fieldfab.datapersistence

import Vector3Float
import com.just1guy.fieldfab.math.DuctFaceMeasure
import com.just1guy.fieldfab.math.LengthMeasurement
import kotlinx.serialization.Serializable
import kotlin.math.abs
import kotlin.math.max

enum class PerspectiveV3 {
    FTL { override fun raw(): RawV3 = RawV3.FTL },
    FTR { override fun raw(): RawV3 = RawV3.FTR },
    FBL { override fun raw(): RawV3 = RawV3.FBL },
    FBR { override fun raw(): RawV3 = RawV3.FBR },
    BTL { override fun raw(): RawV3 = RawV3.BTR },
    BTR { override fun raw(): RawV3 = RawV3.BTL },
    BBL { override fun raw(): RawV3 = RawV3.BBR },
    BBR { override fun raw(): RawV3 = RawV3.BBL },
    LTL { override fun raw(): RawV3 = RawV3.BTL },
    LTR { override fun raw(): RawV3 = RawV3.FTL },
    LBL { override fun raw(): RawV3 = RawV3.BBL },
    LBR { override fun raw(): RawV3 = RawV3.FBL },
    RTL { override fun raw(): RawV3 = RawV3.FTR },
    RTR { override fun raw(): RawV3 = RawV3.BTR },
    RBL { override fun raw(): RawV3 = RawV3.FBR },
    RBR { override fun raw(): RawV3 = RawV3.BBR };
    abstract fun raw(): RawV3
}

enum class RawV3 {
    FTL,
    FTR,
    FBL,
    FBR,
    BTL,
    BTR,
    BBL,
    BBR;
}

@Serializable
data class DuctCoordinates(
    var ftl: Vector3Float,
    var ftr: Vector3Float,
    val fbl: Vector3Float,
    var fbr: Vector3Float,
    var btl: Vector3Float,
    var btr: Vector3Float,
    var bbl: Vector3Float,
    var bbr: Vector3Float,
) {
    constructor(array: List<Vector3Float>) : this(
        array[0],
        array[1],
        array[2],
        array[3],
        array[4],
        array[5],
        array[6],
        array[7],
    ) {}
    operator fun get(c: PerspectiveV3): Vector3Float {
        return when (c.raw()) {
            RawV3.FTL -> ftl
            RawV3.FTR -> ftr
            RawV3.FBL -> fbl
            RawV3.FBR -> fbr
            RawV3.BTL -> btl
            RawV3.BTR -> btr
            RawV3.BBL -> bbl
            RawV3.BBR -> bbr
        }
    }
	val asList: List<Vector3Float> get() = listOf(ftl, ftr, fbl, fbr, btl, btr, bbl, bbr)
}

enum class UnitsOfMeasure {
    INCHES,FEET,METERS,CENTIMETERS,MILLIMETERS
}

@Serializable
data class DuctFaces (
    val front: DuctFaceMeasure,
    val back: DuctFaceMeasure,
    val left: DuctFaceMeasure,
    val right: DuctFaceMeasure,
) {
    operator fun get(face: String): DuctFaceMeasure =
        when (face) {
            "Front" -> front
            "Back" -> back
            "Left" -> left
            else -> right
        }

}

enum class DuctMeasurement {
    WIDTH, DEPTH, LENGTH, OFFSET_X, OFFSET_Y, T_WIDTH, T_DEPTH
}

@Serializable
data class Duct(
    val data: DuctData,
    val outer: DuctCoordinates,
    val inner: DuctCoordinates,
    val measurements: DuctFaces,
) {
    companion object {
        val tabNodeNames = listOf("tab-front-left", "tab-front-right", "tab-front-top", "tab-front-bottom", "tab-left-left", "tab-left-right", "tab-left-top", "tab-left-bottom", "tab-right-left", "tab-right-right", "tab-right-top", "tab-right-bottom", "tab-back-left", "tab-back-right", "tab-back-top", "tab-back-bottom")
        val ductNodeNames = listOf("Front", "Back", "Left", "Right")
        val allNodeNames = listOf("tab-front-left", "tab-front-right", "tab-front-top", "tab-front-bottom", "tab-left-left", "tab-left-right", "tab-left-top", "tab-left-bottom", "tab-right-left", "tab-right-right", "tab-right-top", "tab-right-bottom", "tab-back-left", "tab-back-right", "tab-back-top", "tab-back-bottom", "Front", "Back", "Left", "Right")
        val GAUGE: Float get() = 0.00079375F
        fun make(data: DuctData): Duct {
            val w = data.width
            val d = data.depth
            val l = data.length
            val x = data.offsetx
            val y = data.offsety
            val tw = data.twidth
            val td = data.tdepth
            val o: DuctCoordinates = {
                val fbl = Vector3Float(  -w  / 2,  -l / 2,   d  / 2  )
                val fbr = Vector3Float(   w  / 2,  -l / 2,   d  / 2  )
                val bbl = Vector3Float(  -w  / 2,  -l / 2,  -d  / 2  )
                val bbr = Vector3Float(   w  / 2,  -l / 2,  -d  / 2  )
                val ftl = Vector3Float(  -tw / 2,   l / 2,   td / 2  ).added(Vector3Float(x/2, 0F,y/2))
                val ftr = Vector3Float(   tw / 2,   l / 2,   td / 2  ).added(Vector3Float(x/2, 0F,y/2))
                val btl = Vector3Float(  -tw / 2,   l / 2,  -td / 2  ).added(Vector3Float(x/2, 0F,y/2))
                val btr = Vector3Float(   tw / 2,   l / 2,  -td / 2  ).added(Vector3Float(x/2, 0F,y/2))
                DuctCoordinates(listOf(ftl, ftr, fbl, fbr, btl, btr, bbl, bbr))
            }()
            val i: DuctCoordinates = {
                val g = Duct.GAUGE
                val fbl = o.fbl.added(Vector3Float( g, 0F, -g))
                val fbr = o.fbr.added(Vector3Float(-g, 0F, -g))
                val ftl = o.ftl.added(Vector3Float( g, 0F, -g))
                val ftr = o.ftr.added(Vector3Float(-g, 0F, -g))
                val bbl = o.bbl.added(Vector3Float( g, 0F,  g))
                val bbr = o.bbr.added(Vector3Float(-g, 0F,  g))
                val btl = o.btl.added(Vector3Float( g, 0F,  g))
                val btr = o.btr.added(Vector3Float(-g, 0F,  g))
                DuctCoordinates(listOf(ftl, ftr, fbl, fbr, btl, btr, bbl, bbr))
            }()
            val m: DuctFaces = {
                val fbl = o[PV3.FBL].zeroed(true).distance(o[PV3.FTL].zeroed(true)) + data.tabsData.front.top.length + data.tabsData.front.bottom.length
                var pftt = max(data.width, data.twidth)
                // TODO: Add flt and frt tabs
                if (abs(data.offsetx) > abs(data.width - data.twidth)) { pftt += (abs(data.offsetx) - abs(data.width - data.twidth)) / 2F }
                val ftt = pftt + data.tabsData.front.left.length + data.tabsData.front.right.length
                val fbel = o[PV3.FTL].zeroed(y=true,z=true).distance(o[PV3.FBL].zeroed(y=true,z=true))
                val fber = o[PV3.FTR].zeroed(y=true,z=true).distance(o[PV3.FBR].zeroed(y=true,z=true))
                val fdt = data.twidth
                val fdb = data.width
                val fdl = o[PV3.FBL].distance(o[PV3.FTL])
                val fdr = o[PV3.FBR].distance(o[PV3.FTR])

                val bbl = o[PV3.BBL].zeroed(true).distance(o[PV3.BTL].zeroed(true)) + data.tabsData.back.top.length + data.tabsData.back.bottom.length
                // TODO: Add blt and brt tabs
                val btt = pftt + data.tabsData.back.left.length + data.tabsData.back.right.length
                val bbel = o[PV3.BBL].zeroed(y=true,z=true).distance(o[PV3.BTL].zeroed(y=true,z=true))
                val bber = o[PV3.BBR].zeroed(y=true,z=true).distance(o[PV3.BTR].zeroed(y=true,z=true))
                val bdt = data.twidth
                val bdb = data.width
                val bdl = o[PV3.BBL].distance(o[PV3.BTL])
                val bdr = o[PV3.BBR].distance(o[PV3.BTR])

                val lbl = o[PV3.LBL].zeroed(z=true).distance(o[PV3.LTL].zeroed(z=true)) + data.tabsData.left.top.length + data.tabsData.left.bottom.length
                var pltt = max(data.depth, data.tdepth)
                // TODO: Add flt and frt tabs
                if (abs(data.offsety) > abs(data.depth - data.tdepth)) { pltt += (abs(data.offsety) - abs(data.depth - data.tdepth)) / 2F }
                val ltt = pltt + data.tabsData.left.left.length + data.tabsData.left.right.length
                val lbel = o[PV3.LTL].zeroed(true,y=true).distance(o[PV3.LBL].zeroed(true,y=true))
                val lber = o[PV3.LTR].zeroed(true,y=true).distance(o[PV3.LBR].zeroed(true,y=true))
                val ldt = data.tdepth
                val ldb = data.depth
                val ldl = o[PV3.LBL].distance(o[PV3.LTL])
                val ldr = o[PV3.LBR].distance(o[PV3.LTR])

                val rbl = o[PV3.RBL].zeroed(z=true).distance(o[PV3.RTL].zeroed(z=true)) + data.tabsData.right.top.length + data.tabsData.right.bottom.length
                var prtt = max(data.depth, data.tdepth)
                // TODO: Add flt and frt tabs
                if (abs(data.offsety) > abs(data.depth - data.tdepth)) { prtt += (abs(data.offsety) - abs(data.depth - data.tdepth)) / 2F }
                val rtt = prtt + data.tabsData.right.left.length + data.tabsData.right.right.length
                val rbel = o[PV3.RTL].zeroed(true,y=true).distance(o[PV3.RBL].zeroed(true,y=true))
                val rber = o[PV3.RTR].zeroed(true,y=true).distance(o[PV3.RBR].zeroed(true,y=true))
                val rdt = data.tdepth
                val rdb = data.depth
                val rdl = o[PV3.RBL].distance(o[PV3.RTL])
                val rdr = o[PV3.RBR].distance(o[PV3.RTR])
                DuctFaces(
                    DuctFaceMeasure(
                        LengthMeasurement( fdt, UnitsOfMeasure.METERS),
                        LengthMeasurement( fdb, UnitsOfMeasure.METERS),
                        LengthMeasurement( fdl, UnitsOfMeasure.METERS),
                        LengthMeasurement( fdr, UnitsOfMeasure.METERS),
                        LengthMeasurement( fbel, UnitsOfMeasure.METERS),
                        LengthMeasurement( fber, UnitsOfMeasure.METERS),
                        LengthMeasurement( fbl, UnitsOfMeasure.METERS),
                        LengthMeasurement( ftt, UnitsOfMeasure.METERS),
                    ), DuctFaceMeasure(
                        LengthMeasurement(bdt, UnitsOfMeasure.METERS),
                        LengthMeasurement(bdb, UnitsOfMeasure.METERS),
                        LengthMeasurement(bdl, UnitsOfMeasure.METERS),
                        LengthMeasurement(bdr, UnitsOfMeasure.METERS),
                        LengthMeasurement(bbel, UnitsOfMeasure.METERS),
                        LengthMeasurement(bber, UnitsOfMeasure.METERS),
                        LengthMeasurement(bbl, UnitsOfMeasure.METERS),
                        LengthMeasurement(btt, UnitsOfMeasure.METERS),
                    ), DuctFaceMeasure(
                        LengthMeasurement(ldt, UnitsOfMeasure.METERS),
                        LengthMeasurement(ldb, UnitsOfMeasure.METERS),
                        LengthMeasurement(ldl, UnitsOfMeasure.METERS),
                        LengthMeasurement(ldr, UnitsOfMeasure.METERS),
                        LengthMeasurement(lbel, UnitsOfMeasure.METERS),
                        LengthMeasurement(lber, UnitsOfMeasure.METERS),
                        LengthMeasurement(lbl, UnitsOfMeasure.METERS),
                        LengthMeasurement(ltt, UnitsOfMeasure.METERS),
                    ), DuctFaceMeasure(
                        LengthMeasurement(rdt, UnitsOfMeasure.METERS),
                        LengthMeasurement(rdb, UnitsOfMeasure.METERS),
                        LengthMeasurement(rdl, UnitsOfMeasure.METERS),
                        LengthMeasurement(rdr, UnitsOfMeasure.METERS),
                        LengthMeasurement(rbel, UnitsOfMeasure.METERS),
                        LengthMeasurement(rber, UnitsOfMeasure.METERS),
                        LengthMeasurement(rbl, UnitsOfMeasure.METERS),
                        LengthMeasurement(rtt, UnitsOfMeasure.METERS),
                    )
                )
            }()
            return Duct(data, o, i, m)
        }
		fun getFaceIndex(idx: FaceIndices): Int = idx.idx.and(0b111)
    }
	enum class Perspective {
		OUTER, INNER
	}
	enum class FaceIndices(val idx: Int) {
		FTL(0), FTR(1), FBL(2), FBR(3),
		BTL(5), BTR(4), BBL(7), BBR(6),

		LTR(0b1000), LTL(0b1100), LBL(0b1110), LBR(0b1010),
		RTR(0b1101), RTL(0b1001), RBL(0b1011), RBR(0b1111)
	}
}

typealias PV3 = PerspectiveV3
