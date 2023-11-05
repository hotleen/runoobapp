package com.application.runoobapp.views.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;

public class SPActivity extends AppCompatActivity {
//    SP:程序运行首选项里面的数据会全部加载如内存，SP首选项不能存储太多内容信息
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

    }

    public void saveDateToSP(View view) {
        SharedPreferences runoobSP = getSharedPreferences("runoobSP", Context.MODE_PRIVATE);
        runoobSP.edit().putString("runoobKey","success!").apply();
    }

    public void getDataFromSP(View view) {
        SharedPreferences runoobSP = getSharedPreferences("runoobSP", Context.MODE_PRIVATE);
        String value = runoobSP.getString("runoobKey", "defaultValue");
        Toast.makeText(this,"value is"+value,Toast.LENGTH_SHORT).show();

    }
}
