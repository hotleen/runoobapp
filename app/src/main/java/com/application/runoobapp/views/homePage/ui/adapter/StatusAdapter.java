package com.application.runoobapp.views.homePage.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.application.runoobapp.R;

public final class StatusAdapter extends AppAdapter<String> {

    public StatusAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    private final class ViewHolder extends AppAdapter<?>.ViewHolder {

        private final TextView mTextView;

        private ViewHolder() {
            super(R.layout.status_item);
            mTextView = findViewById(R.id.tv_status_text);
        }

        @Override
        public void onBindView(int position) {
            mTextView.setText(getItem(position));
        }
    }
}
