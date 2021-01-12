package com.longhb.do4life.model.retrofit.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JsonProfile implements Serializable {

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

    public JsonProfile(String idAccount, String fullname, Integer age, String phoneNumber) {
        this.account = idAccount;
        this.fullname = fullname;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }


    public JsonProfile(String idProfile,Integer age, String fullname,  String phoneNumber) {
        this.id = idProfile;
        this.fullname = fullname;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }





    public JsonProfile(String account) {
        this.account = account;
    }
    public JsonProfile(String idProfile,String spam) {
        this.id = idProfile;
    }
}