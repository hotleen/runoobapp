package com.application.runoobapp.views.media;

import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.application.runoobapp.R;

public class SoundPoolActivity extends AppCompatActivity {

    private SoundPool pool;
    private int soundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_pool);

        pool = new SoundPool.Builder().setMaxStreams(3).build();
        soundId = pool.load(this, R.raw.goodbye, 1);


    }

    /**
     * TODO: 只能播放几秒就停下来了，待解决
     * @param view 视图
     */
    public void playMusic(View view) {
        this.pool.play(soundId, 0.5f, 0.5f, 1, 1, 1.0f);
    }

    /**
     * 释放资源
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.pool.unload(this.soundId);
        this.pool.release();
    }
}