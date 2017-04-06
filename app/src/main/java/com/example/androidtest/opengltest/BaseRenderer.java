package com.example.androidtest.opengltest;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by fup on 2017/4/6.
 */
abstract public class BaseRenderer implements GLSurfaceView.Renderer {
    protected static final boolean DEBUG = true;
    protected static final String TAG = "AirRenderer";

    protected String vertexShaperSource;
    protected String fragmentShaperSource;
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        onBaseSurfaceChanged(gl, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        onBaseDrawFrame(gl);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        onBaseSurfaceCreated(gl, config);
    }
    abstract void onBaseSurfaceChanged(GL10 gl, int width, int height);
    abstract void onBaseDrawFrame(GL10 gl);
    abstract void onBaseSurfaceCreated(GL10 gl, EGLConfig config);

}
