package com.application.runoobapp.views.myReceiver;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;

public class ReceiverActivity extends AppCompatActivity {
    private static final String TAG = ReceiverActivity.class.getSimpleName();
    private static final String ACTION_FLAG = "com.runoob.receiver";
    private static final String DYNAMIC_FLAG = "com.runoob.dynamicReceiver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        //动态注册接收者 代码注册，不需要在manifest清单文件注册
        DynamicReceiver receiver = new DynamicReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(DYNAMIC_FLAG);
        registerReceiver(receiver,filter);

        Button sendButton = findViewById(R.id.btn_sendBroad);
        sendButton.setOnClickListener(v -> {
            Log.i(TAG, "static broadcast: button clicked!");
            Intent sendIntent = new Intent();
            sendIntent.setAction(ACTION_FLAG);
            //android 8以上对静态注册广播有限制，需要指定接收者包名
            sendIntent.setComponent(new ComponentName(this,CustomReceiver.class));
            sendBroadcast(sendIntent);
        });
    }

    /**
     * 动态注册发送广播
     * 1、定义接收者
     * 2、onCreate 注册接收者
     * 3、发送
     * @param view 视图
     */
    public void sendBroadcast(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(DYNAMIC_FLAG);
        sendBroadcast(sendIntent);
    }
}
