package com.just1guy.fieldfab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.just1guy.fieldfab.appstate.*
import com.just1guy.fieldfab.pages.MainMenu
import com.just1guy.fieldfab.pages.NewSession
import com.just1guy.fieldfab.ui.theme.FieldFabTheme
import com.just1guy.fieldfab.pages.SettingsPage
//import org.reduxkotlin.Store
//import org.reduxkotlin.createThreadSafeStore

@Composable
fun TopBar(kState: KotlinDefaultAppState, title: String) {
    when (kState.navController.currentDestination?.route) {
        "main-menu" -> TopAppBar(
            title = { Text(title) },
            actions = {
                IconButton(onClick = { kState.navController.navigate(route = "settings-page") }) {
                    Icon(Icons.Filled.Settings, contentDescription = null)
                }
            }
        )
        "settings-page" -> TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                IconButton(onClick = {
                    kState.navController.navigateUp()
                }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
        )
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FieldFabTheme {
                val kAppState = rememberKotlinDefaultAppState()
                val VM = remember { mutableStateOf(AppState()) }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = VM.value.currentPage)},
                                navigationIcon = {
                                    if (VM.value.currentPage != "FieldFab") IconButton(onClick = {
                                        kAppState.navController.navigateUp()
                                        VM.value = VM.value.copy(currentPage = kAppState.navController.currentBackStackEntry?.destination?.route ?: "FieldFab")
                                    }) {
                                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                                    } else {}
                                },
                                actions = {
                                    IconButton(onClick = {
                                        VM.value = VM.value.copy(currentPage = "Settings", lastPage = VM.value.currentPage)
                                        kAppState.navController.navigate(route = "settings-page")
                                    }) {
                                        Icon(Icons.Filled.Settings, contentDescription = null)
                                    }
                                }
                            )
                        },
                        floatingActionButton = {
                            if (VM.value.currentPage == "FieldFab")
                            FloatingActionButton(onClick = {
                                kAppState.navController.navigate("New-Session")
                                VM.value = VM.value.copy(currentPage = "New Session")
                            }) {
                                Icon(Icons.Filled.Create, contentDescription = null)
                            } else {}
                        },
                        bottomBar = {

                        }
                    ) { innerPadding ->
                        NavHost(navController = kAppState.navController, startDestination = "FieldFab", Modifier.padding(innerPadding)) {
                            composable("FieldFab") {
                                MainMenu(kAppState = kAppState, appState = VM)
                            }
                            composable("Settings-Page") {
                                SettingsPage(kAppState)
                            }
                            composable("New-Session") {
                                NewSession(kAppState = kAppState, state = VM)
                            }
                            composable("Duct-Editor") {
                                Text("You are now editing duct")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainActivity()
}