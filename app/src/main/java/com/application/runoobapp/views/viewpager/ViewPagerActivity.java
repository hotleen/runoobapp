package com.application.runoobapp.views.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.application.runoobapp.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_pager);

        //动态加载界面
        LayoutInflater lf = getLayoutInflater().from(this);
        View v1 = lf.inflate(R.layout.view_page1,null);
        View v2 = lf.inflate(R.layout.view_page2,null);
        View v3 = lf.inflate(R.layout.view_page3,null);

        List<View> viewList = new ArrayList<>();
        viewList.add(v1);
        viewList.add(v2);
        viewList.add(v3);

        ViewPager viewPager = findViewById(R.id.vp);
        ViewAdapter adapter = new ViewAdapter(viewList);
        viewPager.setAdapter(adapter);
    }

}