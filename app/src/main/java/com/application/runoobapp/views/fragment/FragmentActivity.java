package com.application.runoobapp.views.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.application.runoobapp.R;

public class FragmentActivity extends AppCompatActivity implements MessageTransfer{

    private TextView textView;

    /**
     * 1、activity 使用fragment
     * 2、动态切换fragment，加入回退栈
     * 3、activity相互fragment传递信息
     * @param savedInstanceState bundle对象
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        textView = findViewById(R.id.tv_title);

        BlankFragment bf = BlankFragment.newInstance("来自于activity的参数字符串！");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fg_container,bf,"blank").commitAllowingStateLoss();
    }

    @Override
    public void onClick(String message) {
        textView.setText(message);
    }
}
