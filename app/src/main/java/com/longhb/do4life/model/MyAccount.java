package com.longhb.do4life.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyAccount {

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

    public MyAccount(String id, String username, String password, Boolean checked, Boolean status, Integer v) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.checked = checked;
        this.status = status;
        this.v = v;
    }

    public MyAccount() {
    }

    public MyAccount(String s) {
        id=s;
    }

    @Override
    public String toString() {
        return "MyAccount{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", checked=" + checked +
                ", status=" + status +
                ", v=" + v +
                '}';
    }
}
