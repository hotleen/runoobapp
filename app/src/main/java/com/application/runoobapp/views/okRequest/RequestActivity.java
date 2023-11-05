package com.application.runoobapp.views.okRequest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class RequestActivity extends AppCompatActivity {

    private static final String TAG = RequestActivity.class.getSimpleName();
    private OkHttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okrequest);
        httpClient = new OkHttpClient();
    }

    public void sendGetRequestSync(View view) {
        //安卓中发送同步网络请求必须新起一个线程
        new Thread(() -> {
            Request request = new Request.Builder().url("https://www.httpbin.org/get?a=1&b=2")
                    .build();
            Call call = httpClient.newCall(request);
            try {
                Response response = call.execute();
                Log.i(TAG, "sendGetRequestSync: " + response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    //异步请求不需要新起线程
    public void sendGetRequestAsync(View view) {
        Request request = new Request.Builder().url("https://www.httpbin.org/get?a=1&b=2")
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + response.body().string());
                }
            }
        });
    }

    public void sendPostRequestSync(View view) {

        new Thread(() -> {
            FormBody body = new FormBody.Builder()
                    .add("a", "1").add("b", "2").build();
            Request request = new Request.Builder().url("https://www.httpbin.org/post")
                    .post(body).build();
            Call call = httpClient.newCall(request);
            try {
                Response response = call.execute();
                Log.i(TAG, "sendPostRequestSync: " + response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void sendPostRequestAsync(View view) {
        FormBody body = new FormBody.Builder()
                .add("a", "1").add("b", "2").build();
        Request request = new Request.Builder().url("https://www.httpbin.org/post")
                .post(body).build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onPostResponse: " + response.body().string());
                }
            }
        });
    }

}
