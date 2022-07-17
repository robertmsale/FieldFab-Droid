package com.just1guy.fieldfab.pages

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.alorma.compose.settings.ui.SettingsCheckbox
import com.just1guy.fieldfab.appstate.AppState
import com.just1guy.fieldfab.appstate.KotlinDefaultAppState

@Composable
fun LineBreak() {
    Canvas(modifier = Modifier.fillMaxWidth()) {
        val w = size.width
        drawLine(
            color = Color.LightGray,
            start = Offset(x = w, y = 0f),
            end = Offset(x = 0f, y = 0f)
        )
    }
}
@Composable
fun SettingsPage(kotlinDefaultAppState: KotlinDefaultAppState) {
    Column {
        SettingsCheckbox(
            title = { Text("Units of Measurement") },
            subtitle = { if (kotlinDefaultAppState.isMetric.value) Text("Metric") else Text(text = "Imperial") },
            state = kotlinDefaultAppState.isMetric
        ) {}
        LineBreak()
        SettingsCheckbox(
            title = { Text("Show debug info") },
            state = kotlinDefaultAppState.showDebugInfo
        ) {}
        LineBreak()
        SettingsCheckbox(
            title = { Text("Show helpers") },
            state = kotlinDefaultAppState.showHelpers
        ) {}
        LineBreak()
        SettingsCheckbox(
            title = { Text("Energy saver mode") },
            subtitle = { Text("This reduces rendering performance in exchange for better battery life") },
            state = kotlinDefaultAppState.energySaver
        ) {}
        LineBreak()
    }
}