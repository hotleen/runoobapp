package com.application.runoobapp.views.homePage.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.application.runoobapp.R;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;


/**
 *    desc   : 图片预览适配器
 */
public final class ImagePreviewAdapter extends AppAdapter<String> {

    public ImagePreviewAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    private final class ViewHolder extends AppAdapter<?>.ViewHolder {

        private final PhotoView mPhotoView;

        private ViewHolder() {
            super(R.layout.image_preview_item);
            mPhotoView = (PhotoView) getItemView();
        }

        @Override
        public void onBindView(int position) {
            //TODO: GlideApp
            Glide.with(getContext())
                    .load(getItem(position))
                    .into(mPhotoView);
        }
    }
}