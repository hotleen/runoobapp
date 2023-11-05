package com.application.runoobapp.views.animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.application.runoobapp.R;

public class AnimationActivity extends Activity {

    private boolean animaFlag = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        RelativeLayout relativeLayout =  findViewById(R.id.animation_relative);
        AnimationDrawable drawable = (AnimationDrawable) relativeLayout.getBackground();
        relativeLayout.setOnClickListener(v -> {
            if (animaFlag){
                drawable.start();
                animaFlag = false;
            }else {
                drawable.stop();
                animaFlag = true;
            }
        });

    }
}
