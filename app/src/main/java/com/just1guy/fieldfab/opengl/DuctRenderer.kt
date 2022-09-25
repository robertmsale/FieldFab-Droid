package com.just1guy.fieldfab.opengl

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import com.just1guy.fieldfab.R
import com.just1guy.fieldfab.math.GeometryComplete
import org.rajawali3d.Object3D
import org.rajawali3d.lights.DirectionalLight
import org.rajawali3d.materials.Material
import org.rajawali3d.materials.methods.DiffuseMethod
import org.rajawali3d.materials.textures.ATexture.TextureException
import org.rajawali3d.materials.textures.Texture
import org.rajawali3d.math.vector.Vector3
import org.rajawali3d.primitives.Sphere
import org.rajawali3d.renderer.Renderer

class DuctRenderer(context: Context, ductGeos: List<Object3D>): Renderer(context) {
	private lateinit var mEarthSphere: Sphere
	private lateinit var mDuctGeos: List<Object3D>
	private lateinit var mDirectionalLight: DirectionalLight

	init {
		mDuctGeos = ductGeos
		setFrameRate(60)
	}

	override fun onOffsetsChanged(
		xOffset: Float,
		yOffset: Float,
		xOffsetStep: Float,
		yOffsetStep: Float,
		xPixelOffset: Int,
		yPixelOffset: Int
	) {
//		TODO("Not yet implemented")
	}

	override fun onTouchEvent(event: MotionEvent?) {
//		TODO("Not yet implemented")
	}

	override fun initScene() {
		mDirectionalLight = DirectionalLight(0.0, 0.0, -1.0)
		mDirectionalLight.setColor(1.0f, 1.0f, 1.0f)
		mDirectionalLight.power = 2f
		currentScene.addLight(mDirectionalLight)
		val material = Material()
		material.enableLighting(true)
		material.diffuseMethod = DiffuseMethod.OrenNayar()
		material.colorInfluence = 0f
		val earthTexture: Texture = Texture("Galvanized", R.drawable.galvanized_diffuse)
		try {
			material.addTexture(earthTexture)
		} catch (error: TextureException) {
			Log.d("DuctRenderer.initScene", error.toString())
		}
		mEarthSphere = Sphere(1f, 24, 24)
		mEarthSphere.material = material
//		mDuctGeos[0].material = material
		for (obj in mDuctGeos) {
			obj.material = material
			currentScene.addChild(obj)
		}
//		currentScene.addChild(mEarthSphere)
		currentCamera.z = 2.5
	}

	override fun onRender(elapsedRealtime: Long, deltaTime: Double) {
		super.onRender(elapsedRealtime, deltaTime)
		for (obj in mDuctGeos) {
			obj.rotate(Vector3.Axis.Y, 1.0)
		}
//		mEarthSphere.rotate(Vector3.Axis.Y, 1.0)
	}
}
