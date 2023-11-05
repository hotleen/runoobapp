package com.application.runoobapp.base.baseActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.application.runoobapp.base.action.ActivityAction;
import com.application.runoobapp.base.action.BundleAction;
import com.application.runoobapp.base.action.ClickAction;
import com.application.runoobapp.base.action.HandlerAction;
import com.application.runoobapp.base.action.KeyboardAction;
import com.application.runoobapp.base.fragment.BaseFragment;

import java.util.List;
import java.util.Random;

/**
 * activity基类
 */
public abstract class BaseActivity extends AppCompatActivity
        implements ActivityAction, ClickAction,
        HandlerAction, BundleAction, KeyboardAction {

    // 错误结果码
    public static final int RESULT_ERROR = -2;

    // 回调集合
    private SparseArray<OnActivityCallback> mActivityCallbacks;

    /**
     * nullable是用来标注方法是否能传入null值，如果可以传入NUll值，则标记为nullable，
     * 如果不可以则标注为Nonnull. 在我们做了一些不安全严谨的编码操作的时候,这些注释会给我们一些警告。
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    /**
     * TODO: 子类和父类的initLayout等重写方法执行顺序是怎样的呢？
     */
    protected void initActivity() {
        initLayout();
        initView();
        initData();
    }

    //获取布局ID
    public abstract int getLayoutId();

    /**
     * 初始化布局内控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    /**
     * 基类统一初始化布局
     */
    protected void initLayout() {
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            initSoftKeyboard();
        }
    }

    /**
     * 初始化软键盘
     */
    protected void initSoftKeyboard() {
        //点击外部隐藏软键盘
        getContentView().setOnClickListener(v -> hideKeyboard(getCurrentFocus()));
    }



    // 在一个activity对象被销毁之前，Android系统会调用该方法，用于释放此activity之前占用的资源，finish方法就会调用onDestroy
    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeCallbacks();
    }

    // 用于结束一个activity生命周期
    @Override
    public void finish() {
        super.finish();
        // 隐藏软键，避免内存泄漏
        hideKeyboard(getCurrentFocus());
    }

    /**
     * 如果当前的 Activity（singleTop 启动模式） 被复用时会回调
     * TODO: 具体什么时候会被调用呢？
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 设置为当前的 Intent，避免 Activity 被杀死后重启 Intent 还是最原先的那个
        setIntent(intent);
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }
    public ViewGroup getContentView() {
        return findViewById(Window.ID_ANDROID_CONTENT);
    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * Fragment 按键事件派发
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            // 这个 Fragment 必须是 BaseFragment 的子类，并且处于可见状态
            if (!(fragment instanceof BaseFragment) ||
                    fragment.getLifecycle().getCurrentState() != Lifecycle.State.RESUMED) {
                continue;
            }
            // 将按键事件派发给 Fragment 进行处理
            if (((BaseFragment<?>) fragment).dispatchKeyEvent(event)) {
                // 如果 Fragment 拦截了这个事件，那么就不交给 Activity 处理
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        // 隐藏软键，避免内存泄漏
        hideKeyboard(getCurrentFocus());
        // 查看源码得知 startActivity 最终也会调用 startActivityForResult
        super.startActivityForResult(intent, requestCode, options);
    }

    /**
     * startActivityForResult 方法优化
     * TODO: startActivityForResult及回调方法 调用，回调 以及该方法过时的替代方法？
     */
    public void startActivityForResult(Class<? extends Activity> clazz, OnActivityCallback callback) {
        startActivityForResult(new Intent(this, clazz), null, callback);
    }

    public void startActivityForResult(Intent intent, OnActivityCallback callback) {
        startActivityForResult(intent, null, callback);
    }

    public void startActivityForResult(Intent intent, @Nullable Bundle options, OnActivityCallback callback) {
        if (mActivityCallbacks == null) {
            mActivityCallbacks = new SparseArray<>(1);
        }
        // 请求码必须在 2 的 16 次方以内
        int requestCode = new Random().nextInt((int) Math.pow(2, 16));
        mActivityCallbacks.put(requestCode, callback);
        startActivityForResult(intent, requestCode, options);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        OnActivityCallback callback;
        if (mActivityCallbacks != null && (callback = mActivityCallbacks.get(requestCode)) != null) {
            callback.onActivityResult(resultCode, data);
            mActivityCallbacks.remove(requestCode);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }





    public interface OnActivityCallback {

        /**
         * 结果回调
         *
         * @param resultCode 结果码
         * @param data       数据
         */
        void onActivityResult(int resultCode, @Nullable Intent data);
    }

}
