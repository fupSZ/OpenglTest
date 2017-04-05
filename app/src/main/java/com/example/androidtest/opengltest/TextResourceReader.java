package com.example.androidtest.opengltest;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by fup on 2017/4/5.
 */
public class TextResourceReader {
    private static final String TAG = TextResourceReader.class.getSimpleName();
    public static String readTextFromResource(Context context, int resourceId) {
        StringBuilder stringBuilder = new StringBuilder();

        try{
            InputStream inputStream = context.getResources().openRawResource(resourceId);

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
        }catch (Exception e) {
            Log.v(TAG, e.getMessage());
        }

        return stringBuilder.toString();
    }
}
