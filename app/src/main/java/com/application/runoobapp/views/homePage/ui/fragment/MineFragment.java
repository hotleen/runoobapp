package com.application.runoobapp.views.homePage.ui.fragment;

import com.application.runoobapp.R;
import com.application.runoobapp.base.fragment.TitleBarFragment;
import com.application.runoobapp.views.homePage.ui.activity.HomeActivity;

public final class MineFragment extends TitleBarFragment<HomeActivity> {

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.btn_mine_dialog, R.id.btn_mine_hint, R.id.btn_mine_login, R.id.btn_mine_register, R.id.btn_mine_forget,
                R.id.btn_mine_reset, R.id.btn_mine_change, R.id.btn_mine_personal, R.id.btn_mine_setting, R.id.btn_mine_about,
                R.id.btn_mine_guide, R.id.btn_mine_browser, R.id.btn_mine_image_select, R.id.btn_mine_image_preview,
                R.id.btn_mine_video_select, R.id.btn_mine_video_play, R.id.btn_mine_crash, R.id.btn_mine_pay);

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

}
