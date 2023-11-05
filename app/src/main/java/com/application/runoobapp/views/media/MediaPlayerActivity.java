package com.application.runoobapp.views.media;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;

import java.io.File;
import java.io.IOException;

public class MediaPlayerActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private static final String TAG = MediaPlayerActivity.class.getSimpleName();
    private TextureView playView;

    private Button playButton;

    private MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palyer);
        this.playView = findViewById(R.id.texture_player);
        this.playButton = findViewById(R.id.btn_play_media);
        this.playButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        CharSequence buttonText = this.playButton.getText();
        if (TextUtils.equals(buttonText, "start")) {
            this.playButton.setText("end");
            this.player = new MediaPlayer();
            this.player.setOnPreparedListener(this);
            this.player.setOnCompletionListener(this);
            File mediaFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                    "song.mp4");
            Log.i(TAG, "media file path: "+mediaFile.getAbsolutePath());
            Log.i(TAG, "media file exists: "+mediaFile.exists());
            try {
                player.setDataSource(mediaFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            //关联画布
            this.player.setSurface(new Surface(this.playView.getSurfaceTexture()));
            player.prepareAsync();
        } else {
            this.playButton.setText("start");
            this.player.stop();
            this.player.release();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.player.start();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.playButton.setText("start");
        this.player.release();
    }
}
