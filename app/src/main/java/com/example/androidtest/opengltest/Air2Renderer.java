package com.example.androidtest.opengltest;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by fup on 2017/4/6.
 */
public class Air2Renderer extends BaseRenderer {
    private static final int BYTE_PER_FLOAT = 4;
    private FloatBuffer vertexData;

    private static final String A_POSITION = "position";
    private int uColorLocation;

    private int aPositionLocation;
    public Air2Renderer(Context context) {
        float[] vertices = {
                0.5f, -0.5f, 0.0f,  1.0f, 0.0f, 0.0f,   // 右下
                -0.5f, -0.5f, 0.0f,  0.0f, 1.0f, 0.0f,   // 左下
                0.0f,  0.5f, 0.0f,  0.0f, 0.0f, 1.0f    // 顶部
        };

        vertexData = ByteBuffer.allocateDirect(vertices.length * BYTE_PER_FLOAT).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(vertices);

        vertexShaperSource = TextResourceReader.readTextFromResource(context, R.raw.activity2_vertex_shader);
        fragmentShaperSource = TextResourceReader.readTextFromResource(context, R.raw.activity2_fragment_shader);
    }

    @Override
    void onBaseDrawFrame(GL10 gl) {

    }

    @Override
    void onBaseSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    void onBaseSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0, 0,0,0);

        int vertexShaper = ShaperUtils.compileVertexShader(vertexShaperSource);
        int fragmentShaper = ShaperUtils.compileFragmentShader(fragmentShaperSource);

        int programId = ShaperUtils.linkPorgram(vertexShaper, fragmentShaper);
        if(DEBUG && !ShaperUtils.validateProgram(programId)) {
            LogUtils.e(TAG, "validateProgram faile");
            return;
        }

        GLES20.glUseProgram(programId);
        aPositionLocation = GLES20.glGetAttribLocation(programId, A_POSITION);
        vertexData.position(0);
        GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, 6 * BYTE_PER_FLOAT, vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);
// 颜色属性
        vertexData.position(3);
        GLES20.glVertexAttribPointer(1, 3, GLES20.GL_FLOAT, false, 6 * BYTE_PER_FLOAT, vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);
    }
}
