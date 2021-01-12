package com.longhb.do4life.model.retrofit.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonScheduleHistory {

    @SerializedName("profile")
    @Expose
    public String profile;

    /**
     * No args constructor for use in serialization
     */
    public JsonScheduleHistory() {
    }

    /**
     * @param profile
     */
    public JsonScheduleHistory(String profile) {
        this.profile = profile;
    }

}
