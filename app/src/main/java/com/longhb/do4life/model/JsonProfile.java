package com.longhb.do4life.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonProfile {


    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("fontCMND")
    @Expose
    public String fontCMND;
    @SerializedName("backCMND")
    @Expose
    public String backCMND;

    public JsonProfile(String id, String fontCMND, String backCMND) {
        this.id = id;
        this.fontCMND = fontCMND;
        this.backCMND = backCMND;
    }

    public JsonProfile(String id) {
        this.id = id;
    }
}
