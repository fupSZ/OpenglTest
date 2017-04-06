package com.example.androidtest.opengltest;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by fup on 2017/4/6.
 */
public class Activity2 extends GLBaseActivity {
    @Override
    void setRenderer() {
        glSurfaceView.setRenderer(new Air2Renderer(this));
    }
}
