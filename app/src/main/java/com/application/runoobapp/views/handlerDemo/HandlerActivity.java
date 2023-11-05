package com.application.runoobapp.views.handlerDemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;

public class HandlerActivity extends AppCompatActivity {

    private static final String TAG = HandlerActivity.class.getSimpleName();

    private Button send_btn;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //这里接受并处理消息
            Log.d(TAG, "handleMessage: " + msg.what);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        this.send_btn = findViewById(R.id.handler_btn_send);
        this.send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            //子线程向主线程发送
                            Message message = Message.obtain();
                            message.what = 1;
                            handler.sendMessage(message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
            }
        });
    }
}
