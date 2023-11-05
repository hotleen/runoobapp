package com.application.runoobapp.views.myRetrofit;

import androidx.annotation.NonNull;

public class BaseResponse {
    private Data data;
    private int errorCode;
    private String errorMsg;

    public Data getData() {
        return data;
    }

    @NonNull
    @Override
    public String toString() {
        return "BaseResponse{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
