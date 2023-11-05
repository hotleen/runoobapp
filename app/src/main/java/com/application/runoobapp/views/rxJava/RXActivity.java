package com.application.runoobapp.views.rxJava;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RXActivity extends AppCompatActivity {

    private final static String BING_IMAGE_PATH = "https://th.bing.com/th/id/R.bdb2ecf91f2f3a7402a6d01f223dd7a5?rik=9u045%2fPpr9CmWw&riu=http%3a%2f%2fpic.zsucai.com%2ffiles%2f2013%2f0717%2fbingo9.jpg&ehk=sHQD5PWoRdNaAGKqmA%2bbJPUhmrqQccQi68eTOlnF%2f6c%3d&risl=&pid=ImgRaw&r=0";

    private final static String TAG = RXActivity.class.getSimpleName();

    private ProgressDialog progressDialog;

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        this.imageView = findViewById(R.id.rx_imageView);
    }

    public void showImageAction(View view) {
        // rx起点
        // TODO: 第二步
        Observable.just(BING_IMAGE_PATH)

                //下载显示图片
                // TODO: 第三步
                .map(path -> {
                    try {
                        URL url = new URL(path);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setConnectTimeout(5000);
                        int responseCode = connection.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            InputStream inputStream = connection.getInputStream();
                            return BitmapFactory.decodeStream(inputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                })

                //加水印
                .map(bitmap -> {
                    Paint paint = new Paint();
                    paint.setColor(Color.RED);
                    paint.setTextSize(88);
                    return drawTextToBitmap(bitmap,"bing image",paint,60,600);
                })

                //加日志
                .map(bitmap -> {
                    Log.e(TAG,"loading image info!");
                    return bitmap;
                })

                .subscribeOn(Schedulers.io())

                //切换为安卓主线程，不可在异步线程更新ui
                .observeOn(AndroidSchedulers.mainThread())

                // 关联observer 订阅
                .subscribe(
                        // 终点
                        new Observer<Bitmap>() {

                            //订阅成功
                            //TODO: rx执行第一步
                            @Override
                            public void onSubscribe(Disposable d) {
                                //加载框
                                progressDialog = new ProgressDialog(RXActivity.this);
                                progressDialog.setTitle("RXJava running in process!");
                                progressDialog.show();
                            }

                            //上一层的响应
                            //TODO: 第四步 显示图片
                            @Override
                            public void onNext(Bitmap bitmap) {
                                imageView.setImageBitmap(bitmap);
                            }


                            //链条发生了错误
                            @Override
                            public void onError(Throwable e) {

                            }

                            //TODO: 第五步 rx调用完成
                            @Override
                            public void onComplete() {
                                if (progressDialog != null) {
                                    progressDialog.hide();
                                }
                            }
                        });

    }

    private Bitmap drawTextToBitmap(Bitmap bitmap, String text, Paint paint, int paddingLeft, int paddingTop){
        Bitmap.Config config = bitmap.getConfig();

        paint.setDither(true); //获取清晰图片采样
        paint.setFilterBitmap(true);//过滤
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(config,true);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawText(text,paddingLeft,paddingTop,paint);
        return bitmap;

    }

}
