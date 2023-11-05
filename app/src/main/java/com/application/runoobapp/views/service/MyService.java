package com.application.runoobapp.views.service;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MY_SERVICE";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: my service create!");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: my service start command!");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: my service destroyed!");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: bind my service!");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: unbind my service!");
        return super.onUnbind(intent);
    }
}
