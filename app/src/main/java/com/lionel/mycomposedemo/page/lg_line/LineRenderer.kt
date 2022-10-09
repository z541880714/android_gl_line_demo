package com.lionel.mycomposedemo.page.lg_line

import android.content.Context
import android.opengl.GLES30.*
import android.opengl.GLSurfaceView
import android.os.SystemClock
import android.util.Log
import com.lionel.mycomposedemo.R
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import kotlin.random.Random


private var mProgramId = 0
private var aPositionLoc: Int? = null
private val vertexPoints = floatArrayOf(
    10f, 20f,
    10f, 100f,
    200f, 250f,
    400f, 600f,
)
private val vertexAlphas = floatArrayOf(
    1f, .5f, 1f, 0f
)


class LineRenderer(val context: Context) : GLSurfaceView.Renderer {
    private var vWidth = 0f
    private var vHeight = 0f

    private val vertexBuffer =
        ByteBuffer.allocateDirect(vertexPoints.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer().apply {
                put(vertexPoints)
                position(0)
            }

    private val vertexAlphaBuffer = ByteBuffer.allocateDirect(vertexAlphas.size * 4)
        .order(ByteOrder.nativeOrder()).asFloatBuffer().apply {
            put(vertexAlphas)
            position(0)
        }

    private var uResolution: Int = 0

    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
        glClearColor(0f, 0f, 0f, 0f)
        val vertexShaderId = ShaderUtils.compileVertexShader(readRawFile(context, R.raw.line_vert))
        val fragShaderId = ShaderUtils.compileFragmentShader(readRawFile(context, R.raw.line_frag))
        mProgramId = ShaderUtils.linkProgram(vertexShaderId, fragShaderId)
        glUseProgram(mProgramId)

        uResolution = glGetUniformLocation(mProgramId, "resolution")
        Log.i("log_zc", "onSurfaceCreated: mProgramId: $mProgramId")
    }

    override fun onSurfaceChanged(p0: GL10?, width: Int, height: Int) {
        Log.i("log_zc", "onSurfaceChanged: !!!")
        vWidth = width.toFloat()
        vHeight = height.toFloat()

        glViewport(0, 0, width, height);
    }

    private var count = 0L;
    private var lastTime = 0L
    private val frameInterval = 120L
    override fun onDrawFrame(p0: GL10?) {
        if (++count % frameInterval == 0L) {
            val cur = SystemClock.elapsedRealtime()
            if (lastTime > 0)
                Log.i("log_zc", "onDrawFrame: ${frameInterval * 1000 / (cur - lastTime)}")
            lastTime = cur
        }
        glClear(GL_COLOR_BUFFER_BIT)
        glUniform2f(uResolution, vWidth, vHeight)

        glEnableVertexAttribArray(0)
        glEnableVertexAttribArray(1)


        repeat(2000) {
            vertexPoints[0] = Random.nextFloat() * 1000f
            vertexPoints[1] = Random.nextFloat() * 1800f
            vertexPoints[4] = Random.nextFloat() * 1000f
            vertexPoints[5] = Random.nextFloat() * 1800f
            vertexBuffer.position(0)
            vertexBuffer.put(vertexPoints)
            vertexBuffer.position(0)

            //启用位置顶点属性
            glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, vertexBuffer)
            glVertexAttribPointer(1, 1, GL_FLOAT, false, 0, vertexAlphaBuffer)

            glDrawArrays(GL_LINE_STRIP, 0, 2);
            glDrawArrays(GL_LINE_STRIP, 2, 2);

        }

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
    }
}

fun readRawFile(context: Context, resId: Int): String {
    context.resources.openRawResource(resId).use {
        return it.bufferedReader(charset = Charsets.UTF_8).readText()
    }
}

