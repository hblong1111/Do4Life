package com.longhb.do4life.model.retrofit.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonShift {

    @SerializedName("department")
    @Expose
    public String department;

    /**
     * No args constructor for use in serialization
     *
     */
    public JsonShift() {
    }

    /**
     *
     * @param department
     */
    public JsonShift(String department) {
        super();
        this.department = department;
    }

}