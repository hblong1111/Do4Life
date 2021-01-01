package com.longhb.do4life.model.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonCheckLogin {


    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("password")
    @Expose
    public String password;

    /**
     * No args constructor for use in serialization
     */
    public JsonCheckLogin() {
    }

    /**
     * @param password
     * @param username
     */
    public JsonCheckLogin(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
}
