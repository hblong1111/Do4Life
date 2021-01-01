package com.longhb.do4life.model.retrofit.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileRetrofit {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("account")
    @Expose
    public String account;
    @SerializedName("fullname")
    @Expose
    public String fullname;
    @SerializedName("age")
    @Expose
    public Integer age;
    @SerializedName("phoneNumber")
    @Expose
    public String phoneNumber;
    @SerializedName("__v")
    @Expose
    public Integer v;

}
