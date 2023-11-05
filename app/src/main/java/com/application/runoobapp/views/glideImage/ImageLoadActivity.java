package com.application.runoobapp.views.glideImage;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imageload);

        ImageView iv = findViewById(R.id.myImage);

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.river)//图片加载中占位符
                .error(R.drawable.river)//图片请求失败
                .fallback(R.drawable.river);//url为null
        //.override(100,100);     指定imageView宽高

        Glide.with(this)
                .load("https://s.cn.bing.net/th?id=OHR.BoobyDarwinDay_ZH-CN9917306809_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&qlt=50")
                .apply(options)
                .transform(new CircleCrop()) //圆形
                .into(iv);
    }
}
