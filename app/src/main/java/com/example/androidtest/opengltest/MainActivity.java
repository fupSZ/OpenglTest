package com.example.androidtest.opengltest;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends GLBaseActivity {
    @Override
    void setRenderer() {
        glSurfaceView.setRenderer(new AirRenderer(this));
    }
}
