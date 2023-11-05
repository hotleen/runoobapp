package com.application.runoobapp.views.webview;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;

public class WebViewActivity extends AppCompatActivity {
    private static final String TAG = WebViewActivity.class.getSimpleName();

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wv);
        WebView webView = findViewById(R.id.web_view_id);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(WebViewActivity.this,"nativeInstance");
        WebView.setWebContentsDebuggingEnabled(true);//允许调试

        Intent intent = getIntent();
        String urlPath = intent.getStringExtra("urlPath");

        webView.loadUrl(urlPath);
    }

    //js调用安卓，必须加@JavascriptInterface注释的方法才可以被js调用
    @JavascriptInterface
    public String androidMethod() {
        Log.i(TAG, "js调用了安卓的方法");
        return "我是js调用安卓获取的数据";
    }

}
