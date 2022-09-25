package com.just1guy.fieldfab.opengl

import android.opengl.GLES20.*
import java.nio.IntBuffer

class ShaderHelper {
	companion object {
		fun compileVertexShader(shaderCode: String): Int {
			return compileShader(GL_VERTEX_SHADER, shaderCode)
		}
		fun compileFragmentShader(shaderCode: String): Int {
			return compileShader(GL_FRAGMENT_SHADER, shaderCode)
		}

		private fun compileShader(type: Int, code: String): Int {
			val shaderObjectId = glCreateShader(type)

			if (shaderObjectId == 0) {
				return 0
			}

			glShaderSource(shaderObjectId, code)
			glCompileShader(shaderObjectId)
			val cmpStatus = intArrayOf(0)
			glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, cmpStatus, 0)
			if (cmpStatus[0] == 0) {
				glDeleteShader(shaderObjectId)
				return 0
			}
			return shaderObjectId
		}

		fun linkProgram(vertexShaderId: Int, fragmentShaderId: Int): Int {
			val programObjectId = glCreateProgram()
			if (programObjectId == 0) {
				return 0
			}
			glLinkProgram(programObjectId)

			val linkStatus = intArrayOf(0)
			glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0)

			if (linkStatus[0] == 0) {
				glDeleteProgram(programObjectId)
				return 0
			}
			return programObjectId
		}

		fun validateProgram(programId: Int): Boolean {
			glValidateProgram(programId)

			val validStatus = intArrayOf(0)
			glGetProgramiv(programId, GL_VALIDATE_STATUS, validStatus, 0)
			return validStatus[0] != 0
		}
	}
}
