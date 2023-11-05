package com.application.runoobapp.views.notification;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.application.runoobapp.R;

public class NotificationActivity extends Activity {

    private static final String TAG = "NOTIFY_TAG";
    private Toolbar toolBar;
    private AlertDialog.Builder builder;
    private View popupView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        toolBar = findViewById(R.id.toolBar);
        toolBar.setNavigationOnClickListener(v -> Log.e(TAG, "onClick: tool bar left icon was clicked!" ));



    }

    public void showDialog(View view) {
        builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher)//图标
                .setTitle("我是对话框")
                .setMessage("how is weather today?")
                .setView(R.layout.view_dialog)
                .setPositiveButton("确定", (dialog, which) -> Log.i(TAG, "onClick: confirm button has been clicked!"))
                .setNegativeButton("取消", (dialog, which) -> Log.i(TAG, "onClick: cancel button has been clicked!"))
                .setNeutralButton("中间", (dialog, which) -> Log.i(TAG, "onClick: middle button has been clicked!"))
                .create()
                .show();

    }

    public void showPopup(View view) {
        popupView = getLayoutInflater().inflate(R.layout.view_popup, null);
        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);
        Button popupButton = popupView.findViewById(R.id.pop_btn);
        popupButton.setOnClickListener(v -> {
            popupWindow.dismiss();//关闭弹窗
        });

        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.river));
        popupWindow.showAsDropDown(view);
    }
}
