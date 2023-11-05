package com.application.runoobapp.views.homePage.ui.fragment;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.application.runoobapp.R;
import com.application.runoobapp.base.fragment.AppFragment;
import com.application.runoobapp.base.fragment.TitleBarFragment;
import com.application.runoobapp.views.homePage.ui.activity.HomeActivity;
import com.application.runoobapp.views.homePage.ui.adapter.FragmentPagerAdapter;
import com.application.runoobapp.views.homePage.ui.adapter.TabAdapter;
import com.application.runoobapp.views.homePage.ui.widget.XCollapsingToolbarLayout;
import com.gyf.immersionbar.ImmersionBar;

public final class HomeFragment extends TitleBarFragment<HomeActivity>
    implements TabAdapter.OnTabListener, ViewPager.OnPageChangeListener,
        XCollapsingToolbarLayout.OnScrimsListener {

    private XCollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;

    private TextView mAddressView;
    private TextView mHintView;
    private AppCompatImageView mSearchView;

    private RecyclerView mTabView;
    private ViewPager mViewPager;

    private TabAdapter mTabAdapter;

    private FragmentPagerAdapter<AppFragment<?>> mPagerAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView() {
        mCollapsingToolbarLayout = findViewById(R.id.ctl_home_bar); //协调布局
        mToolbar = findViewById(R.id.tb_home_title); //工具栏

        mAddressView = findViewById(R.id.tv_home_address); //定位
        mHintView = findViewById(R.id.tv_home_hint); //搜索提示输入兰
        mSearchView = findViewById(R.id.iv_home_search); //搜索栏

        mTabView = findViewById(R.id.rv_home_tab); //tab列表切换页
        mViewPager = findViewById(R.id.vp_home_pager); //切换页

        mPagerAdapter = new FragmentPagerAdapter<>(this); //适配器
        mPagerAdapter.addFragment(StatusFragment.newInstance(), "列表演示");
        mPagerAdapter.addFragment(BrowserFragment.newInstance("https://github.com/getActivity"), "网页演示");
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(this);

        mTabAdapter = new TabAdapter(getAttachActivity());
        mTabView.setAdapter(mTabAdapter);

        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
        ImmersionBar.setTitleBar(getAttachActivity(), mToolbar); //沉浸式状态栏

        //设置渐变监听
        mCollapsingToolbarLayout.setOnScrimsListener(this);
    }

    @Override
    protected void initData() {
        mTabAdapter.addItem("列表演示");
        mTabAdapter.addItem("网页演示");
        mTabAdapter.setOnTabListener(this);
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @Override
    public boolean isStatusBarDarkFont() {
        return mCollapsingToolbarLayout.isScrimsShown();
    }

    /**
     * {@link TabAdapter.OnTabListener}
     */

    @Override
    public boolean onTabSelected(RecyclerView recyclerView, int position) {
        mViewPager.setCurrentItem(position);
        return true;
    }

    /**
     * {@link ViewPager.OnPageChangeListener}
     */

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        if (mTabAdapter == null) {
            return;
        }
        mTabAdapter.setSelectedPosition(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    /**
     * CollapsingToolbarLayout 渐变回调
     *
     * {@link XCollapsingToolbarLayout.OnScrimsListener}
     */
    @SuppressLint("RestrictedApi")
    @Override
    public void onScrimsStateChange(XCollapsingToolbarLayout layout, boolean shown) {
        getStatusBarConfig().statusBarDarkFont(shown).init();
        mAddressView.setTextColor(ContextCompat.getColor(getAttachActivity(), shown ? R.color.black : R.color.white));
        mHintView.setBackgroundResource(shown ? R.drawable.home_search_bar_gray_bg : R.drawable.home_search_bar_transparent_bg);
        mHintView.setTextColor(ContextCompat.getColor(getAttachActivity(), shown ? R.color.black60 : R.color.white60));
        mSearchView.setSupportImageTintList(ColorStateList.valueOf(getColor(shown ? R.color.common_icon_color : R.color.white)));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewPager.setAdapter(null);
        mViewPager.removeOnPageChangeListener(this);
        mTabAdapter.setOnTabListener(null);
    }


}
