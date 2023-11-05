package com.application.runoobapp.views.media;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;

import java.io.File;

public class VideoViewActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = VideoViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        VideoView videoView = findViewById(R.id.video_view_container);
        MediaController controller = new MediaController(this);
        controller.setPrevNextListeners(this,this);
        videoView.setMediaController(controller);
        File mediaFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                "song.mp4");
        videoView.setVideoPath(mediaFile.getAbsolutePath());
        videoView.start();
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "prev or next click!");
    }
}