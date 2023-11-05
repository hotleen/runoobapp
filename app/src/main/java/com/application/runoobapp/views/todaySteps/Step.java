package com.application.runoobapp.views.todaySteps;

public class Step {

    private int id;
    private long mBeginTime;
    private long mEndTime;
    private int mMode;
    private int mSteps;

    public Step(int id, long mBeginTime, long mEndTime, int mMode, int mSteps) {
        this.id = id;
        this.mBeginTime = mBeginTime;
        this.mEndTime = mEndTime;
        this.mMode = mMode;
        this.mSteps = mSteps;
    }
}
