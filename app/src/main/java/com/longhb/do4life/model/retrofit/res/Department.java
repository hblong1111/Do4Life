package com.longhb.do4life.model.retrofit.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Department {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("price")
    @Expose
    public Integer price;
    @SerializedName("__v")
    @Expose
    public Integer v;
}
