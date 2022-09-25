package com.just1guy.fieldfab.components

import Vector2Float
import Vector3Float
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.zIndex
import androidx.core.math.MathUtils.clamp
import com.just1guy.fieldfab.R
import com.just1guy.fieldfab.appstate.AppState
import com.just1guy.fieldfab.appstate.KotlinDefaultAppState
import com.just1guy.fieldfab.appstate.rememberKotlinDefaultAppState
import com.just1guy.fieldfab.datapersistence.Duct
import com.just1guy.fieldfab.datapersistence.DuctData
import com.just1guy.fieldfab.datapersistence.PerspectiveV3
import com.just1guy.fieldfab.datapersistence.UnitsOfMeasure
import com.just1guy.fieldfab.math.*
import kotlin.math.max
import kotlin.math.min

fun Path.moveTo(point: Vector2Float) {
    moveTo(point.x, point.y)
}

fun Path.lineTo(point: Vector2Float) {
    lineTo(point.x, point.y)
}

fun Dp.abs(): Dp = if (this < 0.dp) -this else this

fun List<Vector2Float>.max(): Vector2Float = reduce { acc, v -> Vector2Float(max(acc.x, v.x), max(acc.y, v.y)) }
fun List<Vector2Float>.min(): Vector2Float = reduce { acc, v -> Vector2Float(min(acc.x, v.x), min(acc.y, v.y)) }
fun List<Vector2Float>.maxXminY(): Vector2Float = reduce { acc, v -> Vector2Float(max(acc.x, v.x), min(acc.y, v.y)) }
fun List<Vector2Float>.minXmaxY(): Vector2Float = reduce { acc, v -> Vector2Float(min(acc.x, v.x), max(acc.y, v.y)) }

fun List<Vector2Float>.toOffsets(): List<Offset> = map { v -> Offset(v.x, v.y) }

@Composable
fun CenterText(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

}

class DSVPadding(
    val tl: Vector2Float = Vector2Float(.3F, .3F),
    val tr: Vector2Float = Vector2Float(.7F, .3F),
    val bl: Vector2Float = Vector2Float(.3F, .7F),
    val br: Vector2Float = Vector2Float(.7F, .7F)
    )

fun Vector3Float.normalizeToV2(axis: V3Axis): Vector2Float {
    val norm = normalize()
    return when (axis) {
        V3Axis.X -> return Vector2Float(x, y)
        else -> return Vector2Float(z, y)
    }
}

fun Vector3Float.toV2(axis: V3Axis): Vector2Float {
    return when (axis) {
        V3Axis.X -> return Vector2Float(x, y)
        else -> return Vector2Float(z, y)
    }
}

fun Offset.toVector2(): Vector2Float = Vector2Float(x, y)
fun Vector2Float.toOffset(): Offset = Offset(x, y)

@Composable
fun DuctSideView(modifier: Modifier = Modifier, side: String, state: MutableState<AppState>, kAppData: KotlinDefaultAppState, showMeasurements: Boolean = true) {
    val image = painterResource(id = R.drawable.galvanized_diffuse)
    Box(modifier = modifier) {
        Image(painter = image, modifier = modifier.zIndex(-1F), contentDescription = null)
        Canvas (
            modifier = modifier,
        ) {
            // from center
            val fc = min(size.width, size.height) / 2
            val dsvp = DSVPadding(
                tl = Vector2Float(.3F, .3F).multipliedScalar(fc),
                tr = Vector2Float(.7F, .3F).multipliedScalar(fc),
                bl = Vector2Float(.3F, .7F).multipliedScalar(fc),
                br = Vector2Float(.7F, .7F).multipliedScalar(fc),
            )
            val dtl = when (side) {
                "Front" -> state.value.currentDuct!!.outer[PerspectiveV3.FTL].toV2(V3Axis.X)
                "Back" -> state.value.currentDuct!!.outer[PerspectiveV3.BTL].toV2(V3Axis.X).multiplied(Vector2Float(-1F,1F))
                "Left" -> state.value.currentDuct!!.outer[PerspectiveV3.LTL].toV2(V3Axis.Z)
                "Right" -> state.value.currentDuct!!.outer[PerspectiveV3.RTL].toV2(V3Axis.Z).multiplied(Vector2Float(-1F,1F))
                else -> Vector2Float(0F, 0F)
            }
            val dtr = when (side) {
                "Front" -> state.value.currentDuct!!.outer[PerspectiveV3.FTR].toV2(V3Axis.X)
                "Back" -> state.value.currentDuct!!.outer[PerspectiveV3.BTR].toV2(V3Axis.X).multiplied(Vector2Float(-1F,1F))
                "Left" -> state.value.currentDuct!!.outer[PerspectiveV3.LTR].toV2(V3Axis.Z)
                "Right" -> state.value.currentDuct!!.outer[PerspectiveV3.RTR].toV2(V3Axis.Z).multiplied(Vector2Float(-1F,1F))
                else -> Vector2Float(0F, 0F)
            }
            val dbr = when (side) {
                "Front" -> state.value.currentDuct!!.outer[PerspectiveV3.FBR].toV2(V3Axis.X)
                "Back" -> state.value.currentDuct!!.outer[PerspectiveV3.BBR].toV2(V3Axis.X).multiplied(Vector2Float(-1F,1F))
                "Left" -> state.value.currentDuct!!.outer[PerspectiveV3.LBR].toV2(V3Axis.Z)
                "Right" -> state.value.currentDuct!!.outer[PerspectiveV3.RBR].toV2(V3Axis.Z).multiplied(Vector2Float(-1F,1F))
                else -> Vector2Float(0F, 0F)
            }
            val dbl = when (side) {
                "Front" -> state.value.currentDuct!!.outer[PerspectiveV3.FBL].toV2(V3Axis.X)
                "Back" -> state.value.currentDuct!!.outer[PerspectiveV3.BBL].toV2(V3Axis.X).multiplied(Vector2Float(-1F,1F))
                "Left" -> state.value.currentDuct!!.outer[PerspectiveV3.LBL].toV2(V3Axis.Z)
                "Right" -> state.value.currentDuct!!.outer[PerspectiveV3.RBL].toV2(V3Axis.Z).multiplied(Vector2Float(-1F,1F))
                else -> Vector2Float(0F, 0F)
            }
            val min = listOf(dbl, dtl, dtr, dbr).min()
            val max = listOf(dbl, dtl, dtr, dbr).max()
            val sizex = min.x.distance(max.x)
            val sizey = min.y.distance(max.y)
            val sizeNorm = Vector2Float(sizex, sizey).normalize()
            val cen = center.toVector2()
            val ah = size.width / size.height
            val aw = size.height / size.width
            val ptl = cen
                .multiplied(Vector2Float(0.3F * if(aw > ah) 1F else aw * if (sizex < sizey) sizeNorm.x else 1F, 0.3F * if(aw < ah) 1F else ah * if (sizex > sizey) sizeNorm.y else 1F))
                .added(Vector2Float(if (sizex < sizey) fc / 2 * sizeNorm.x else 1F, if (sizex > sizey) fc / 2 * sizeNorm.y else 1F))
            val ptr = cen
                .multiplied(Vector2Float(1.7F * if(aw > ah) 1F else aw * if (sizex < sizey) sizeNorm.x else 1F, 0.3F * if(aw < ah) 1F else ah * if (sizex > sizey) sizeNorm.y else 1F))
                .added(Vector2Float(if (sizex < sizey) fc / 2 * sizeNorm.x else 1F, if (sizex > sizey) fc / 2 * sizeNorm.y else 1F))
            val pbl = cen
                .multiplied(Vector2Float(0.3F * if(aw > ah) 1F else aw * if (sizex < sizey) sizeNorm.x else 1F, 1.7F * if(aw < ah) 1F else ah * if (sizex > sizey) sizeNorm.y else 1F))
                .added(Vector2Float(if (sizex < sizey) fc / 2 * sizeNorm.x else 1F, if (sizex > sizey) fc / 2 * sizeNorm.y else 1F))
            val pbr = cen
                .multiplied(Vector2Float(1.7F * if(aw > ah) 1F else aw * if (sizex < sizey) sizeNorm.x else 1F, 1.7F * if(aw < ah) 1F else ah * if (sizex > sizey) sizeNorm.y else 1F))
                .added(Vector2Float(if (sizex < sizey) fc / 2 * sizeNorm.x else 1F, if (sizex > sizey) fc / 2 * sizeNorm.y else 1F))

            val bbl = pbl.added(Vector2Float(-20.sp.toPx(), 0F))
            val btll = ptl.added(Vector2Float(-20.sp.toPx(), 0F))
            val btlt = ptl.added(Vector2Float(0F, -20.sp.toPx()))
            val btr = ptr.added(Vector2Float(0F, -20.sp.toPx()))

            val loffset = clamp(dtl.x.distance(dbl.x) / min.x.distance(max.x), 0F, 1F)
            val roffset = clamp(dtr.x.distance(dbr.x) / min.x.distance(max.x), 0F, 1F)
            val atl = ptl.lerped(ptr, if(dtl.x > dbl.x) (loffset) else 0F)
            val abl = pbl.lerped(pbr, if(dtl.x < dbl.x) (loffset) else 0F)
            val atr = ptr.lerped(ptl, if(dtr.x < dbr.x) (roffset) else 0F)
            val abr = pbr.lerped(pbl, if(dtr.x > dbr.x) (roffset) else 0F)

            if (state.value.currentDuct!!.data.tabsData[side].top.length > 0.0F) {
                val margin = 20.sp.toPx() * state.value.currentDuct!!.data.tabsData[side].top.lengthAsAlpha
                val bl = atl.lerped( abl, clamp(margin / atl.distance(abl), 0F, 1F) )
                val br = atr.lerped(abr, clamp(margin / atr.distance(abr), 0F, 1F))
                drawPath(
                    path = Path().apply {
                        moveTo(atl)
                        lineTo(atr)
                        lineTo(br)
                        lineTo(bl)
                        lineTo(atl)
                    },
                    color = Color.White.copy(alpha = 0.5F)
                )
            }
            if (state.value.currentDuct!!.data.tabsData[side].bottom.length > 0.0F) {
                val margin = 20.sp.toPx() * state.value.currentDuct!!.data.tabsData[side].bottom.lengthAsAlpha
                val tl = abl.lerped(atl, clamp(margin / atl.distance(abl), 0F, 1F))
                val tr = abr.lerped(atr, clamp(margin / atr.distance(abr), 0F, 1F))
                drawPath(
                    path = Path().apply {
                        moveTo(abl)
                        lineTo(abr)
                        lineTo(tr)
                        lineTo(tl)
                        lineTo(abl)
                    },
                    color = Color.White.copy(alpha = 0.5F)
                )
            }
            if (state.value.currentDuct!!.data.tabsData[side].left.length > 0.0F) {
                val margin = 20.sp.toPx() * state.value.currentDuct!!.data.tabsData[side].left.lengthAsAlpha
                val abla = abl.added(Vector2Float(margin))
                val atla = atl.added(Vector2Float(margin))
                drawPath(
                    path = Path().apply {
                        moveTo(abl)
                        lineTo(abla)
                        lineTo(atla)
                        lineTo(atl)
                        lineTo(abl)
                    },
                    color = Color.White.copy(alpha = 0.5F)
                )
            }
            if (state.value.currentDuct!!.data.tabsData[side].right.length > 0.0F) {
                val margin = 20.sp.toPx() * state.value.currentDuct!!.data.tabsData[side].right.lengthAsAlpha
                val abra = abr.subtracted(Vector2Float(margin))
                val atra = atr.subtracted(Vector2Float(margin))
                drawPath(
                    path = Path().apply {
                        moveTo(abr)
                        lineTo(abra)
                        lineTo(atra)
                        lineTo(atr)
                        lineTo(abr)
                    },
                    color = Color.White.copy(alpha = 0.5F)
                )
            }
            drawPath(
                path = Path().apply {
                    moveTo(Vector2Float())
                    lineTo(ptl)
                    lineTo(pbl)
                    lineTo(Vector2Float(y=fc*2F))
                    lineTo(Vector2Float())
                },
                color = Color.White,
            )
            drawPath(
                path = Path().apply {
                    moveTo(ptl)
                    lineTo(atl)
                    lineTo(abl)
                    lineTo(pbl)
                },
                color = Color.White
            )
            drawPath(
                path = Path().apply {
                    moveTo(ptr)
                    lineTo(atr)
                    lineTo(abr)
                    lineTo(pbr)
                },
                color = Color.White
            )
            drawPath(
                path = Path().apply {
                    moveTo(Vector2Float(-8F))
                    lineTo(ptl.subtracted(Vector2Float(8F)))
                    lineTo(ptr.added(Vector2Float(8F)))
                    lineTo(Vector2Float(fc*2F+8F))
                    lineTo(Vector2Float(-8F))
                },
                color = Color.White,
            )
            drawPath(
                path = Path().apply {
                    moveTo(ptr)
                    lineTo(Vector2Float(fc*2F))
                    lineTo(Vector2Float(fc*2F, fc*2F))
                    lineTo(pbr)
                    lineTo(ptr)
                },
                color = Color.White,
            )
            drawPath(
                path = Path().apply {
                    moveTo(pbl.subtracted(Vector2Float(8F)))
                    lineTo(Vector2Float(-8F,fc*2F))
                    lineTo(Vector2Float(fc*2F+8F, fc*2F))
                    lineTo(pbr.added(Vector2Float(8F)))
                    lineTo(pbl.subtracted(Vector2Float(8F)))
                },
                color = Color.White,
            )
            if (showMeasurements) {
                val pOffsets = listOf(ptl, ptr, ptr, pbr, pbr, pbl, pbl, ptl).map { it.toOffset() }
                drawPoints(
                    points = pOffsets,
                    strokeWidth = 2F,
                    color = Color.DarkGray,
                    pointMode = PointMode.Lines
                )
                val pbOffsets = listOf(
                    pbl,
                    bbl,
                    bbl,
                    btll,
                    btll,
                    ptl,
                    ptl,
                    btlt,
                    btlt,
                    btr,
                    btr,
                    ptr
                ).map { it.toOffset() }
                drawPoints(
                    points = pbOffsets,
                    strokeWidth = 2F,
                    color = Color.DarkGray,
                    pointMode = PointMode.Lines
                )
            }
            val totalTopPos = btlt.lerped(btr, .5F)
            val totalLeftPos = bbl.lerped(btll, .5F)
            val unitsOfMeasure = if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES
            val measurements = state.value.currentDuct!!.measurements[side].convertAll(unitsOfMeasure)

            drawContext.canvas.nativeCanvas.apply {
                val paint = Paint().asFrameworkPaint().apply {
                    textSize = 12.sp.toPx()
                    isAntiAlias = true
                    color = android.graphics.Color.BLACK
                    textAlign = android.graphics.Paint.Align.CENTER
                }
                val paintWhite = Paint().asFrameworkPaint().apply {
                    textSize = 12.sp.toPx()
                    isAntiAlias = true
                    color = android.graphics.Color.WHITE
                    textAlign = android.graphics.Paint.Align.CENTER

                }
                if (showMeasurements) {
                    drawText(
                        measurements.totalTop.toText(),
                        totalTopPos.x,
                        totalTopPos.y - 2.sp.toPx(),
                        paint
                    )
                    rotate(270F, totalLeftPos.x, totalLeftPos.y)
                    drawText(
                        measurements.totalLeft.toText(),
                        totalLeftPos.x,
                        totalLeftPos.y - 2.sp.toPx(),
                        paint
                    )
                    rotate(-270F, totalLeftPos.x, totalLeftPos.y)
                    val tlox = if (atl.x > abl.x)
                        ptl.lerped(ptr, loffset * .5F).added(Vector2Float(0F, -2.sp.toPx()))
                    else pbl.lerped(pbr, loffset * .5F).added(Vector2Float(0F, 14.sp.toPx()))
                    val trox = if (atr.x < abr.x)
                        ptr.lerped(ptl, roffset * .5F).added(Vector2Float(0F, -2.sp.toPx()))
                    else pbr.lerped(pbl, roffset * .5F).added(Vector2Float(0F, 14.sp.toPx()))
                    drawText(
                        measurements.boundingLeft.toText(),
                        tlox.x,
                        tlox.y - 2.sp.toPx(),
                        paint,
                    )
                    drawText(
                        measurements.boundingRight.toText(),
                        trox.x,
                        trox.y - 2.sp.toPx(),
                        paint,
                    )
                } else {
                    drawText(
                        side,
                        center.x,
                        size.height * .1F,
                        paint
                    )
                }
//                drawText(
//                    dtr.x.toString(),
//                    totalLeftPos.x,
//                    totalLeftPos.y - 2.sp.toPx(),
//                    paint
//                )
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
            width = 0.5F,
            depth = 0.5F,
            length = 0.5F,
            offsetx = 1F,
            offsety = 1F,
            twidth = 0.5F,
            tdepth = 0.5F
        ))
    )

    DuctSideView(modifier = Modifier.size(880.dp), side = "Right", state = state, kAppData = rememberKotlinDefaultAppState())
}