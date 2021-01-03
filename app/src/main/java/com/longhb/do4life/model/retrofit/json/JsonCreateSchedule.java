package com.longhb.do4life.model.retrofit.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonCreateSchedule {

    @SerializedName("shift")
    @Expose
    public String shift;
    @SerializedName("profile")
    @Expose
    public String profile;

    /**
     * No args constructor for use in serialization
     */
    public JsonCreateSchedule() {
    }

    /**
     * @param shift
     * @param profile
     */
    public JsonCreateSchedule(String shift, String profile) {
        super();
        this.shift = shift;
        this.profile = profile;
    }

}
