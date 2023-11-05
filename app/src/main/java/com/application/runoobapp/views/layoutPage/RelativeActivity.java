package com.application.runoobapp.views.layoutPage;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.application.runoobapp.R;

public class RelativeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);
    }
}
