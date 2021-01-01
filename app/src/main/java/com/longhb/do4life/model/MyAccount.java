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
    @SerializedName("CMND")
    @Expose
    public String cMND;
    @SerializedName("checked")
    @Expose
    public Boolean checked;
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("__v")
    @Expose
    public Integer v;
    @SerializedName("backCMND")
    @Expose
    public String backCMND;
    @SerializedName("fontCMND")
    @Expose
    public String fontCMND;

    @Override
    public String toString() {
        return "MyAccount{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cMND='" + cMND + '\'' +
                ", checked=" + checked +
                ", status=" + status +
                ", v=" + v +
                ", backCMND='" + backCMND + '\'' +
                ", fontCMND='" + fontCMND + '\'' +
                '}';
    }
}
