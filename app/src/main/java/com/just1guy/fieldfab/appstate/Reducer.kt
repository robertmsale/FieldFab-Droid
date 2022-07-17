//package com.just1guy.fieldfab.appstate
//
//import androidx.compose.ui.platform.LocalContext
//import com.just1guy.fieldfab.datapersistence.Duct
//import com.just1guy.fieldfab.datapersistence.DuctData
//import com.just1guy.fieldfab.datapersistence.DuctMeasurement
//import kotlinx.serialization.encodeToString
//import kotlinx.serialization.json.Json
//import org.reduxkotlin.Reducer
//import java.io.File
//
///**
// * Pure function that applies state changes
// */
//val reducer: Reducer<AppState> = { state: AppState, action ->
//    when (action) {
//        is Actions.INIT -> AppState.INITIAL_STATE
//        is Actions.ShowSheet -> state.copy(sheetsShown = action.type)
//        is Actions.SetCurrentPage -> state.copy(currentPage = action.to)
//        is Actions.ToggleSaveSuccessfulPopup -> state.copy(popupSaveSuccessful = action.to)
//        is Actions.AddDuctData -> {
//            state.copy(ductData = state.ductData.plus(action.data))
//        }
//        is Actions.RemoveDuctData -> {
//            state.copy(ductData = state.ductData.drop(action.atIndex))
//        }
//        is Actions.SetCurrentDuct -> {
//            if (action.toIndex == null)
//                state.copy(currentDuct = null, currentDuctIndex = null)
//                else state.copy(currentDuctIndex = action.toIndex, currentDuct = Duct.make(state.ductData[action.toIndex]))
//        }
//        is Actions.ChangeMeasurement -> {
//            val newDuctData: DuctData = when (action.ofType) {
//                DuctMeasurement.WIDTH -> state.currentDuct!!.data.copy(width = action.to)
//                DuctMeasurement.DEPTH -> state.currentDuct!!.data.copy(depth = action.to)
//                DuctMeasurement.LENGTH -> state.currentDuct!!.data.copy(length = action.to)
//                DuctMeasurement.OFFSET_X -> state.currentDuct!!.data.copy(offsetx = action.to)
//                DuctMeasurement.OFFSET_Y -> state.currentDuct!!.data.copy(offsety = action.to)
//                DuctMeasurement.T_WIDTH -> state.currentDuct!!.data.copy(twidth = action.to)
//                DuctMeasurement.T_DEPTH -> state.currentDuct!!.data.copy(tdepth = action.to)
//            }
//            state.copy(currentDuct = Duct.make(newDuctData))
//        }
//        is Actions.SaveCurrentDuct -> {
//            val newState = state.copy(
//                ductData = state.ductData.mapIndexed { index, ductData ->  if (index == state.currentDuctIndex) state.currentDuct!!.data else ductData },
//                popupSaveSuccessful = true
//            )
//            newState
//        }
//        is Actions.ClosePopupSaveSuccessful -> {
//            state.copy(popupSaveSuccessful = false)
//        }
//        is Actions.ChangeCurrentFace -> {
//            state.copy(currentFace = action.to)
//        }
//        else -> state
//    }
//}