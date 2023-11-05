package com.application.runoobapp.views.homePage.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.application.runoobapp.R;
import com.application.runoobapp.base.baseActivity.AppActivity;
import com.application.runoobapp.base.fragment.AppFragment;
import com.application.runoobapp.views.homePage.other.helper.DoubleClickHelper;
import com.application.runoobapp.views.homePage.other.manager.ActivityManager;
import com.application.runoobapp.views.homePage.ui.adapter.FragmentPagerAdapter;
import com.application.runoobapp.views.homePage.ui.adapter.NavigationAdapter;
import com.application.runoobapp.views.homePage.ui.fragment.FindFragment;
import com.application.runoobapp.views.homePage.ui.fragment.HomeFragment;
import com.application.runoobapp.views.homePage.ui.fragment.MessageFragment;
import com.application.runoobapp.views.homePage.ui.fragment.MineFragment;
import com.gyf.immersionbar.ImmersionBar;

public final class HomeActivity extends AppActivity
        implements NavigationAdapter.OnNavigationListener {

    //用于判断当前fragment
    private static final String INTENT_KEY_IN_FRAGMENT_INDEX = "fragmentIndex";

    private static final String INTENT_KEY_IN_FRAGMENT_CLASS = "fragmentClass";

    //首页切换容器
    private ViewPager mViewPager;

    //导航栏容器
    private RecyclerView mNavigationView;

    //导航适配器
    private NavigationAdapter mNavigationAdapter;

    //首页容器适配器
    private FragmentPagerAdapter<AppFragment> mPagerAdapter;

    public static void start(Context context) {
        start(context, HomeFragment.class);
    }

    public static void start(Context context, Class<? extends AppFragment<?>> fragmentClass) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(INTENT_KEY_IN_FRAGMENT_CLASS, fragmentClass);
        //TODO: 跟activity启动模式有关
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        mViewPager = findViewById(R.id.vp_home_pager);
        mNavigationView = findViewById(R.id.rv_home_navigation);

        //TODO: 这个recyclerView的orientation是vertical 但是展示是水平的，为啥？
        mNavigationAdapter = new NavigationAdapter(this);
        mNavigationAdapter.addItem(new NavigationAdapter.MenuItem(getString(R.string.home_nav_index),
                ContextCompat.getDrawable(this, R.drawable.home_home_selector)));
        mNavigationAdapter.addItem(new NavigationAdapter.MenuItem(getString(R.string.home_nav_found),
                ContextCompat.getDrawable(this, R.drawable.home_found_selector)));
        mNavigationAdapter.addItem(new NavigationAdapter.MenuItem(getString(R.string.home_nav_message),
                ContextCompat.getDrawable(this, R.drawable.home_message_selector)));
        mNavigationAdapter.addItem(new NavigationAdapter.MenuItem(getString(R.string.home_nav_me),
                ContextCompat.getDrawable(this, R.drawable.home_me_selector)));
        //设置导航监听事件
        mNavigationAdapter.setOnNavigationListener(this);
        mNavigationView.setAdapter(mNavigationAdapter);

    }

    @Override
    protected void initData() {
        mPagerAdapter = new FragmentPagerAdapter<AppFragment>(this);
        mPagerAdapter.addFragment(HomeFragment.newInstance());
        mPagerAdapter.addFragment(FindFragment.newInstance());
        mPagerAdapter.addFragment(MessageFragment.newInstance());
        mPagerAdapter.addFragment(MineFragment.newInstance());

        mViewPager.setAdapter(mPagerAdapter);

        onNewIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        switchFragment(mPagerAdapter.getFragmentIndex(getSerializable(INTENT_KEY_IN_FRAGMENT_CLASS)));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // 保存当前 Fragment 索引位置
        outState.putInt(INTENT_KEY_IN_FRAGMENT_INDEX, mViewPager.getCurrentItem());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // 恢复当前 Fragment 索引位置
        switchFragment(savedInstanceState.getInt(INTENT_KEY_IN_FRAGMENT_INDEX));
    }

    private void switchFragment(int fragmentIndex){
        if (fragmentIndex == -1) {
            return;
        }

        switch (fragmentIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
                mViewPager.setCurrentItem(fragmentIndex);
                mNavigationAdapter.setSelectedPosition(fragmentIndex);
                break;
            default:
                break;
        }
    }



    @Override
    public boolean onNavigationItemSelected(int position) {
        switch (position) {
            case 0:
            case 1:
            case 2:
            case 3:
                mViewPager.setCurrentItem(position);
                return true;
            default:
                return false;
        }

    }

    @NonNull
    @Override
    protected ImmersionBar createStatusBarConfig() {
        return super.createStatusBarConfig()
                // 指定导航栏背景颜色
                .navigationBarColor(R.color.white);
    }

    @Override
    public void onBackPressed() {
        if (!DoubleClickHelper.isOnDoubleClick()) {
            toast(R.string.home_exit_hint);
            return;
        }

        // 移动到上一个任务栈，避免侧滑引起的不良反应
        moveTaskToBack(false);
        postDelayed(() -> {
            // 进行内存优化，销毁掉所有的界面
            ActivityManager.getInstance().finishAllActivities();
            // 销毁进程（注意：调用此 API 可能导致当前 Activity onDestroy 方法无法正常回调）
            // System.exit(0);
        }, 300);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.setAdapter(null);
        mNavigationView.setAdapter(null);
        mNavigationAdapter.setOnNavigationListener(null);
    }

}
