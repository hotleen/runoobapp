package com.application.runoobapp.views.todaySteps;

public class StepInfo {

    private int stepCount;

    private String dataInfo;

    public StepInfo(int stepCount, String dataInfo) {
        this.stepCount = stepCount;
        this.dataInfo = dataInfo;
    }

    public int getStepCount() {
        return stepCount;
    }

    public String getDataInfo() {
        return dataInfo;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public void setDataInfo(String dataInfo) {
        this.dataInfo = dataInfo;
    }
}
