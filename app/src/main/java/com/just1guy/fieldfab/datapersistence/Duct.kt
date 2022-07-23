package com.just1guy.fieldfab.datapersistence

import Vector3
import com.just1guy.fieldfab.math.zeroed
import kotlin.math.abs
import kotlin.math.max

typealias V3 = Vector3

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

data class DuctCoordinates(
    var ftl: V3,
    var ftr: V3,
    val fbl: V3,
    var fbr: V3,
    var btl: V3,
    var btr: V3,
    var bbl: V3,
    var bbr: V3,
) {
    constructor(array: List<V3>) : this(
        array[0],
        array[1],
        array[2],
        array[3],
        array[4],
        array[5],
        array[6],
        array[7],
    ) {}
    operator fun get(c: PerspectiveV3): Vector3 {
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
}

data class DuctFaceMeasure (
    val top: Float,
    val bottom: Float,
    val left: Float,
    val right: Float,
    val boundingLeft: Float,
    val boundingRight: Float,
    val totalLeft: Float,
    val totalTop: Float,
) {

}

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
                val fbl = V3(  -w  / 2,  -l / 2,   d  / 2  )
                val fbr = V3(   w  / 2,  -l / 2,   d  / 2  )
                val bbl = V3(  -w  / 2,  -l / 2,  -d  / 2  )
                val bbr = V3(   w  / 2,  -l / 2,  -d  / 2  )
                val ftl = V3(  -tw / 2,   l / 2,   td / 2  ).added(V3(x/2, 0F,y/2))
                val ftr = V3(   tw / 2,   l / 2,   td / 2  ).added(V3(x/2, 0F,y/2))
                val btl = V3(  -tw / 2,   l / 2,  -td / 2  ).added(V3(x/2, 0F,y/2))
                val btr = V3(   tw / 2,   l / 2,  -td / 2  ).added(V3(x/2, 0F,y/2))
                DuctCoordinates(listOf(ftr, ftl, fbl, fbr, btr, btl, bbl, bbr))
            }()
            val i: DuctCoordinates = {
                val g = Duct.GAUGE
                val fbl = o.fbl.added(Vector3( g, 0F, -g))
                val fbr = o.fbr.added(Vector3(-g, 0F, -g))
                val ftl = o.ftl.added(Vector3( g, 0F, -g))
                val ftr = o.ftr.added(Vector3(-g, 0F, -g))
                val bbl = o.bbl.added(Vector3( g, 0F,  g))
                val bbr = o.bbr.added(Vector3(-g, 0F,  g))
                val btl = o.btl.added(Vector3( g, 0F,  g))
                val btr = o.btr.added(Vector3(-g, 0F,  g))
                DuctCoordinates(listOf(ftr, ftl, fbl, fbr, btr, btl, bbl, bbr))
            }()
            val m: DuctFaces = {
                val fbl = o[PV3.FBL].zeroed(true).distance(o[PV3.FTL].zeroed(true)) // TODO: Add tabs
                var pftt = max(data.width, data.twidth)
                // TODO: Add flt and frt tabs
                if (abs(data.offsetx) > abs(data.width - data.twidth)) { pftt += (abs(data.offsetx) - abs(data.width - data.twidth)) / 2F }
                val ftt = pftt // TODO add conversions to metric + flt + frt
                val fbel = o[PV3.FTL].zeroed(y=true,z=true).distance(o[PV3.FBL].zeroed(y=true,z=true))
                val fber = o[PV3.FTR].zeroed(y=true,z=true).distance(o[PV3.FBR].zeroed(y=true,z=true))
                val fdt = data.twidth
                val fdb = data.width
                val fdl = o[PV3.FBL].distance(o[PV3.FTL])
                val fdr = o[PV3.FBR].distance(o[PV3.FTR])

                val bbl = o[PV3.BBL].zeroed(true).distance(o[PV3.BTL].zeroed(true)) // TODO: Add tabs
                // TODO: Add blt and brt tabs
                val btt = pftt // TODO add conversions to metric + blt + brt
                val bbel = o[PV3.BBL].zeroed(y=true,z=true).distance(o[PV3.BTL].zeroed(y=true,z=true))
                val bber = o[PV3.BBR].zeroed(y=true,z=true).distance(o[PV3.BTR].zeroed(y=true,z=true))
                val bdt = data.twidth
                val bdb = data.width
                val bdl = o[PV3.BBL].distance(o[PV3.BTL])
                val bdr = o[PV3.BBR].distance(o[PV3.BTR])

                val lbl = o[PV3.LBL].zeroed(z=true).distance(o[PV3.LTL].zeroed(z=true)) // TODO: Add tabs
                var pltt = max(data.depth, data.tdepth)
                // TODO: Add flt and frt tabs
                if (abs(data.offsety) > abs(data.depth - data.tdepth)) { pltt += (abs(data.offsety) - abs(data.depth - data.tdepth)) / 2F }
                val ltt = pftt // TODO add conversions to metric + flt + frt
                val lbel = o[PV3.LTL].zeroed(true,y=true).distance(o[PV3.LBL].zeroed(true,y=true))
                val lber = o[PV3.LTR].zeroed(true,y=true).distance(o[PV3.LBR].zeroed(true,y=true))
                val ldt = data.tdepth
                val ldb = data.depth
                val ldl = o[PV3.LBL].distance(o[PV3.LTL])
                val ldr = o[PV3.LBR].distance(o[PV3.LTR])

                val rbl = o[PV3.RBL].zeroed(z=true).distance(o[PV3.RTL].zeroed(z=true)) // TODO: Add tabs
                var prtt = max(data.depth, data.tdepth)
                // TODO: Add flt and frt tabs
                if (abs(data.offsety) > abs(data.depth - data.tdepth)) { prtt += (abs(data.offsety) - abs(data.depth - data.tdepth)) / 2F }
                val rtt = prtt // TODO add conversions to metric + flt + frt
                val rbel = o[PV3.RTL].zeroed(true,y=true).distance(o[PV3.RBL].zeroed(true,y=true))
                val rber = o[PV3.RTR].zeroed(true,y=true).distance(o[PV3.RBR].zeroed(true,y=true))
                val rdt = data.tdepth
                val rdb = data.depth
                val rdl = o[PV3.RBL].distance(o[PV3.RTL])
                val rdr = o[PV3.RBR].distance(o[PV3.RTR])
                DuctFaces(
                    DuctFaceMeasure(
                        fdt,
                        fdb,
                        fdl,
                        fdr,
                        fbel,
                        fber,
                        fbl,
                        ftt
                    ), DuctFaceMeasure(
                        bdt,
                        bdb,
                        bdl,
                        bdr,
                        bbel,
                        bber,
                        bbl,
                        btt
                    ), DuctFaceMeasure(
                        ldt,
                        ldb,
                        ldl,
                        ldr,
                        lbel,
                        lber,
                        lbl,
                        ltt
                    ), DuctFaceMeasure(
                        rdt,
                        rdb,
                        rdl,
                        rdr,
                        rbel,
                        rber,
                        rbl,
                        rtt
                    )
                )
            }()
            return Duct(data, o, i, m)
        }
    }
}

typealias PV3 = PerspectiveV3