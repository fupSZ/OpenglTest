package com.example.androidtest.opengltest;

import android.opengl.GLES20;
import android.opengl.GLU;

/**
 * Created by fup on 2017/4/5.
 */
public class LogUtils {
    public static final void e(String tag, String msg) {
        throw new RuntimeException(msg + ",reason:" + GLU.gluErrorString(GLES20.glGetError())+
                ",error num is:" + GLES20.glGetError());
    }
}
