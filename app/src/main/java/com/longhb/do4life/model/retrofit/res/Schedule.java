package com.longhb.do4life.model.retrofit.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule {
    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("doctor")
    @Expose
    public String doctor;
    @SerializedName("department")
    @Expose
    public String department;
    @SerializedName("room")
    @Expose
    public String room;
    @SerializedName("time")
    @Expose
    public String time;
    @SerializedName("profileName")
    @Expose
    public String profileName;
    @SerializedName("profileAge")
    @Expose
    public Integer profileAge;
    @SerializedName("phoneNumber")
    @Expose
    public String phoneNumber;
}
