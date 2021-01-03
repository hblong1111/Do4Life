package com.longhb.do4life.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notification {
    @SerializedName("title")
    @Expose
    public String Title;
    @SerializedName("content")
    @Expose
    public String Content;
    @SerializedName("time")
    @Expose
    public String Time;

    public Notification(String title, String content, String time) {
        Title = title;
        Content = content;
        Time = time;
    }

    public Notification() {
    }
}
