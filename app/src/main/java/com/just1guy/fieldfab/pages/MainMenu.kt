package com.just1guy.fieldfab.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just1guy.fieldfab.appstate.AppState
import com.just1guy.fieldfab.appstate.KotlinDefaultAppState
import com.just1guy.fieldfab.datapersistence.Duct
import com.just1guy.fieldfab.datapersistence.DuctData

@Composable
fun MenuItem(data: DuctData, appState: MutableState<AppState>, kAppState: KotlinDefaultAppState, index: Int) {
    Column(Modifier.clickable {
        appState.value = appState.value.copy(
            currentPage = data.name,
            currentDuctIndex = index,
            currentDuct = Duct.make(data)
        )
        kAppState.navController.navigate("Duct-Editor")
    }) {
        LineBreak()
        Row(
            Modifier.fillMaxWidth().padding(14.dp),
            Arrangement.SpaceBetween, 
            verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(text = data.name)
                Text("Created on " + data.created)
            }
            Icon(Icons.Filled.ArrowForward, contentDescription = null)
        }
        LineBreak()
    }
}

@Composable
fun MainMenu(kAppState: KotlinDefaultAppState, appState: MutableState<AppState>) {
    Column {
        if (appState.value.ductData.size <= 0) {
            Text("Press the button below to create a new session")
        } else {
            appState.value.ductData.mapIndexed { index, ductData ->
                MenuItem(data = ductData, appState = appState, kAppState = kAppState, index = index)
            }
        }
    }
}