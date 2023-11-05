package com.application.runoobapp.views.viewpager;


import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ViewAdapter extends PagerAdapter {

    private final List<View> viewList;

    //将给定位置的view添加到viewGroup 容器中，创建并显示出来
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = viewList.get(position);
        container.addView(v, 0);
        return v;
    }

    public ViewAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    //viewpager 中view 个数
    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //删除view
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }
}
