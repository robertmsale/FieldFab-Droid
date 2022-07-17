package com.just1guy.fieldfab.appstate

//import android.content.res.Resources
import com.just1guy.fieldfab.datapersistence.DuctData
import com.just1guy.fieldfab.datapersistence.DuctFaceALL
import com.just1guy.fieldfab.datapersistence.DuctMeasurement

open class ActionType()

class Actions {
    class INIT: ActionType()
    class ShowSheet(val type: Sheets): ActionType()
    class SetCurrentPage(val to: String): ActionType()
    class ToggleSaveSuccessfulPopup(val to: Boolean): ActionType()
    class AddDuctData(val data: DuctData): ActionType()
    class RemoveDuctData(val atIndex: Int): ActionType()
    class SetCurrentDuct(val toIndex: Int?): ActionType()
    class ChangeMeasurement(val ofType: DuctMeasurement, val to: Float): ActionType()
    class SaveCurrentDuct(): ActionType()
    class ClosePopupSaveSuccessful(): ActionType()
    class ChangeCurrentFace(val to: DuctFaceALL): ActionType()
    class CreateDuct(val ductData: DuctData): ActionType()
}