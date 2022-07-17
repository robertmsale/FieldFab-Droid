package com.just1guy.fieldfab.appstate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun PreviewState(content: @Composable (KotlinDefaultAppState, MutableState<AppState>) -> Unit) {
    val kAppState = rememberKotlinDefaultAppState()
    val VM = remember { mutableStateOf(AppState()) }
    content(kAppState, VM)
}