package com.longhb.do4life.model.retrofit.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProfileRetrofit implements Serializable {

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

    public ProfileRetrofit(String account, String fullname, Integer age, String phoneNumber) {
        this.account = account;
        this.fullname = fullname;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ProfileRetrofit{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", fullname='" + fullname + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", v=" + v +
                '}';
    }
}
