package com.application.runoobapp.views.countdown;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import com.application.runoobapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CountDownActivity extends Activity {

    private static final String TAG = "CountApp";

    private final List<ListBean> beanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        IntStream.range(0,100).forEach(i->{
            ListBean bean = new ListBean();
            bean.setName("learning android development for"+i+"days");
            beanList.add(bean);
        });
        ListView listView = findViewById(R.id.lv);
        listView.setAdapter(new AppAdapter(beanList,this));

        listView.setOnItemClickListener((parent, view, position, id) -> Log.i(TAG, "onItem click: "+position));

    }
}
