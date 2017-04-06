package com.example.androidtest.opengltest;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by fup on 2017/4/6.
 */
abstract public class GLBaseActivity extends AppCompatActivity {
    protected GLSurfaceView glSurfaceView;
    protected boolean rendererSet = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glSurfaceView = new GLSurfaceView(this);
        if(ES2Utils.checkSupportES2(this)) {
            glSurfaceView.setEGLContextClientVersion(2);
            setRenderer();
            rendererSet = true;
            setContentView(glSurfaceView);
        } else {
            Toast.makeText(this, "不支持ES2.0", Toast.LENGTH_SHORT).show();
        }
    }

    abstract void setRenderer();

    @Override
    protected void onPause() {
        super.onPause();
        if(rendererSet) {
            glSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(rendererSet) {
            glSurfaceView.onResume();
        }
    }
}
