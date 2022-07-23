package com.just1guy.fieldfab.pages

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.just1guy.fieldfab.appstate.AppState
import com.just1guy.fieldfab.appstate.KotlinDefaultAppState
import com.just1guy.fieldfab.appstate.PreviewState
import com.just1guy.fieldfab.components.DuctSideView
import com.just1guy.fieldfab.datapersistence.Duct
import com.just1guy.fieldfab.datapersistence.DuctData
import java.lang.NumberFormatException
import kotlin.math.min

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

@Composable
fun DuctEditor(kAppData: KotlinDefaultAppState, state: MutableState<AppState>) {
    val faces = listOf("Front", "Back", "Left", "Right", "All")
    var currentFace by remember { mutableStateOf(0) }
    var scrollOffset by remember { mutableStateOf(0F) }
    var width by remember { mutableStateOf(state.value.currentDuct!!.data.width.toString()) }
    var depth by remember { mutableStateOf(state.value.currentDuct!!.data.depth.toString()) }
    var length by remember { mutableStateOf(state.value.currentDuct!!.data.length.toString()) }
    var offsetx by remember { mutableStateOf(state.value.currentDuct!!.data.offsetx.toString()) }
    var offsety by remember { mutableStateOf(state.value.currentDuct!!.data.offsety.toString()) }
    var twidth by remember { mutableStateOf(state.value.currentDuct!!.data.twidth.toString()) }
    var tdepth by remember { mutableStateOf(state.value.currentDuct!!.data.tdepth.toString()) }
    val configuration = LocalConfiguration.current
    val size = min(configuration.screenHeightDp.dp, configuration.screenWidthDp.dp)

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
    ) {
        Box(modifier = Modifier
            .size(size)
            .absolutePadding(top = 42.dp)) {
            if (faces[currentFace] == "All") {
                Column {
                    Row {
                        DuctSideView(side = "Front", state = state, showMeasurements = false)
                        DuctSideView(side = "Back", state = state, showMeasurements = false)
                    }
                    Row {
                        DuctSideView(side = "Left", state = state, showMeasurements = false)
                        DuctSideView(side = "Right", state = state, showMeasurements = false)
                    }
                }
            } else
            DuctSideView(side = faces[currentFace], state = state)
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(state = rememberScrollState(0))
        ) {
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = width, onValueChange = { v ->
                try {
                    state.value = state.value.copy(currentDuct = Duct.make(state.value.currentDuct!!.data.copy(width = v.toFloat())))
                } catch (e: NumberFormatException) {}
                width = v
            }, label = {Text("Width")})
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = depth, onValueChange = { v ->
                try {
                    state.value = state.value.copy(currentDuct = Duct.make(state.value.currentDuct!!.data.copy(depth = v.toFloat())))
                } catch (e: NumberFormatException) {}
                depth = v
            }, label = {Text("Depth")})
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = length, onValueChange = { v ->
                try {
                    state.value = state.value.copy(currentDuct = Duct.make(state.value.currentDuct!!.data.copy(length = v.toFloat())))
                } catch (e: NumberFormatException) {}
                length = v
            }, label = {Text("Length")})
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = offsetx, onValueChange = { v ->
                try {
                    state.value = state.value.copy(currentDuct = Duct.make(state.value.currentDuct!!.data.copy(offsetx = v.toFloat())))
                } catch (e: NumberFormatException) {}
                offsetx = v
            }, label = {Text("Offset X")})
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = offsety, onValueChange = { v ->
                try {
                    state.value = state.value.copy(currentDuct = Duct.make(state.value.currentDuct!!.data.copy(offsety = v.toFloat())))
                } catch (e: NumberFormatException) {}
                offsety = v
            }, label = {Text("Offset Y")})
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = twidth, onValueChange = { v ->
                try {
                    state.value = state.value.copy(currentDuct = Duct.make(state.value.currentDuct!!.data.copy(twidth = v.toFloat())))
                } catch (e: NumberFormatException) {}
                twidth = v
            }, label = {Text("Transition Width")})
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = tdepth, onValueChange = { v ->
                try {
                    state.value = state.value.copy(currentDuct = Duct.make(state.value.currentDuct!!.data.copy(tdepth = v.toFloat())))
                } catch (e: NumberFormatException) {}
                tdepth = v
            }, label = {Text("Transition Depth")})
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