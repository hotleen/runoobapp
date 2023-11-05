package com.application.runoobapp.views.recycler;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.runoobapp.R;
import com.application.runoobapp.views.countdown.ListBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RecycleActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerTag";
    private final List<ListBean> beanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        IntStream.range(0, 101).forEach(i -> {
            ListBean bean = new ListBean();
            bean.setName("learning android development for " + i + " days");
            beanList.add(bean);
        });

        RecyclerView recyclerView = findViewById(R.id.rvPage);

        //指定RecyclerView布局模式，同样支持网格、
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecycleAdapter adapter = new RecycleAdapter(beanList, this);
        recyclerView.setAdapter(adapter);
        adapter.setRecyclerItemClickListener(position -> Log.i(TAG, "onRecyclerItemClick: " + position));
    }
}