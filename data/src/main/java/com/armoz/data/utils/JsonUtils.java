package com.armoz.data.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonUtils {

    public static String readJsonFromRawResource(Context context, int rawResourceId) {
        try {
            InputStream is = context.getResources().openRawResource(rawResourceId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            System.out.println(out.toString());
            return out.toString();
        } catch (Exception e) {
            throw new IllegalStateException("Error reading from raw fileId " + rawResourceId);
        }
    }
}
