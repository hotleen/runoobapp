package com.application.runoobapp.views.media;

import android.Manifest;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.application.runoobapp.R;

import java.io.File;
import java.io.IOException;

public class MediaRecordActivity extends AppCompatActivity {

    private static final String TAG = MediaRecorder.class.getSimpleName();
    private TextureView textureView;
    private Button operateButton;
    private MediaRecorder mediaRecorder;
    private Camera camera;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_record);
        //申请权限
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO}, 100);
        this.textureView = findViewById(R.id.texture_view);
        this.operateButton = findViewById(R.id.btn_operate_media);
    }

    public void operateMedia(View view) {

        CharSequence operateButtonText = this.operateButton.getText();
        if (TextUtils.equals(operateButtonText, "start")) {
            operateButton.setText("end");

            //设置摄像头预览方向
            camera = Camera.open();
            camera.setDisplayOrientation(90);
            camera.unlock();


            mediaRecorder = new MediaRecorder();
            mediaRecorder.setCamera(camera);
            //音频源：麦克风
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //视频源：摄像头
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            //视频输出格式：mp4
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            //设置音视频编码
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            mediaRecorder.setOrientationHint(90);
            //视频输出目录
            mediaRecorder.setOutputFile(new File(getExternalFilesDir(""),
                    "recorder.mp4").getAbsolutePath());
            //设置视频宽高
            mediaRecorder.setVideoSize(640, 480);
            //设置预览
            mediaRecorder.setPreviewDisplay(new Surface(textureView.getSurfaceTexture()));
            try {
                mediaRecorder.prepare();
            } catch (IOException e) {
                Log.e(TAG, "operateMedia prepare err: " + e.getMessage());
                throw new RuntimeException(e);
            }
            mediaRecorder.start();
        } else {
            operateButton.setText("start");
            //资源释放
            mediaRecorder.stop();
            mediaRecorder.release();
            camera.stopPreview();
            camera.release();
        }
    }

    public void requireMediaPermission(View view) {
        //申请权限
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO}, 100);
    }

}
