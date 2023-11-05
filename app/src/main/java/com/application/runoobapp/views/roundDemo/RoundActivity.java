package com.application.runoobapp.views.roundDemo;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;
import com.application.runoobapp.util.DisplayUtils;

public class RoundActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        initWidgets();
        initAnotherWidget();
    }


    private void initWidgets() {
        LinearLayout mLly1 = findViewById(R.id.lly1);
        GradientDrawable gradientDrawable1 = new GradientDrawable();
        gradientDrawable1.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable1.setCornerRadius(DisplayUtils.dp2px(10F));
        gradientDrawable1.setColor(Color.RED);
        mLly1.setBackground(gradientDrawable1);

        LinearLayout mLly2 = findViewById(R.id.lly2);
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable2.setColor(Color.BLUE);
        float[] radii = new float[]{
                DisplayUtils.dp2px(10F), DisplayUtils.dp2px(10F),
                0F, 0F,
                0F, 0F,
                DisplayUtils.dp2px(10F), DisplayUtils.dp2px(10F)
        };
        gradientDrawable2.setCornerRadii(radii);
        mLly2.setBackground(gradientDrawable2);

        LinearLayout mLly3 = findViewById(R.id.lly3);
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable3.setColor(Color.CYAN);
        float[] radii1 = new float[]{
                DisplayUtils.dp2px(10F), DisplayUtils.dp2px(10F),
                0F, 0F,
                0F, 0F,
                0F, 0F
        };
        gradientDrawable3.setCornerRadii(radii1);
        mLly3.setBackground(gradientDrawable3);
    }

    private void initAnotherWidget(){
        LinearLayout mLly4 = findViewById(R.id.lly4);
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable3.setColor(Color.MAGENTA);
        float[] radii2 = new float[]{
                DisplayUtils.dp2px(10F), DisplayUtils.dp2px(10F),
                DisplayUtils.dp2px(10F), DisplayUtils.dp2px(10F),
                0F, 0F,
                0F, 0F
        };
        gradientDrawable3.setCornerRadii(radii2);
        mLly4.setBackground(gradientDrawable3);

    }
}
