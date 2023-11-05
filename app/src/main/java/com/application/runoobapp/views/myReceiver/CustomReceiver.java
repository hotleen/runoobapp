package com.application.runoobapp.views.myReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CustomReceiver extends BroadcastReceiver {
    /**
     * 1.注册静态接收者
     * 2.发送
     */
    private static final String TAG = "CUSTOM_RECEIVER";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: receive message!");
    }
}
