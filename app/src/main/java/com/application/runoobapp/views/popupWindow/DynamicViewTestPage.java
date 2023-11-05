package com.application.runoobapp.views.popupWindow;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;

public class DynamicViewTestPage extends AppCompatActivity {

    private final static String TAG = DynamicViewTestPage.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dynamic_view);
        initLayout();
    }

    private void initLayout() {
        LinearLayout dynamicBizLayout = findViewById(R.id.dynamic_biz_layout);

        LinearLayout firstLayout = new LinearLayout(this);
        ViewGroup.LayoutParams firstLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , 0, 1.0f);
        firstLayout.setLayoutParams(firstLayoutParams);
        firstLayout.setBackgroundColor(Color.RED);

        LinearLayout secondLayout = new LinearLayout(this);
        ViewGroup.LayoutParams secondLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , 0, 3.0f);
        secondLayout.setLayoutParams(secondLayoutParams);
        secondLayout.setBackgroundColor(Color.WHITE);

        Button resizeBtn = new Button(this);
        ViewGroup.LayoutParams resizeBtnParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        resizeBtn.setLayoutParams(resizeBtnParams);
        resizeBtn.setText("resize layout");
        resizeBtn.setBackgroundColor(Color.GREEN);
        resizeBtn.setOnClickListener(v -> {
                    Log.i(TAG, "onClick: resize layout!");
                    dynamicBizLayout.removeView(firstLayout);
                }
        );

        secondLayout.addView(resizeBtn);

        dynamicBizLayout.addView(firstLayout);
        dynamicBizLayout.addView(secondLayout);

    }
}
