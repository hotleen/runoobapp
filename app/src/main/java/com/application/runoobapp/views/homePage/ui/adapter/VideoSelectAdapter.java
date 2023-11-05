package com.application.runoobapp.views.homePage.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.runoobapp.R;
import com.application.runoobapp.base.view.PlayerView;
import com.application.runoobapp.views.homePage.other.manager.CacheDataManager;
import com.application.runoobapp.views.homePage.ui.activity.VideoSelectActivity;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2020/03/01
 *    desc   : 视频选择适配器
 */
public final class VideoSelectAdapter extends AppAdapter<VideoSelectActivity.VideoBean> {

    private final List<VideoSelectActivity.VideoBean> mSelectVideo;

    public VideoSelectAdapter(Context context, List<VideoSelectActivity.VideoBean> images) {
        super(context);
        mSelectVideo = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    private final class ViewHolder extends AppAdapter<?>.ViewHolder {

        private final ImageView mImageView;
        private final CheckBox mCheckBox;
        private final TextView mDurationView;
        private final TextView mSizeView;

        private ViewHolder() {
            super(R.layout.video_select_item);
            mImageView = findViewById(R.id.iv_video_select_image);
            mCheckBox = findViewById(R.id.iv_video_select_check);
            mDurationView = findViewById(R.id.tv_video_select_duration);
            mSizeView = findViewById(R.id.tv_video_select_size);
        }

        @Override
        public void onBindView(int position) {
            VideoSelectActivity.VideoBean bean = getItem(position);
            //TODO: GlideApp
            Glide.with(getContext())
                    .load(bean.getVideoPath())
                    .into(mImageView);

            mCheckBox.setChecked(mSelectVideo.contains(getItem(position)));

            // 获取视频的总时长
            mDurationView.setText(PlayerView.conversionTime((int) bean.getVideoDuration()));

            // 获取视频文件大小
            mSizeView.setText(CacheDataManager.getFormatSize(bean.getVideoSize()));
        }
    }

    @Override
    protected RecyclerView.LayoutManager generateDefaultLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }
}