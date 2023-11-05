package com.application.runoobapp.views.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.application.runoobapp.R;

/**
 * 补间动画
 */
public class ViewAnimationActivity extends Activity {

    private Integer clickTimes = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewanimation);

        ImageView view = findViewById(R.id.iv);
        view.setOnClickListener(v -> {
            clickTimes++;
            if (clickTimes == 1) {
                Animation animation = AnimationUtils.loadAnimation(ViewAnimationActivity.this, R.anim.alpha);
                view.startAnimation(animation);
            } else if (clickTimes == 2) {
                Animation animation = AnimationUtils.loadAnimation(ViewAnimationActivity.this, R.anim.rotate);
                view.startAnimation(animation);
            } else if (clickTimes == 3) {
                Animation animation = AnimationUtils.loadAnimation(ViewAnimationActivity.this, R.anim.sclale);
                view.startAnimation(animation);
            } else if (clickTimes == 4) {
                Animation animation = AnimationUtils.loadAnimation(ViewAnimationActivity.this, R.anim.translate);
                view.startAnimation(animation);
            } else {
                clickTimes = 0;
            }
        });
    }
}
