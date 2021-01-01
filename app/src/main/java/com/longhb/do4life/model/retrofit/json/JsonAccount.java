package com.longhb.do4life.model.retrofit.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonAccount {


    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("password")
    @Expose
    public String password;

    @SerializedName("CMND")
    @Expose
    public String cMND;

    public JsonAccount(String username, String password, String cMND) {
        this.username = username;
        this.password = password;
        this.cMND = cMND;
    }

    public JsonAccount(String id) {
        this.id = id;
    }

    public JsonAccount(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
}
