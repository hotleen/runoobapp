package com.application.runoobapp.views.practice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.application.runoobapp.R;
import com.application.runoobapp.views.notification.NotificationActivity;

public class UIPracticePage extends AppCompatActivity {

    private static final String TAG = UIPracticePage.class.getSimpleName();
    private EditText et;
    private ProgressBar progressBar;
    private ProgressBar downloadBar;
    private NotificationManager manager;
    private Notification notification;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        infoBtnClick();
        btnClick();
    }

    private void btnClick() {
        Button btn = findViewById(R.id.btn);
        //button按钮事件：点击，长按，触摸，在xml注册事件也有同样的效果
        btn.setOnClickListener(v -> Log.e(TAG, "onClick: hello onclick event!"));

        btn.setOnLongClickListener(v -> {
            Log.i(TAG, "onLongClick: hello long click event！");
            return true;
        });

        /*
          @return 返回false不会阻止touch后续的click，longClick事件，返回true会阻止
         * 类似于冒泡事件
         */
        btn.setOnTouchListener((v, event) -> {
            Log.e(TAG, "onTouch: hello touch event!");
            return false;
        });
    }

    private void infoBtnClick() {
        Button infoBtn = findViewById(R.id.get_input_btn);
        et = findViewById(R.id.input_number);
        progressBar = findViewById(R.id.pb);
        downloadBar = findViewById(R.id.pb_horizontal);
        //通知管理器,系统服务
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //Android 8.0 以上支持状态栏通知
        NotificationChannel channel = new NotificationChannel("huzy", "Test notification!", NotificationManager.IMPORTANCE_HIGH);
        manager.createNotificationChannel(channel);

        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        notification = new NotificationCompat.Builder(this, "huzy")
                .setContentTitle("notification content title")
                .setContentText("this is an official content")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.river))
                .setColor(Color.parseColor("#ff0000"))
                .setContentIntent(pendingIntent) //点击跳转
                .setAutoCancel(true)  //点击后自动取消通知
                .build();


        //如果有类似于popup组件比打印效果要明显
        infoBtn.setOnClickListener(v -> {
            String info = et.getText().toString();
            Log.i(TAG, "info button onClick: " + info);
        });

    }

    /**
     * 改变加载条的显示/隐藏状态
     *
     * @param view 传入的视图
     */
    public void changeBarStatus(View view) {
        if (progressBar.getVisibility() == View.GONE) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void loadProgress(View view) {
        int value = downloadBar.getProgress();
        value += 10;
        if (value > 90) {
            value = 0;
        }
        downloadBar.setProgress(value);
    }

    /**
     * 推送通知
     *
     * @param view 视图
     */
    public void pushNotice(View view) {
        manager.notify(1, notification);
    }

    /**
     * 取消推送
     *
     * @param view 视图
     */
    public void cancelNotice(View view) {
        manager.cancel(1); //和notify 的id一致
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, UIPracticePage.class);
        context.startActivity(intent);
    }
}
