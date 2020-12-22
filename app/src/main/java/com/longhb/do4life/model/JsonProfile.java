package com.longhb.do4life.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonProfile {


    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("checked")
    @Expose
    public Boolean checked;
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("__v")
    @Expose
    public Integer v;

    public JsonProfile() {
    }

    public JsonProfile(String id, String username, String password, Boolean checked, Boolean status, Integer v) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.checked = checked;
        this.status = status;
        this.v = v;
    }

}
