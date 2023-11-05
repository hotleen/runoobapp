package com.application.runoobapp.views.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Button startButton = findViewById(R.id.btn_start);
        Button stopButton = findViewById(R.id.btn_stop);
        Button bindButton = findViewById(R.id.btn_bind);
        Button unbindButton = findViewById(R.id.btn_unbind);
        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
        });

        stopButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MyService.class);
            stopService(intent);
        });

        bindButton.setOnClickListener(v -> bindService(
                new Intent(this, MyService.class),
                connection, Context.BIND_AUTO_CREATE));

        unbindButton.setOnClickListener(v -> unbindService(connection));


    }

    //activity销毁解绑service,要先bind在unbind 否则会闪退，最好这里做一次service判空处理
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    //activity 与 service桥梁
    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
