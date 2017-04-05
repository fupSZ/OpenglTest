package com.example.androidtest.opengltest;

import android.opengl.GLES20;
import android.util.Log;

/**
 * Created by fup on 2017/4/5.
 */
public class ShaperUtils {
    private static final String TAG = ShaperUtils.class.getSimpleName();
    public static int compileVertexShader(String shapeCode) {
        return complieShader(GLES20.GL_VERTEX_SHADER, shapeCode);
    }

    public static int compileFragmentShader(String shapeCode) {
        return complieShader(GLES20.GL_FRAGMENT_SHADER, shapeCode);
    }

    public static int complieShader(int type, String shaperCode) {
        int shaderId = GLES20.glCreateShader(type);

        if(shaderId == 0) {
            LogUtils.e(TAG, "can not create shaper id["+ shaderId +"] :" + GLES20.glGetError());
            return 0;
        }

        GLES20.glShaderSource(shaderId, shaperCode);//上传
        GLES20.glCompileShader(shaderId);

        if(checkCompileStatus(shaderId) == 0) {
            LogUtils.e(TAG, "the shader id ["+ shaderId+"] compile failed, shader info log:" + GLES20.glGetShaderInfoLog(shaderId));
            GLES20.glDeleteShader(shaderId);
            return 0;
        }
        return shaderId;
    }

    public static int linkPorgram(int vertexSharerId, int fragmentSharerId) {
        int programObjectId = GLES20.glCreateProgram();

        if(programObjectId == 0) {
            LogUtils.e(TAG, "can not create program");
            return 0;
        }

        GLES20.glAttachShader(programObjectId, vertexSharerId);
        GLES20.glAttachShader(programObjectId, fragmentSharerId);

        GLES20.glLinkProgram(programObjectId);

        if(checkLinkStatus(programObjectId) == 0) {
            GLES20.glDeleteProgram(programObjectId);

            LogUtils.e(TAG, "the program id ["+ programObjectId +"] compile failed");
            return 0;
        }

        return programObjectId;
    }

    public static boolean validateProgram(int programObjectId) {
        GLES20.glValidateProgram(programObjectId);

        int[] validate = new int[1];
        GLES20.glGetProgramiv(programObjectId, GLES20.GL_VALIDATE_STATUS, validate, 0);

        return validate[0] != 0;
    }

    private static int checkLinkStatus(int programObjectId) {
        int linkStatus[] = new int[1];
        GLES20.glGetProgramiv(programObjectId, GLES20.GL_LINK_STATUS, linkStatus, 0);

        return linkStatus[0];
    }

    private static int checkCompileStatus(int shaperId) {
        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shaperId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
        return compileStatus[0];
    }
}
