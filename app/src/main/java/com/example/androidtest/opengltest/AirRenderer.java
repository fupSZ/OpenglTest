package com.example.androidtest.opengltest;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by fup on 2017/4/5.
 */
public class AirRenderer extends BaseRenderer {
    private static final String U_COLOR = "u_Color";
    private static final String A_POSITION = "a_Position";
    private int uColorLocation;

    private int aPositionLocation;
    private FloatBuffer vertexData;
    private static final int BYTE_PER_FLOAT = 4;

    private static final int POSITION_COMPONENT_COUNT = 2;
    public AirRenderer(Context context) {
        float[] tableVertices = {
               /* 0, 0,
                9f, 14f,
                0, 14f,

                0, 0,
                9f, 0,
                9f, 14f,

                0f, 7f,
                9f, 7f,
                4.5f, 2f,
                4.5f, 12f*/

                // Triangle 1
                -0.5f, -0.5f,
                0.5f,  0.5f,
                -0.5f,  0.5f,

                // Triangle 2
                -0.5f, -0.5f,
                0.5f, -0.5f,
                0.5f,  0.5f,

                // Line 1
                -0.5f, 0f,
                0.5f, 0f,

                // Mallets
                0f, -0.25f,
                0f,  0.25f
        };

        vertexData = ByteBuffer.allocateDirect(tableVertices.length * BYTE_PER_FLOAT).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(tableVertices);

        vertexShaperSource = TextResourceReader.readTextFromResource(context, R.raw.simple_vertex_shaper);
        fragmentShaperSource = TextResourceReader.readTextFromResource(context, R.raw.simple_fragment_shaper);
    }
    @Override
    public void onBaseDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        GLES20.glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);

        GLES20.glUniform4f(uColorLocation, 1.0f, 0, 0, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_LINES, 6, 2);

        GLES20.glUniform4f(uColorLocation, 0, 0, 1.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 8, 1);

        GLES20.glUniform4f(uColorLocation, 1.0f, 0, 0, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 9, 1);
    }

    @Override
    public void onBaseSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onBaseSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0, 0,0,0);

        int vertexShaper = ShaperUtils.compileVertexShader(vertexShaperSource);
        int fragmentShaper = ShaperUtils.compileFragmentShader(fragmentShaperSource);

        int programId = ShaperUtils.linkPorgram(vertexShaper, fragmentShaper);

        if(DEBUG && !ShaperUtils.validateProgram(programId)) {
            LogUtils.e(TAG, "validateProgram faile");
            return;
        }

        GLES20.glUseProgram(programId);
        uColorLocation = GLES20.glGetUniformLocation(programId, U_COLOR);
        aPositionLocation = GLES20.glGetAttribLocation(programId, A_POSITION);

        vertexData.position(0);
        GLES20.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false, 0, vertexData);

        GLES20.glEnableVertexAttribArray(aPositionLocation);
    }
}
