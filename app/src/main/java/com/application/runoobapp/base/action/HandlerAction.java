package com.application.runoobapp.base.action;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/**
 * handler意图处理
 * TODO: 关于handler的机制 有必要继续深入了解
 */
public interface HandlerAction {

    Handler HANDLER = new Handler(Looper.getMainLooper());

    /**
     * 获取handler
     */
    default Handler getHandler(){
        return HANDLER;
    }

    //延迟执行
    default boolean post(Runnable runnable) {
        return postDelayed(runnable, 0);
    }


    /**
     * 延迟一段时间执行
     */
    default boolean postDelayed(Runnable runnable, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return postAtTime(runnable, SystemClock.uptimeMillis() + delayMillis);
    }

    /**
     * 在指定的时间执行
     */
    default boolean postAtTime(Runnable runnable, long uptimeMillis) {
        // 发送和当前对象相关的消息回调
        return HANDLER.postAtTime(runnable, this, uptimeMillis);
    }

    /**
     * 移除单个消息回调
     */
    default void removeCallbacks(Runnable runnable) {
        HANDLER.removeCallbacks(runnable);
    }

    /**
     * 移除全部消息回调
     */
    default void removeCallbacks() {
        // 移除和当前对象相关的消息回调
        HANDLER.removeCallbacksAndMessages(this);
    }

}
