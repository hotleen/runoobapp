package com.application.runoobapp.views.myRetrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    private static final String TAG = RetrofitActivity.class.getSimpleName();
    private Retrofit retrofit;
    private HttpService httpService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        retrofit = new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        httpService = retrofit.create(HttpService.class);
    }


    public void postAsyncWithRetrofit(View view) {
        retrofit2.Call<ResponseBody> call = httpService.post("huzy", "123");
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<ResponseBody> call, @NonNull retrofit2.Response<ResponseBody> response) {
                try {
                    if (response.body() != null) {
                        Log.i(TAG, "onResponse by retrofit: " + response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<ResponseBody> call, @NonNull Throwable t) {

            }
        });
    }

    public void getAsyncWithRetrofit(View view) {
        retrofit2.Call<ResponseBody> call = httpService.get("huzy", "123");
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<ResponseBody> call, @NonNull retrofit2.Response<ResponseBody> response) {
                try {
                    assert response.body() != null;
                    Log.i(TAG, "onResponse by retrofit: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<ResponseBody> call, @NonNull Throwable t) {

            }
        });
    }

    /**
     * 将接收到的值转换为bean对象
     *
     * @param view 视图
     */
    public void convertToBean(View view) {
        retrofit = new Retrofit.Builder().baseUrl("https://www.wanandroid.com").build();
        httpService = retrofit.create(HttpService.class);
        retrofit2.Call<ResponseBody> call = httpService.login("lanceedu", "123123");
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<ResponseBody> call, @NonNull retrofit2.Response<ResponseBody> response) {
                try {
                    String result = response.body() != null ? response.body().string() : null;
                    Log.i(TAG, "onResponse: "+result);
                    BaseResponse baseResponse = new Gson().fromJson(result,BaseResponse.class);
                    Log.i(TAG, "onResponse: baseResponse: "+baseResponse.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<ResponseBody> call, @NonNull Throwable t) {
                String result = t.getMessage();
                Log.i(TAG, "onFailure: "+result);
            }
        });

    }

    public void autoConvert(View view) {
        retrofit = new Retrofit.Builder().baseUrl("https://www.wanandroid.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        httpService = retrofit.create(HttpService.class);
        retrofit2.Call<BaseResponse> call = httpService.autoLogin("lanceedu", "123123");
        call.enqueue(new retrofit2.Callback<BaseResponse>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<BaseResponse> call, @NonNull retrofit2.Response<BaseResponse> response) {
                BaseResponse res = response.body();
                Log.i(TAG, "auto login onResponse: "+ (res != null ? res.toString() : null));
            }
            @Override
            public void onFailure(@NonNull retrofit2.Call<BaseResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
