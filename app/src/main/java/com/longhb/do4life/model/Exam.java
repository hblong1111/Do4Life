package com.longhb.do4life.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exam {
    @SerializedName("stt")
    @Expose
    public String STT;
    @SerializedName("name")
    @Expose
    public String Name;
    @SerializedName("day")
    @Expose
    public String day;

    public Exam(String STT, String name, String day) {
        this.STT = STT;
        Name = name;
        this.day = day;
    }

    public Exam() {
    }
}
