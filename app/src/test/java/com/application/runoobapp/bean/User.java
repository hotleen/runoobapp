package com.application.runoobapp.bean;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;

public class User {

    // @Expose注解 用于声明当前的 参数 需要暴露给 JSON 进行序列化或反序列化
    @Expose
    private final String userName;

    @Expose
    private final String password;

    @Expose
    private final int age;

    @Expose
    private final boolean isStudent;

    @Expose
    private Job job;

    public User(String userName, String password, int age, boolean isStudent) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.isStudent = isStudent;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", isStudent=" + isStudent +
                ", job=" + job +
                '}';
    }
}
