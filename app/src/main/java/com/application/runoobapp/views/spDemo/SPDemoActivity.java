package com.application.runoobapp.views.spDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.application.runoobapp.R;

public class SPDemoActivity extends AppCompatActivity {

    private EditText inputName;
    private EditText inputPwd;
    private CheckBox checkRemember;
    private CheckBox checkAutoLogin;
    private SharedPreferences config;

    private final String TAG = SPDemoActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spdemo);
        config = getSharedPreferences("LOGIN_CONFIG", Context.MODE_PRIVATE);
        initView();

        loginAgain();
    }

    private void loginAgain() {
        boolean rememberLogin = config.getBoolean("rememberPwd", false);
        boolean autoLogin = config.getBoolean("autoLogin", false);

        //设置记住密码
        if (rememberLogin) {
            String userName = config.getString("userName", "");
            String userPwd = config.getString("userPwd", "");
            Log.e(TAG, "login confg: " + userName + " " + userPwd);
            inputName.setText(userName);
            inputPwd.setText(userPwd);
            checkRemember.setChecked(true);
        }

        //设置自动登录
        if (autoLogin){
            checkAutoLogin.setChecked(true);
            Toast.makeText(SPDemoActivity.this,"user auto login!",Toast.LENGTH_LONG).show();
        }
    }

    private void initView() {
        inputName = findViewById(R.id.sp_input_name);
        inputPwd = findViewById(R.id.sp_input_pwd);
        checkRemember = findViewById(R.id.check_remember_pwd);
        checkAutoLogin = findViewById(R.id.check_auto_login);
        Button buttonRegister = findViewById(R.id.btn_register_user);
        Button buttonLogin = findViewById(R.id.btn_login_user);

        buttonRegister.setOnClickListener(view -> Log.e(TAG, "register"));

        buttonLogin.setOnClickListener(view -> {
            Log.e(TAG, "onClick: login!");
            String userName = inputName.getText().toString().trim();
            String userPwd = inputPwd.getText().toString().trim();
            Log.e(TAG, "onClick: loginInfo: " + userName + " " + userPwd);
            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPwd)) {
                Toast.makeText(SPDemoActivity.this, "输入不能为空！", Toast.LENGTH_LONG).show();
            } else {
                //记住密码
                if (checkRemember != null && checkRemember.isChecked()) {
                    SharedPreferences.Editor editor = config.edit();
                    editor.putString("userName", userName);
                    editor.putString("userPwd", userPwd);
                    editor.putBoolean("rememberPwd", true);
                    editor.apply();
                }

                //自动登录
                if (checkAutoLogin != null && checkAutoLogin.isChecked()) {
                    SharedPreferences.Editor editor = config.edit();
                    editor.putBoolean("autoLogin", true);
                    editor.apply();
                }
            }
        });
    }
}