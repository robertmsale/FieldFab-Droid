package com.just1guy.fieldfab.components

import Vector2
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.just1guy.fieldfab.R
import com.just1guy.fieldfab.appstate.AppState
import com.just1guy.fieldfab.appstate.PreviewState
import com.just1guy.fieldfab.datapersistence.Duct
import com.just1guy.fieldfab.datapersistence.DuctData
import com.just1guy.fieldfab.datapersistence.PV3
import com.just1guy.fieldfab.datapersistence.PerspectiveV3
import com.just1guy.fieldfab.math.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun Path.moveTo(point: Vector2) {
    moveTo(point.x, point.y)
}

fun Path.lineTo(point: Vector2) {
    lineTo(point.x, point.y)
}

fun Dp.abs(): Dp = if (this < 0.dp) -this else this

fun List<Vector2>.max(): Vector2 = reduce { acc, v -> Vector2(max(acc.x, v.x), max(acc.y, v.y)) }
fun List<Vector2>.min(): Vector2 = reduce { acc, v -> Vector2(min(acc.x, v.x), min(acc.y, v.y)) }
fun List<Vector2>.maxXminY(): Vector2 = reduce { acc, v -> Vector2(max(acc.x, v.x), min(acc.y, v.y)) }
fun List<Vector2>.minXmaxY(): Vector2 = reduce { acc, v -> Vector2(min(acc.x, v.x), max(acc.y, v.y)) }

fun List<Vector2>.toOffsets(): List<Offset> = map { v -> Offset(v.x, v.y) }

@Composable
fun CenterText(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

}

@Composable
fun DuctSideView(side: String, state: MutableState<AppState>, showMeasurements: Boolean = true) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0.9F, 0.9F, 0.9F))
    ) {
        val size = Size(with(LocalDensity.current) {maxWidth.toPx()}, with(LocalDensity.current) {maxHeight.toPx()})
        val density = with(LocalDensity.current) {1.dp.toPx()}
        val wh = Vector2(size.width, size.height)
        val maxwh = min(wh.x, wh.y)
        val center = Vector2(maxwh, maxwh).dividedScalar(2F)
        val scale = 0.7F
        var tl = center.lerped(Vector2(0F, 0F), scale)
        var tr = center.lerped(Vector2(maxwh, 0F), scale)
        var bl = center.lerped(Vector2(0F, maxwh), scale)
        var br = center.lerped(Vector2(maxwh, maxwh), scale)
        val ctl = tl.copy()
        val ctr = tr.copy()
        val cbl = bl.copy()
        val cbr = br.copy()
        var ntl: Vector2? = Vector2(0F, 0F); ntl = null
        var ntr: Vector2? = Vector2(0F, 0F); ntr = null
        var nbl: Vector2? = Vector2(0F, 0F); nbl = null
        var nbr: Vector2? = Vector2(0F, 0F); nbr = null
        var v3c: List<PerspectiveV3> = listOf()
        var v3a: V3Axis = V3Axis.X
        var aspectw: Float? = 0F; aspectw = null
        var aspecth: Float? = 0F; aspecth = null
        var tabt: Float? = 0F; tabt = null
        var tabb: Float? = 0F; tabb = null
        var tabl: Float? = 0F; tabl = null
        var tabr: Float? = 0F; tabr = null



        val linelen = maxwh * 0.035F
        // TODO: len2F (turn tab length into screen dimensions)

        val sh = state.value.currentDuct!!.data.length
        when (side) { // TODO: Add tab calculations
            "Front" -> {
                v3c = listOf(PV3.FTR, PV3.FTL, PV3.FBL, PV3.FBR)
                val sw = max(state.value.currentDuct!!.data.width, state.value.currentDuct!!.data.twidth + abs(state.value.currentDuct!!.data.offsetx))
                if (sw < sh) aspectw = sw / sh else aspecth = sh / sw
            }
            "Back" -> {
                v3c = listOf(PV3.BTR, PV3.BTL, PV3.BBL, PV3.BBR)
                val sw = max(state.value.currentDuct!!.data.width, state.value.currentDuct!!.data.twidth + abs(state.value.currentDuct!!.data.offsetx))
                if (sw < sh) aspectw = sw / sh else aspecth = sh / sw
            }
            "Left" -> {
                v3a = V3Axis.Z
                v3c = listOf(PV3.LTR, PV3.LTL, PV3.LBL, PV3.LBR)
                val sw = max(state.value.currentDuct!!.data.depth, state.value.currentDuct!!.data.tdepth + abs(state.value.currentDuct!!.data.offsety))
                if (sw < sh) aspectw = sw / sh else aspecth = sh / sw
            }
            "Right" -> {
                v3a = V3Axis.Z
                v3c = listOf(PV3.RTR, PV3.RTL, PV3.RBL, PV3.RBR)
                val sw = max(state.value.currentDuct!!.data.depth, state.value.currentDuct!!.data.tdepth + abs(state.value.currentDuct!!.data.offsety))
                if (sw < sh) aspectw = sw / sh else aspecth = sh / sw
            }
            else -> {
                // TODO: Add all duct faces case
            }
        }

        val tw = max(
            abs(state.value.currentDuct!!.outer[v3c[0]][v3a]),
            abs(state.value.currentDuct!!.outer[v3c[3]][v3a])
        ) + max(
            abs(state.value.currentDuct!!.outer[v3c[2]][v3a]),
            abs(state.value.currentDuct!!.outer[v3c[1]][v3a])
        )

        val clmp = 0.7F
        if (aspectw != null) {
            tl.lerp(ctr, 1 - aspectw.clamp(clmp, 1F))
            tr.lerp(ctl, 1 - aspectw.clamp(clmp, 1F))
            bl.lerp(cbr, 1 - aspectw.clamp(clmp, 1F))
            br.lerp(cbl, 1 - aspectw.clamp(clmp, 1F))
        }
        if (aspecth != null) {
            tl.lerp(cbl, 1 - aspecth.clamp(clmp, 1F))
            tr.lerp(cbr, 1 - aspecth.clamp(clmp, 1F))
            bl.lerp(ctl, 1 - aspecth.clamp(clmp, 1F))
            br.lerp(ctr, 1 - aspecth.clamp(clmp, 1F))
        }
        val ml = abs(state.value.currentDuct!!.outer[v3c[0]][v3a].distance(state.value.currentDuct!!.outer[v3c[3]][v3a]))
        val mr = abs(state.value.currentDuct!!.outer[v3c[1]][v3a].distance(state.value.currentDuct!!.outer[v3c[2]][v3a]))
        val mltw = when (side) {
            "Front", "Back" -> 1 - ml / tw
            else -> ml / tw
        }
        val mrtw = when (side) {
            "Front", "Back" -> 1 - mr / tw
            else -> mr / tw
        }
        if (state.value.currentDuct!!.outer[v3c[0]][v3a].distance(0F) > state.value.currentDuct!!.outer[v3c[3]][v3a].distance(0F)) {
            nbr = br.lerped(bl, mltw)
        } else {
            ntr = tr.lerped(tl, mltw)
        }
        if (state.value.currentDuct!!.outer[v3c[2]][v3a].distance(0F) > state.value.currentDuct!!.outer[v3c[1]][v3a].distance(0F)) {
            ntl = tl.lerped(tr, mrtw)
        } else {
            nbl = bl.lerped(br, mrtw)
        }

        if (ntl != null) tl = ntl
        if (nbl != null) bl = nbl
        if (ntr != null) tr = ntr
        if (nbr != null) br = nbr
        val stroke = 5F

        val maxw = ((maxWidth - maxHeight).abs().div(2))
        val x = if (maxWidth < maxHeight) 0F else maxw.value * 2.75F
        val y = if (maxWidth < maxHeight) maxw.value * 2.75F else 0F

//        tl.multiplyScalar(2.75F)
//        tr.multiplyScalar(2.75F)
//        br.multiplyScalar(2.75F)
//        bl.multiplyScalar(2.75F)

        tl.add(Vector2(x, y))
        tr.add(Vector2(x, y))
        br.add(Vector2(x, y))
        bl.add(Vector2(x, y))

        var boundstl = listOf(tl, tr, bl, br).min()
        var boundstr = listOf(tl, tr, bl, br).maxXminY()
        var boundsbr = listOf(tl, tr, bl, br).max()
        var boundsbl = listOf(tl, tr, bl, br).minXmaxY()


        val ductBoundsShape = GenericShape { size, p ->
            moveTo(boundstl)
            lineTo(boundstr)
            lineTo(boundsbr)
            lineTo(boundsbl)
            lineTo(boundstl)
        }
        val ductShape = GenericShape { size, p ->
            moveTo(tl)
            lineTo(tr)
            lineTo(br)
            lineTo(bl)
            lineTo(tl)
        }

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.galvanized_diffuse),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        shape = ductShape
                        clip = true
                    }
                    .border(2.dp, color = Color.LightGray, shape = ductShape)
            )
            Canvas(modifier = Modifier.fillMaxSize()) {
                val stroke = 2.5F
                drawPoints(
                    listOf(
                        boundstl,
                        boundstr,
                        boundstr,
                        boundsbr,
                        boundsbr,
                        boundsbl,
                        boundsbl,
                        boundstl,
                    ).toOffsets(),
                    color = Color.Black,
                    pointMode = PointMode.Lines,
                    strokeWidth = stroke
                )
                drawPoints(
                    listOf(
                        boundstl,
                        boundstl.added(Vector2(0F, -80F)),
                        boundstl.added(Vector2(0F, -80F)),
                        boundstr.added(Vector2(0F, -80F)),
                        boundstr.added(Vector2(0F, -80F)),
                        boundstr,
                    ).toOffsets(),
                    color = Color.Black,
                    pointMode = PointMode.Lines,
                    strokeWidth = stroke
                )
                drawPoints(
                    listOf(
                        boundsbl,
                        boundsbl.added(Vector2(-80F, 0F)),
                        boundsbl.added(Vector2(-80F, 0F)),
                        boundstl.added(Vector2(-80F, 0F)),
                        boundstl.added(Vector2(-80F, 0F)),
                        boundstl,
                    ).toOffsets(),
                    color = Color.Black,
                    pointMode = PointMode.Lines,
                    strokeWidth = stroke
                )
            }
            if (showMeasurements) {
                val midLeft = boundstl.lerped(boundsbl, 0.5F)
                val boundingLeftText =
                    state.value.currentDuct!!.measurements[side].totalLeft.toString()

                Text(
                    text = boundingLeftText,
                    modifier = Modifier
                        .offset((midLeft.x / density).dp, (midLeft.y / density).dp)
                        .offset((-85F).dp, 0.dp)
                        .rotate(-90F)
                )
                val midTop = boundstl.lerped(boundstr, 0.5F)
                val boundingTopText =
                    state.value.currentDuct!!.measurements[side].totalTop.toString()
                Text(
                    text = boundingTopText,
                    modifier = Modifier
                        .offset((midTop.x / density).dp, (midTop.y / density).dp)
                        .offset(0.dp, (-48F).dp)
                )
                val midTopO = tl.lerped(tr, 0.5F)
                Text(
                    text = state.value.currentDuct!!.measurements[side].top.toString(),
                    color = Color.White,
                    modifier = Modifier
                        .offset((midTopO.x / density).dp, (midTopO.y / density).dp)
                )
                val midBot = bl.lerped(br, 0.5F)
                Text(
                    text = state.value.currentDuct!!.measurements[side].bottom.toString(),
                    color = Color.White,
                    modifier = Modifier
                        .offset((midBot.x / density).dp, (midBot.y / density).dp)
                        .offset(0.dp, (-24).dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DuctSideViewPreview() {
    var state = remember { mutableStateOf(AppState()) }
    state.value = state.value.copy(
        currentDuct = Duct.make(DuctData(
            name = "Test",
            width = 0.05F,
            depth = 0.05F,
            length = 0.05F,
            offsetx = 0.055F,
            offsety = 0F,
            twidth = 0.05F,
            tdepth = 0.05F
        ))
    )
    DuctSideView(side = "Front", state = state)
}