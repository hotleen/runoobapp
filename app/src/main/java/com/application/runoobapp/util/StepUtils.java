package com.application.runoobapp.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.application.runoobapp.views.todaySteps.Step;

import java.lang.reflect.Method;
import java.util.LinkedList;

public class StepUtils {

    private static StepUtils instance;

    /* Data Field */
    public static final String ID = "_id";
    public static final String BEGIN_TIME = "_begin_time";
    public static final String END_TIME = "_end_time";
    // 0: NOT SUPPORT 1:REST 2:WALKING 3:RUNNING
    public static final String MODE = "_mode";
    public static final String STEPS = "_steps";
    /* Default sort order */
    public static final String DEFAULT_SORT_ORDER = "_id asc";
    /* Authority */
    public static final String AUTHORITY = "com.miui.providers.steps";
    /* Content URI */
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/item");

    public static String[] projection = new String[]{
            StepUtils.ID,
            StepUtils.BEGIN_TIME,
            StepUtils.END_TIME,
            StepUtils.MODE,
            StepUtils.STEPS
    };

    public static StepUtils getInstance() {
        if (instance == null) {
            instance = new StepUtils();
        }
        return instance;
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        try {
            Class featureParserClass = Class.forName("miui.util.FeatureParser");
            Method method = featureParserClass.getMethod("getBoolean", String.class, boolean.class);
            return (Boolean) method.invoke(null, name, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public LinkedList<Step> getAllSteps(Context context, String selection, String[] args) {
        LinkedList<Step> steps = new LinkedList<>();
        Cursor cursor = context.getContentResolver().query(StepUtils.CONTENT_URI, projection, selection, args,
                StepUtils.DEFAULT_SORT_ORDER);
        if (cursor.moveToFirst()) {
            do {
                Step s = new Step(cursor.getInt(0),
                        cursor.getLong(1),
                        cursor.getLong(2),
                        cursor.getInt(3),
                        cursor.getInt(4));
                steps.add(s);
            } while (cursor.moveToNext());
        }
        return steps;
    }
}
