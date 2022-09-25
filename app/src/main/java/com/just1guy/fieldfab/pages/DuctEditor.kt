package com.just1guy.fieldfab.pages

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.just1guy.fieldfab.appstate.AppState
import com.just1guy.fieldfab.appstate.KotlinDefaultAppState
import com.just1guy.fieldfab.appstate.PreviewState
import com.just1guy.fieldfab.components.DuctSideView
import com.just1guy.fieldfab.datapersistence.Duct
import com.just1guy.fieldfab.datapersistence.DuctData
import com.just1guy.fieldfab.datapersistence.UnitsOfMeasure
import com.just1guy.fieldfab.math.LengthMeasurement

@Composable
fun WorkshopLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(modifier = modifier) { measurables, constraints ->
        val placeables = measurables.map {
            it.measure(constraints)
        }
        layout(constraints.maxWidth, constraints.maxHeight) {
            var yPosition = 0

            placeables.forEachIndexed { index, placeable ->
                placeable.placeRelative(0, yPosition)
                yPosition += constraints.maxHeight / 2
            }
        }
    }
}

enum class Keyboard { OPENED, CLOSED }

@Composable
fun keyboardAsState(): State<Keyboard> {
    val keyboardState = remember{ mutableStateOf(Keyboard.CLOSED)}
    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenH = view.rootView.height
            val keypadH = screenH - rect.bottom
            keyboardState.value = if (keypadH > screenH * 0.15) Keyboard.OPENED else Keyboard.CLOSED
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }
    return keyboardState
}

@Composable
fun DuctEditor(kAppData: KotlinDefaultAppState, state: MutableState<AppState>) {
    val faces = listOf("Front", "Back", "Left", "Right", "All")
    var currentFace by remember { mutableStateOf(0) }
    var scrollOffset by remember { mutableStateOf(0F) }
    var width by remember { mutableStateOf(LengthMeasurement(state.value.currentDuct!!.data.width, UnitsOfMeasure.METERS).convert(if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES).value.toString()) }
    var depth by remember { mutableStateOf(LengthMeasurement(state.value.currentDuct!!.data.depth, UnitsOfMeasure.METERS).convert(if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES).value.toString()) }
    var length by remember { mutableStateOf(LengthMeasurement(state.value.currentDuct!!.data.length, UnitsOfMeasure.METERS).convert(if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES).value.toString()) }
    var offsetx by remember { mutableStateOf(LengthMeasurement(state.value.currentDuct!!.data.offsetx, UnitsOfMeasure.METERS).convert(if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES).value.toString()) }
    var offsety by remember { mutableStateOf(LengthMeasurement(state.value.currentDuct!!.data.offsety, UnitsOfMeasure.METERS).convert(if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES).value.toString()) }
    var twidth by remember { mutableStateOf(LengthMeasurement(state.value.currentDuct!!.data.twidth, UnitsOfMeasure.METERS).convert(if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES).value.toString()) }
    var tdepth by remember { mutableStateOf(LengthMeasurement(state.value.currentDuct!!.data.tdepth, UnitsOfMeasure.METERS).convert(if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES).value.toString()) }
    val configuration = LocalConfiguration.current
    val kbState by keyboardAsState()
    val sideViewSize =
		min(configuration.screenHeightDp.dp, configuration.screenWidthDp.dp)
			.div(if (kbState == Keyboard.OPENED) 6.dp else 1.dp).dp


    TabRow(
        selectedTabIndex = currentFace,
        modifier = Modifier.requiredHeight(42.dp)
    ) {
        faces.forEachIndexed { index, title ->
            Tab(selected = index == currentFace,
                onClick = {
                currentFace = index
            }) {
                Text(title)
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .absolutePadding(top = 42.dp)
    ) {
        Box(modifier = Modifier
            .size(sideViewSize)
        ) {
            if (faces[currentFace] == "All") {
                Column {
                    Row {
                        DuctSideView(side = "Front", state = state, showMeasurements = false, kAppData = kAppData, modifier = Modifier.size(sideViewSize.div(2)))
                        DuctSideView(side = "Back", state = state, showMeasurements = false, kAppData = kAppData, modifier = Modifier.size(sideViewSize.div(2)))
                    }
                    Row {
                        DuctSideView(side = "Left", state = state, showMeasurements = false, kAppData = kAppData, modifier = Modifier.size(sideViewSize.div(2)))
                        DuctSideView(side = "Right", state = state, showMeasurements = false, kAppData = kAppData, modifier = Modifier.size(sideViewSize.div(2)))
                    }
                }
            } else
            DuctSideView( side = faces[currentFace], state = state, kAppData = kAppData, modifier = Modifier.size(sideViewSize))
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(state = rememberScrollState(0))
        ) {
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = width, onValueChange = { v ->
                try {
                    val num = LengthMeasurement(v.toFloat(), if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES).convert(UnitsOfMeasure.METERS)
                    state.value = state.value.copy(currentDuct = Duct.make(state.value.currentDuct!!.data.copy(width = num.value)))
                } catch (e: NumberFormatException) {}
                width = v
            }, label = {Text("Width")}, trailingIcon = {Text(if (kAppData.isMetric.value) "mm" else "inches")})
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = depth, onValueChange = { v ->
                try {
                    val num = LengthMeasurement(v.toFloat(), if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES).convert(UnitsOfMeasure.METERS)
                    state.value = state.value.copy(currentDuct = Duct.make(state.value.currentDuct!!.data.copy(depth = num.value)))
                } catch (e: NumberFormatException) {}
                depth = v
            }, label = {Text("Depth")}, trailingIcon = {Text(if (kAppData.isMetric.value) "mm" else "inches")})
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = length, onValueChange = { v ->
                try {
                    val num = LengthMeasurement(v.toFloat(), if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES).convert(UnitsOfMeasure.METERS)
                    state.value = state.value.copy(currentDuct = Duct.make(state.value.currentDuct!!.data.copy(length = num.value)))
                } catch (e: NumberFormatException) {}
                length = v
            }, label = {Text("Length")}, trailingIcon = {Text(if (kAppData.isMetric.value) "mm" else "inches")})
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = offsetx, onValueChange = { v ->
                try {
                    val num = LengthMeasurement(v.toFloat(), if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES).convert(UnitsOfMeasure.METERS)
                    state.value = state.value.copy(currentDuct = Duct.make(state.value.currentDuct!!.data.copy(offsetx = num.value)))
                } catch (e: NumberFormatException) {}
                offsetx = v
            }, label = {Text("Offset X")}, trailingIcon = {Text(if (kAppData.isMetric.value) "mm" else "inches")})
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = offsety, onValueChange = { v ->
                try {
                    val num = LengthMeasurement(v.toFloat(), if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES).convert(UnitsOfMeasure.METERS)
                    state.value = state.value.copy(currentDuct = Duct.make(state.value.currentDuct!!.data.copy(offsety = num.value)))
                } catch (e: NumberFormatException) {}
                offsety = v
            }, label = {Text("Offset Y")}, trailingIcon = {Text(if (kAppData.isMetric.value) "mm" else "inches")})
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = twidth, onValueChange = { v ->
                try {
                    val num = LengthMeasurement(v.toFloat(), if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES).convert(UnitsOfMeasure.METERS)
                    state.value = state.value.copy(currentDuct = Duct.make(state.value.currentDuct!!.data.copy(twidth = num.value)))
                } catch (e: NumberFormatException) {}
                twidth = v
            }, label = {Text("Transition Width")}, trailingIcon = {Text(if (kAppData.isMetric.value) "mm" else "inches")})
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = tdepth, onValueChange = { v ->
                try {
                    val num = LengthMeasurement(v.toFloat(), if (kAppData.isMetric.value) UnitsOfMeasure.MILLIMETERS else UnitsOfMeasure.INCHES).convert(UnitsOfMeasure.METERS)
                    state.value = state.value.copy(currentDuct = Duct.make(state.value.currentDuct!!.data.copy(tdepth = num.value)))
                } catch (e: NumberFormatException) {}
                tdepth = v
            }, label = {Text("Transition Depth")}, trailingIcon = {Text(if (kAppData.isMetric.value) "mm" else "inches")})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DuctEditorPreview() {
    PreviewState { k, d ->
        d.value = d.value.copy(
            currentDuct = Duct.make(DuctData(
                name = "Derp",
                width = 0.05F,
                depth = 0.05F,
                length = 0.05F,
                offsetx = 0.0F,
                offsety = 0.0F,
                twidth = 0.05F,
                tdepth = 0.05F
            ))
        )
        DuctEditor(k, d)
    }
}
