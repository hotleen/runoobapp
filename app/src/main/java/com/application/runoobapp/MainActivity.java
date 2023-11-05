package com.application.runoobapp;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.application.runoobapp.views.amap.AmapDemoActivity;
import com.application.runoobapp.views.animation.AnimationActivity;
import com.application.runoobapp.views.animation.ValueAnimatorActivity;
import com.application.runoobapp.views.animation.ViewAnimationActivity;
import com.application.runoobapp.views.contentProvider.ContactsDemoActivity;
import com.application.runoobapp.views.countdown.CountDownActivity;
import com.application.runoobapp.views.fragment.FragmentActivity;
import com.application.runoobapp.views.glideImage.ImageLoadActivity;
import com.application.runoobapp.views.homePage.ui.activity.HomeActivity;
import com.application.runoobapp.views.housecountdown.HouseCountDownActivity;
import com.application.runoobapp.views.layoutPage.ConstraintActivity;
import com.application.runoobapp.views.layoutPage.FrameActivity;
import com.application.runoobapp.views.layoutPage.GridActivity;
import com.application.runoobapp.views.layoutPage.RelativeActivity;
import com.application.runoobapp.views.layoutPage.TableActivity;
import com.application.runoobapp.views.media.MediaPlayerActivity;
import com.application.runoobapp.views.media.MediaRecordActivity;
import com.application.runoobapp.views.media.SoundPoolActivity;
import com.application.runoobapp.views.media.VideoViewActivity;
import com.application.runoobapp.views.myReceiver.ReceiverActivity;
import com.application.runoobapp.views.myRetrofit.RetrofitActivity;
import com.application.runoobapp.views.mySqlite.SqliteDemoActivity;
import com.application.runoobapp.views.newTarget.NewTargetActivity;
import com.application.runoobapp.views.notification.NotificationActivity;
import com.application.runoobapp.views.okRequest.RequestActivity;
import com.application.runoobapp.views.popupWindow.PopupActivity;
import com.application.runoobapp.views.recycler.RecycleActivity;
import com.application.runoobapp.views.roundDemo.RoundActivity;
import com.application.runoobapp.views.rxJava.RXActivity;
import com.application.runoobapp.views.service.ServiceActivity;
import com.application.runoobapp.views.sharedPreference.SPActivity;
import com.application.runoobapp.views.spDemo.SPDemoActivity;
import com.application.runoobapp.views.todaySteps.TodayStepsActivity;
import com.application.runoobapp.views.viewpager.ViewPagerActivity;
import com.application.runoobapp.views.webview.WebViewActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "runoobApp";
    private EditText et;
    private ProgressBar progressBar;
    private ProgressBar downloadBar;
    private NotificationManager manager;
    private Notification notification;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
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

        //button按钮事件：点击，长按，触摸，在xml注册事件也有同样的效果
        btn.setOnClickListener(v -> Log.e(TAG, "onClick: hello onclick event!"));

        btn.setOnLongClickListener(v -> {
            Log.e(TAG, "onLongClick: hello long click event！");
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


    public void jumpPageAction(View view) {
        //显示跳转意图
        Intent jumpIntent = new Intent(this, CountDownActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转相对布局
     *
     * @param view 视图
     */
    public void jumpRelativePageAction(View view) {
        //显示跳转意图
        Intent jumpIntent = new Intent(this, RelativeActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转帧布局
     *
     * @param view 视图
     */
    public void jumpFramePageAction(View view) {
        Intent jumpIntent = new Intent(this, FrameActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转表格布局
     *
     * @param view 视图
     */
    public void jumpTablePageAction(View view) {
        Intent jumpIntent = new Intent(this, TableActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转网格布局
     *
     * @param view 视图
     */
    public void jumpGridPageAction(View view) {
        Intent jumpIntent = new Intent(this, GridActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转网格布局
     *
     * @param view 视图
     */
    public void jumpConstraintPageAction(View view) {
        Intent jumpIntent = new Intent(this, ConstraintActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转网格布局
     *
     * @param view 视图
     */
    public void jumpRecyclePageAction(View view) {
        Intent jumpIntent = new Intent(this, RecycleActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转帧动画
     *
     * @param view 视图
     */
    public void jumpAnimationPageAction(View view) {
        Intent jumpIntent = new Intent(this, AnimationActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转补间动画
     *
     * @param view 视图
     */
    public void jumpViewAnimationPageAction(View view) {
        Intent jumpIntent = new Intent(this, ViewAnimationActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转属性动画
     *
     * @param view 视图
     */
    public void jumpValueAnimatorPageAction(View view) {
        Intent jumpIntent = new Intent(this, ValueAnimatorActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转viewpager
     *
     * @param view 视图
     */
    public void jumpViewPagerAction(View view) {
        Intent jumpIntent = new Intent(this, ViewPagerActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转fragment
     *
     * @param view 视图
     */
    public void jumpFragmentPagerAction(View view) {
        Intent jumpIntent = new Intent(this, FragmentActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转house countdown activity
     *
     * @param view 视图
     */
    public void jumpHouseCountAction(View view) {
        Intent jumpIntent = new Intent(this, HouseCountDownActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转service activity
     *
     * @param view 视图
     */
    public void jumpServicePageAction(View view) {
        Intent jumpIntent = new Intent(this, ServiceActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转broadcast activity
     *
     * @param view 视图
     */
    public void jumpBroadcastPageAction(View view) {
        Intent jumpIntent = new Intent(this, ReceiverActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转broadcast activity
     *
     * @param view 视图
     */
    public void jumpGlideImagePageAction(View view) {
        Intent jumpIntent = new Intent(this, ImageLoadActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转request activity
     *
     * @param view 视图
     */
    public void jumpRequestPageAction(View view) {
        Intent jumpIntent = new Intent(this, RequestActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转request activity
     *
     * @param view 视图
     */
    public void jumpRetrofitPageAction(View view) {
        Intent jumpIntent = new Intent(this, RetrofitActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转rxJava activity
     *
     * @param view 视图
     */
    public void jumpRXPageAction(View view) {
        Intent jumpIntent = new Intent(this, RXActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转SP activity
     *
     * @param view 视图
     */
    public void jumpSharedPreferencePageAction(View view) {
        Intent jumpIntent = new Intent(this, SPActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转webview activity
     * 传参：1.intent对象 2.bundle对象 3.对象实现serializable接口
     * 4.对象实现parcelable接口（优先）
     * @param view 视图
     */
    public void jumpWebViewPageAction(View view) {
        EditText urlPathText = findViewById(R.id.input_url_path);
        String urlPath = urlPathText.getText().toString();
        if (urlPath.length() == 0) {
            urlPath = "https://www.baidu.com/";
        }
        Intent jumpIntent = new Intent(this, WebViewActivity.class);
        jumpIntent.putExtra("urlPath", urlPath);
        startActivity(jumpIntent);
    }

    /**
     * 跳转sp demo activity
     * @param view 视图
     */
    public void jumpSpDemoPageAction(View view) {
        Intent jumpIntent = new Intent(this, SPDemoActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转sp demo activity
     * @param view 视图
     */
    public void jumpSQLDemoPageAction(View view) {
        Intent jumpIntent = new Intent(this, SqliteDemoActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转media record demo activity
     * @param view 视图
     */
    public void jumpMediaRecordDemoPageAction(View view) {
        Intent jumpIntent = new Intent(this, MediaRecordActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转media player demo activity
     * @param view 视图
     */
    public void jumpMediaPlayDemoPageAction(View view) {
        Intent jumpIntent = new Intent(this, MediaPlayerActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转video view activity
     * @param view 视图
     */
    public void jumpVideoViewDemoPageAction(View view) {
        Intent jumpIntent = new Intent(this, VideoViewActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转sound play activity
     * @param view 视图
     */
    public void jumpSoundPlayDemoPageAction(View view) {
        Intent jumpIntent = new Intent(this, SoundPoolActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转contacts activity
     * @param view 视图
     */
    public void jumpContactsDemoPageAction(View view) {
        Intent jumpIntent = new Intent(this, ContactsDemoActivity.class);
        startActivity(jumpIntent);
    }


    public void jumpPopupPage(View view) {
        Intent jumpIntent = new Intent(this, PopupActivity.class);
        startActivity(jumpIntent);
    }

    public void jumpAmapPage(View view) {
        Intent jumpIntent = new Intent(this, AmapDemoActivity.class);
        startActivity(jumpIntent);
    }

    public void jumpRoundPage(View view) {
        Intent jumpIntent = new Intent(this, RoundActivity.class);
        startActivity(jumpIntent);
    }
    
    public void jumpNewTarget(View view) {
        NewTargetActivity.start(this);
    }

    public void jumpHomePage(View view) {
        HomeActivity.start(this);
    }

    public void jumpTodaySteps(View view) {
        TodayStepsActivity.start(this);
    }
}