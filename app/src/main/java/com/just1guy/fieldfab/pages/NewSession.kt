package com.just1guy.fieldfab.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*

import com.just1guy.fieldfab.appstate.AppState
import com.just1guy.fieldfab.appstate.KotlinDefaultAppState
import com.just1guy.fieldfab.appstate.rememberKotlinDefaultAppState
import com.just1guy.fieldfab.datapersistence.Duct
import com.just1guy.fieldfab.datapersistence.DuctData

@Composable
fun NewSession(kAppState: KotlinDefaultAppState, state: MutableState<AppState>) {
    var name by remember { mutableStateOf("") }
    Column(Modifier.padding(all = 20.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Session Name") },
        )
        Button(onClick = {
            val newDuct = DuctData(
                name,
                width = 0.3048F,
                depth = 0.3048F,
                length = 0.3048F,
                offsetx = 0F,
                offsety = 0F,
                twidth = 0.3048F,
                tdepth = 0.3048F
            )
            state.value = state.value.copy(
                ductData = state.value.ductData.plus(newDuct),
                currentPage = name,
                currentDuct = Duct.make(newDuct),
                currentDuctIndex = state.value.ductData.size
            )
            kAppState.navController.navigate("Duct-Editor") {
                popUpTo("FieldFab")
            }
        }) {
            Text("Create")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewSessionPreview() {
    var k = rememberKotlinDefaultAppState()
    var s = remember { mutableStateOf(AppState())}
    NewSession(kAppState = k, state = s)
}