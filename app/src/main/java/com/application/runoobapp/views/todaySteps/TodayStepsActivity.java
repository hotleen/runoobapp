package com.application.runoobapp.views.todaySteps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;
import com.application.runoobapp.base.action.ToastAction;
import com.application.runoobapp.util.StepUtils;

public class TodayStepsActivity extends AppCompatActivity implements ToastAction {

    private final static String TAG = TodayStepsActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, TodayStepsActivity.class);
        context.startActivity(intent);
    }


    public void getStepStatus(View view) {
        //在功能开始之前判断是否支持stepsProvider功能
        boolean isSupport= StepUtils.getInstance().getBoolean("support_steps_provider",false);
        Log.i(TAG, "getStepStatus: " + isSupport);
        String tipMsg = "";
        if (isSupport) {
            tipMsg = "当前设备支持计步功能！";
        } else {
            tipMsg = "当前设备不支持计步功能！";
        }
        toast(tipMsg);
    }


    public void getTodaySteps(View view) {
        StepUtils.getInstance().getAllSteps(this, null, null);
    }
}
