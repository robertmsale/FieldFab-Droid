package com.just1guy.fieldfab.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.just1guy.fieldfab.appstate.AppState
import com.just1guy.fieldfab.appstate.KotlinDefaultAppState
import com.just1guy.fieldfab.datapersistence.DuctFace
import com.just1guy.fieldfab.math.FaceGeometry
import com.just1guy.fieldfab.opengl.DuctRenderView
import com.just1guy.fieldfab.opengl.DuctRenderer

@Composable
fun ThreeDView(kAppData: KotlinDefaultAppState, state: MutableState<AppState>) {
	AndroidView(
		modifier = Modifier.fillMaxSize(),
		factory = { context ->
			val faces = listOf(DuctFace.FRONT, DuctFace.BACK, DuctFace.LEFT, DuctFace.RIGHT)
			DuctRenderView(context, faces.map {
				FaceGeometry.generate(it, state.value.currentDuct!!.outer.asList, false).geometry
			}).apply {

			}
		},
		update = { view ->

		}
	)
}
