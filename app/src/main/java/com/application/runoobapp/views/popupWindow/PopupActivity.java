package com.application.runoobapp.views.popupWindow;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;

import com.application.runoobapp.R;

public class PopupActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
    }



    public void showPopupWidow(View view) {
        View popView = getLayoutInflater().inflate(R.layout.layout_popup, null);
        PopupWindow popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAsDropDown(view);

    }

    public void jumpBizPage(View view) {
        Intent jumpIntent = new Intent(this, BizTransparentActivity.class);
        startActivity(jumpIntent);
    }

    public void jumpDynamicPage(View view) {
        Intent intent = new Intent(this,DynamicViewActivity.class);
        startActivity(intent);
    }
    public void jumpDynamicTestPage(View view) {
        Intent intent = new Intent(this,DynamicViewTestPage.class);
        startActivity(intent);
    }

    public void addImage(View view) {
        ImageView dynamicImage = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(800,600);
        dynamicImage.setLayoutParams(params);
        dynamicImage.setBackgroundColor(Color.parseColor("#2087CEFA"));

        LinearLayout layout = findViewById(R.id.popup_layout);
        layout.addView(dynamicImage);
    }
}
