package com.longhb.do4life.model.retrofit.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleHistory {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("fullname")
    @Expose
    public String fullname;
    @SerializedName("age")
    @Expose
    public Integer age;
    @SerializedName("phoneNumber")
    @Expose
    public String phoneNumber;
    @SerializedName("department")
    @Expose
    public String department;
    @SerializedName("doctor")
    @Expose
    public String doctor;
    @SerializedName("room")
    @Expose
    public String room;
    @SerializedName("time")
    @Expose
    public Integer time;
    @SerializedName("result")
    @Expose
    public String result;
}
