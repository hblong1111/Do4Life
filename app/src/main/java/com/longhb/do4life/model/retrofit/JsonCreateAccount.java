package com.longhb.do4life.model.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonCreateAccount {


    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("CMND")
    @Expose
    public String cMND;

    /**
     * No args constructor for use in serialization
     *
     */
    public JsonCreateAccount() {
    }

    /**
     *
     * @param password
     * @param cMND
     * @param username
     */
    public JsonCreateAccount(String username, String password, String cMND) {
        super();
        this.username = username;
        this.password = password;
        this.cMND = cMND;
    }
}
