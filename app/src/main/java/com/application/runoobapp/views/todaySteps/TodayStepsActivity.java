package com.application.runoobapp.views.todaySteps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;
import com.application.runoobapp.base.action.ToastAction;
import com.application.runoobapp.util.DateUtils;
import com.application.runoobapp.util.StepUtils;

import java.util.ArrayList;
import java.util.List;

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
        boolean isSupport = StepUtils.getInstance().getBoolean("support_steps_provider", false);
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
        new Thread(() -> {
            try {
                List<Step> steps = StepUtils.getInstance().getAllSteps(TodayStepsActivity.this, null, null);
                if (steps == null || steps.size() == 0) {
                    return;
                }
                StepInfo stepInfo = null;
                List<StepInfo> stepInfoList = new ArrayList<>();
                for (int i = 0; i < steps.size(); i++) {
                    Step singleStep = steps.get(i);
                    if (stepInfo == null) {
                        stepInfo = new StepInfo(singleStep.getmSteps(), DateUtils.getDateFormatResult(singleStep.getmBeginTime()));
                    } else {
                        String dateResult = DateUtils.getDateFormatResult(singleStep.getmBeginTime());
                        if (TextUtils.equals(dateResult, stepInfo.getDataInfo())) {
                            stepInfo.setStepCount(stepInfo.getStepCount() + singleStep.getmSteps());
                        } else {
                            stepInfoList.add(stepInfo);
                            stepInfo = null;
                        }
                    }
                }
                Log.i(TAG, "getTodaySteps: stepInfo size: " + stepInfoList.size());
                int maxCount = 0;
                for (int i = 0; i < stepInfoList.size(); i++) {
                    StepInfo info = stepInfoList.get(i);
                    if (info.getStepCount() > maxCount) {
                        maxCount = info.getStepCount();
                    }
                    if (info.getStepCount() > 10000) {
                        Log.i(TAG, " date is " + info.getDataInfo() + " steps is " + info.getStepCount());
                    }
                }
                Log.i(TAG, "getTodaySteps: max step count is: " + maxCount);

            } catch (Exception e) {
                Log.i(TAG, "getTodaySteps: err " + e.getMessage());
            }

        }).start();
    }
}
