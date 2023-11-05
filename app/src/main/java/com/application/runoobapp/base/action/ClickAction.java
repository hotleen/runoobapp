package com.application.runoobapp.base.action;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

public interface ClickAction extends View.OnClickListener {

    <V extends View> V findViewById(@IdRes int id);

    /**
     * int... 可变参数类型 接收任意数量 把ids可以当作数组
     * default 在接口中提供方法的默认实现 接口实现类是否实现该方法是可选的，使得在现有接口添加新方法是，可以保持向后兼容性
     * @param ids
     */
    default void setOnClickListener(@IdRes int... ids) {
        setOnClickListener(this, ids);
    }

    default void setOnClickListener(@Nullable View.OnClickListener listener, int... ids) {
        for (int id : ids) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    default void setOnClickListener(View... views) {
        setOnClickListener(this, views);
    }

    default void setOnClickListener(@Nullable View.OnClickListener listener, View... views) {
        for (View view : views) {
            view.setOnClickListener(listener);
        }
    }

    @Override
    default void onClick(View view) {
        // 默认不实现，让子类实现
    }
}
