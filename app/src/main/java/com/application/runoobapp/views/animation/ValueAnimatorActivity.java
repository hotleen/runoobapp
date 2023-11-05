package com.application.runoobapp.views.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.application.runoobapp.R;

public class ValueAnimatorActivity extends AppCompatActivity {

    private final String TAG = "ValueAnimatorActivity";

    @SuppressLint("StringFormatMatches")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);

        ValueAnimator animator = ValueAnimator.ofFloat(0f,1f);
        TextView textView = findViewById(R.id.valueText);
        animator.setDuration(5000);
        animator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            Log.i(TAG, "animator value: "+value);
            // string.xml with placeholder
            textView.setText(String.format(getString(R.string.VALUE_ANIMATOR_TEXT),value));
        });
        animator.start();
    }
}