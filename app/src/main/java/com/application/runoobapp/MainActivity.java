package com.application.runoobapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.views.amap.AmapDemoActivity;
import com.application.runoobapp.views.animation.AnimationActivity;
import com.application.runoobapp.views.animation.ValueAnimatorActivity;
import com.application.runoobapp.views.animation.ViewAnimationActivity;
import com.application.runoobapp.views.contentProvider.ContactsDemoActivity;
import com.application.runoobapp.views.countdown.CountDownActivity;
import com.application.runoobapp.views.fragment.FragmentActivity;
import com.application.runoobapp.views.glideImage.ImageLoadActivity;
import com.application.runoobapp.views.homePage.ui.activity.SplashActivity;
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
import com.application.runoobapp.views.okRequest.RequestActivity;
import com.application.runoobapp.views.popupWindow.PopupActivity;
import com.application.runoobapp.views.practice.UIPracticePage;
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpPracticePage(View view) {
        UIPracticePage.start(this);
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
     * 跳转约束布局
     *
     * @param view 视图
     */
    public void jumpConstraintPageAction(View view) {
        Intent jumpIntent = new Intent(this, ConstraintActivity.class);
        startActivity(jumpIntent);
    }

    /**
     * 跳转RecyclerView布局
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
     * 跳转Glide  activity
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
     * 跳转retrofit request activity
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
     * 跳转sql activity
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
        SplashActivity.start(this);
    }

    public void jumpTodaySteps(View view) {
        TodayStepsActivity.start(this);
    }
}