package com.just1guy.fieldfab.pages

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.just1guy.fieldfab.appstate.AppState
import com.just1guy.fieldfab.appstate.KotlinDefaultAppState
import com.just1guy.fieldfab.appstate.PreviewState
import com.just1guy.fieldfab.datapersistence.Duct

@Composable
fun DuctEditor(kAppData: KotlinDefaultAppState, state: MutableState<AppState>) {
    val faces = listOf("Front", "Back", "Left", "Right", "All")
    var currentFace by remember { mutableStateOf(0) }
    TabRow(
        items = faces,
        selectedTabIndex = currentFace
    ) { index, text ->
        Tab()
    }
}

@Preview(showBackground = true)
@Composable
fun DuctEditorPreview() {
    PreviewState { k, d ->
        DuctEditor(k, d)
    }
}