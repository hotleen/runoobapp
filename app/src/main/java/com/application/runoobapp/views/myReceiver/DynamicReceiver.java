package com.application.runoobapp.views.myReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DynamicReceiver extends BroadcastReceiver {
    private static final String TAG = DynamicReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: dynamic broadcast receiver!");
    }
}
