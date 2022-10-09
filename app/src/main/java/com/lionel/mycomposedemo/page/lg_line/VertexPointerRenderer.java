package com.lionel.mycomposedemo.page.lg_line;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;


import com.lionel.mycomposedemo.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @anchor: andy
 * @date: 2018-11-02
 * @description:
 */
public class VertexPointerRenderer implements GLSurfaceView.Renderer {

    private Context context;
    private final FloatBuffer vertexBuffer;
    private final FloatBuffer alphaBuffer;

    private int mProgram;

    /**
     * 点的坐标
     */
    private float[] vertexPoints = new float[]{
            0.0f, 0.5f,
            -0.5f, -0.5f,
            0.5f, -0.5f,
            0.5f, -0.8f,
    };

    private float[] vertexPointsAlpha = new float[]{
            1.f, .5f, 1f, 0f
    };

    public VertexPointerRenderer(Context context) {
        this.context = context.getApplicationContext();
        //分配内存空间,每个浮点型占4字节空间
        vertexBuffer = ByteBuffer.allocateDirect(vertexPoints.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        //传入指定的坐标数据
        vertexBuffer.put(vertexPoints);
        vertexBuffer.position(0);


        alphaBuffer = ByteBuffer.allocateDirect(vertexPointsAlpha.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        alphaBuffer.put(vertexPointsAlpha);
        alphaBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //设置背景颜色
        GLES30.glClearColor(0f, 0f, 0f, 1f);
        //编译
        final int vertexShaderId = ShaderUtils.compileVertexShader(ResReadUtils.readResource(context, R.raw.vertex_pointer_shader));
        final int fragmentShaderId = ShaderUtils.compileFragmentShader(ResReadUtils.readResource(context, R.raw.fragment_pointer_shader));
        //鏈接程序片段
        mProgram = ShaderUtils.linkProgram(vertexShaderId, fragmentShaderId);
        //使用程序片段
        GLES30.glUseProgram(mProgram);


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
        vertexBuffer.position(0);
        //启用顶点属性
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glVertexAttribPointer(0, 2, GLES30.GL_FLOAT, false, 0, vertexBuffer);

        GLES30.glEnableVertexAttribArray(1);
        GLES30.glVertexAttribPointer(1, 1, GLES30.GL_FLOAT, false, 0, alphaBuffer);

        //绘制三个点
        GLES30.glDrawArrays(GLES30.GL_LINE_STRIP, 2, 2);
        GLES30.glDrawArrays(GLES30.GL_LINE_STRIP, 0, 2);

        GLES30.glDisableVertexAttribArray(0);
        GLES30.glDisableVertexAttribArray(1);

    }
}
