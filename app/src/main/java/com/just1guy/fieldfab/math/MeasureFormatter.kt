package com.just1guy.fieldfab.math

import java.text.DecimalFormat
import java.text.NumberFormat
import com.just1guy.fieldfab.datapersistence.UnitsOfMeasure
import kotlinx.serialization.Serializable
import java.math.RoundingMode
import kotlin.math.ceil
import kotlin.math.floor

@Serializable
data class DuctFaceMeasure (
    val top: LengthMeasurement,
    val bottom: LengthMeasurement,
    val left: LengthMeasurement,
    val right: LengthMeasurement,
    val boundingLeft: LengthMeasurement,
    val boundingRight: LengthMeasurement,
    val totalLeft: LengthMeasurement,
    val totalTop: LengthMeasurement,
) {
    fun convertAll(to: UnitsOfMeasure): DuctFaceMeasure = DuctFaceMeasure(
        top.convert(to),
        bottom.convert(to),
        left.convert(to),
        right.convert(to),
        boundingLeft.convert(to),
        boundingRight.convert(to),
        totalLeft.convert(to),
        totalTop.convert(to)
    )
}

@Serializable
data class LengthMeasurement(val value: Float, val unit: UnitsOfMeasure) {
    private val formatter: NumberFormat
    get() {
        val nf = NumberFormat.getInstance()
        if (nf is DecimalFormat) {
            nf.maximumFractionDigits = when (unit) {
                UnitsOfMeasure.INCHES -> 0
                UnitsOfMeasure.FEET -> 4
                UnitsOfMeasure.METERS -> 4
                UnitsOfMeasure.CENTIMETERS -> 2
                UnitsOfMeasure.MILLIMETERS -> 0
            }
            nf.roundingMode = RoundingMode.FLOOR
        }
        return nf
    }
    fun toText(): String {
        var value = this.value
        val postfix = when (unit) {
            UnitsOfMeasure.INCHES ->
                if      ( (0.0625F..0.1874999F).contains(value - value.toInt().toFloat()) ) "⅛\""
                else if ( (0.1875F..0.3124999F).contains(value - value.toInt().toFloat()) ) "¼\""
                else if ( (0.3125F..0.4374999F).contains(value - value.toInt().toFloat()) ) "⅜\""
                else if ( (0.4375F..0.5624999F).contains(value - value.toInt().toFloat()) ) "½\""
                else if ( (0.5625F..0.6874999F).contains(value - value.toInt().toFloat()) ) "⅝\""
                else if ( (0.6875F..0.8124999F).contains(value - value.toInt().toFloat()) ) "¾\""
                else if ( (0.8125F..0.9374999F).contains(value - value.toInt().toFloat()) ) "⅞\""
                else "\""
            UnitsOfMeasure.FEET -> "\'"
            UnitsOfMeasure.METERS -> " m"
            UnitsOfMeasure.CENTIMETERS -> " cm"
            UnitsOfMeasure.MILLIMETERS -> " mm"
        }
        if (unit == UnitsOfMeasure.INCHES && (value - floor(value)) > 0.0625F && postfix[0] == '"') value += 1
        return formatter.format(floor(value)) + postfix
    }
    fun convert(to: UnitsOfMeasure): LengthMeasurement {
        val retval = when (unit) {
            UnitsOfMeasure.INCHES -> when (to) {
                UnitsOfMeasure.INCHES -> value
                UnitsOfMeasure.FEET -> value / 12F
                UnitsOfMeasure.METERS -> value / 39.37F
                UnitsOfMeasure.CENTIMETERS -> value * 2.54F
                UnitsOfMeasure.MILLIMETERS -> value * 25.4F
            }
            UnitsOfMeasure.FEET -> when (to) {
                UnitsOfMeasure.INCHES -> value * 12F
                UnitsOfMeasure.FEET -> value
                UnitsOfMeasure.METERS -> value / 3.281F
                UnitsOfMeasure.CENTIMETERS -> value * 30.48F
                UnitsOfMeasure.MILLIMETERS -> value * 304.8F
            }
            UnitsOfMeasure.METERS -> when (to) {
                UnitsOfMeasure.INCHES -> value * 39.37F
                UnitsOfMeasure.FEET -> value * 3.281F
                UnitsOfMeasure.METERS -> value
                UnitsOfMeasure.CENTIMETERS -> value * 100F
                UnitsOfMeasure.MILLIMETERS -> value * 1000F
            }
            UnitsOfMeasure.CENTIMETERS -> when (to) {
                UnitsOfMeasure.INCHES -> value / 2.54F
                UnitsOfMeasure.FEET -> value / 30.48F
                UnitsOfMeasure.METERS -> value * .01F
                UnitsOfMeasure.CENTIMETERS -> value
                UnitsOfMeasure.MILLIMETERS -> value * 10F
            }
            UnitsOfMeasure.MILLIMETERS -> when (to) {
                UnitsOfMeasure.INCHES -> value / 25.4F
                UnitsOfMeasure.FEET -> value / 304.8F
                UnitsOfMeasure.METERS -> value * .001F
                UnitsOfMeasure.CENTIMETERS -> value * .1F
                UnitsOfMeasure.MILLIMETERS -> value
            }
        }
        return copy(
            value = retval,
            unit = to
        )
    }
}
