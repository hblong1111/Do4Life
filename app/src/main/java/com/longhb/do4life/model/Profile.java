package com.longhb.do4life.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("name")
    @Expose
    public String nameUser;
    @SerializedName("ImageUser")
    @Expose
    public int imageUser;
    @SerializedName("birth")
    @Expose
    public String birth;

    public Profile(String nameUser, int imageUser, String birth) {
        this.nameUser = nameUser;
        this.imageUser = imageUser;
        this.birth = birth;
    }

    public Profile() {
    }
}
