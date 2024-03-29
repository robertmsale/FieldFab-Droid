package com.just1guy.fieldfab.appstate

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.base.rememberBooleanSettingState
import com.just1guy.fieldfab.datapersistence.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

//import org.reduxkotlin.createThreadSafeStore

/**
 * This class holds app state using Jetpack Composes State Holder method.
 * I personally don't like this style but am using it to maintain compatibility with
 * UI components such as NavHost and Scaffold as well as the Android settings framework
 */
class KotlinDefaultAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val isMetric: SettingValueState<Boolean>,
    val showDebugInfo: SettingValueState<Boolean>,
    val showHelpers: SettingValueState<Boolean>,
    val energySaver: SettingValueState<Boolean>,
    private val resources: Resources
) {

}

/**
 * I mean look... rewriting the same thing like 3 times?
 */
@Composable
fun rememberKotlinDefaultAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    isMetric: SettingValueState<Boolean> = rememberBooleanSettingState(),
    showDebugInfo: SettingValueState<Boolean> = rememberBooleanSettingState(),
    showHelpers: SettingValueState<Boolean> = rememberBooleanSettingState(),
    energySaver: SettingValueState<Boolean> = rememberBooleanSettingState(),
    resources: Resources = LocalContext.current.resources
) = remember(
    scaffoldState,
    navController,
    isMetric,
    showDebugInfo,
    showHelpers,
    energySaver,
    resources
) {
    KotlinDefaultAppState(
        scaffoldState,
        navController,
        isMetric,
        showDebugInfo,
        showHelpers,
        energySaver,
        resources
    )
}

/**
 * Now for my favorite state management framework :)
 * Using redux style immutable state reduction
 */
enum class Sheets {
    SHARE,
    HELP,
    HELP_WEB,
    ABOUT,
    AR_MENU,
    SHARED,
    LOAD,
    ADVANCED_SETTINGS,
    CAMERA_HELP,
    AR_HELP,
    NONE
}

enum class WorkingViews {
	WORKSHOP,
	THREE_D,
	AR
}

data class AppState(
	val sheetsShown: Sheets = Sheets.NONE,
	val popupSaveSuccessful: Boolean = false,
	val ductData: List<DuctData> = listOf(),
	val currentFace: DuctFaceALL = DuctFaceALL.FRONT,
	val currentDuctIndex: Int? = 0,
	val currentDuct: Duct? = null,
	val currentPage: String = "FieldFab",
	val lastPage: String = "FieldFab",
	val workingViews: WorkingViews = WorkingViews.WORKSHOP,
    ) {
    companion object {
        // TODO: Initialize certain values from persistent storage
        val INITIAL_STATE = AppState()
        val TEST_STATE = AppState(
            ductData = listOf(
                DuctData(
                    name = "Test Duct",
                    width = 1f,
                    depth = 1f,
                    length = 1f,
                    offsetx = 0.375f,
                    offsety = 0f,
                    twidth = 1f,
                    tdepth = 1f,
//                    tabsData = TabsData(
//                        front = TabSidesData(
//                            top = TabData(1, 0.0F),
//                            bottom = TabData(1, 0.0127F),
//                            left = TabData(1, 0.0254F),
//                            right = TabData(1, 0.0254F),
//                        )
//                    )
                )
            )
        )
    }
}
