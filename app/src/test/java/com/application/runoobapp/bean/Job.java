package com.application.runoobapp.bean;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;

public class Job {

    @Expose
    private final String jobKind;

    @Expose
    private final String salary;

    public Job(String jobKind, String salary) {
        this.jobKind = jobKind;
        this.salary = salary;
    }

    @NonNull
    @Override
    public String toString() {
        return "Job{" +
                "jobKind='" + jobKind + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
