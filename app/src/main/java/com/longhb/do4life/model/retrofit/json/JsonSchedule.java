package com.longhb.do4life.model.retrofit.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonSchedule {

    @SerializedName("profile")
    @Expose
    public String profile;

    /**
     * No args constructor for use in serialization
     *
     */
    public JsonSchedule() {
    }

    /**
     *
     * @param profile
     */
    public JsonSchedule(String profile) {
        super();
        this.profile = profile;
    }
}
