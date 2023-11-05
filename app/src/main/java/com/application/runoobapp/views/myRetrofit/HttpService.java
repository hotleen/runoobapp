package com.application.runoobapp.views.myRetrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface  HttpService {

    //https://wwww.httpbin.org/post?userName=userNameValue
    // FormUrlEncoded 表单方式提交
    @POST("post")
    @FormUrlEncoded
    Call<ResponseBody> post(@Field("userName") String userName, @Field("password") String pwd);


    //https://wwww.httpbin.org/get?userName=userNameValue
    @GET("get")
    Call<ResponseBody> get(@Query("username") String userName, @Query("password") String pwd);

    @POST("user/login")
    @FormUrlEncoded
    Call<ResponseBody> login(@Field("username") String username,@Field("password") String pwd);

    @POST("user/login")
    @FormUrlEncoded
    Call<BaseResponse> autoLogin(@Field("username") String username,@Field("password") String pwd);
}
