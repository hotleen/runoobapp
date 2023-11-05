package com.application.runoobapp.views.recycler;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.application.runoobapp.R;
import com.application.runoobapp.views.countdown.ListBean;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder> {

    private final List<ListBean> beanList;
    private final Context context;

    public RecycleAdapter(List<ListBean> beanList, Context context) {
        this.beanList = beanList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.list_item, null);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        holder.textView.setText(beanList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    //AppAdapter 优化逻辑类似
    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
            itemView.setOnClickListener(v -> {
                if (myListener != null) {
                    myListener.onRecyclerItemClick(getAdapterPosition());
                }
            });
        }
    }

    private OnRecyclerItemClickListener myListener;

    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
        myListener = listener;
    }

    // 自行实现点击回调
    public interface OnRecyclerItemClickListener {
        void onRecyclerItemClick(int position);
    }
}
