package com.application.runoobapp.base.http.api;

import com.hjq.http.config.IRequestApi;

public final class UserInfoApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/info";
    }

    public final class Bean {

    }
}