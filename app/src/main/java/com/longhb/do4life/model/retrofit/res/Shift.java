package com.longhb.do4life.model.retrofit.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Shift {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("department")
    @Expose
    public String department;
    @SerializedName("doctor")
    @Expose
    public String doctor;
    @SerializedName("time")
    @Expose
    public long time;
    @SerializedName("status")
    @Expose
    public boolean status;
    @SerializedName("__v")
    @Expose
    public Integer v;

    @Override
    public String toString() {
        return "Shift{" +
                "id='" + id + '\'' +
                ", department='" + department + '\'' +
                ", doctor='" + doctor + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                ", v=" + v +
                '}';
    }

    public String getTime(SimpleDateFormat format){
        return format.format(new Date(time));
    }
}
