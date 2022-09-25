package com.just1guy.fieldfab.opengl

import android.content.Context
import org.rajawali3d.Object3D
import org.rajawali3d.view.ISurface
import org.rajawali3d.view.SurfaceView

class DuctRenderView(context: Context, ductGeos: List<Object3D>): SurfaceView(context) {
	private val renderer: DuctRenderer
//	private val mpos: Drag;

	init {
		renderer = DuctRenderer(context, ductGeos)
		setFrameRate(60.0)
		renderMode = ISurface.RENDERMODE_WHEN_DIRTY

		setSurfaceRenderer(renderer)
	}
}
