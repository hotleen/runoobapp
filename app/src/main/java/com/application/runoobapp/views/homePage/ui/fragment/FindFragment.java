package com.application.runoobapp.views.homePage.ui.fragment;

import android.view.View;
import android.widget.ImageView;

import com.application.runoobapp.R;
import com.application.runoobapp.base.aop.SingleClick;
import com.application.runoobapp.base.fragment.TitleBarFragment;
import com.application.runoobapp.base.view.CountdownView;
import com.application.runoobapp.base.view.SwitchButton;
import com.application.runoobapp.views.homePage.ui.activity.HomeActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

public final class FindFragment extends TitleBarFragment<HomeActivity>
        implements SwitchButton.OnCheckedChangeListener {

    private ImageView mCircleView;
    private ImageView mCornerView;
    private SwitchButton mSwitchButton;
    private CountdownView mCountdownView;

    public static FindFragment newInstance() {
        return new FindFragment();
    }

    protected int getLayoutId() {
        return R.layout.find_fragment;
    }

    @Override
    protected void initView() {
        mCircleView = findViewById(R.id.iv_find_circle); //圆角
        mCornerView = findViewById(R.id.iv_find_corner); //方角
        mSwitchButton = findViewById(R.id.sb_find_switch); //切换switch按钮
        mCountdownView = findViewById(R.id.cv_find_countdown); //验证码

        mSwitchButton.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {
        // 显示圆形的 ImageView
        Glide.with(this)
                .load(R.drawable.update_app_top_bg)
                .transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()))
                .into(mCircleView);

        // 显示圆角的 ImageView
        Glide.with(this)
                .load(R.drawable.update_app_top_bg)
                .transform(new MultiTransformation<>(new CenterCrop(), new RoundedCorners((int) getResources().getDimension(R.dimen.dp_10))))
                .into(mCornerView);

    }

    @SingleClick
    @Override
    public void onClick(View view) {
        if (view == mCountdownView) {
            toast(R.string.common_code_send_hint);
            mCountdownView.start();
        }
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }


    @Override
    public void onCheckedChanged(SwitchButton button, boolean checked) {
        toast(checked);
    }
}
