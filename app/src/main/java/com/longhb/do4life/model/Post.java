package com.longhb.do4life.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("subTitle")
    @Expose
    public String subTitle;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("__v")
    @Expose
    public Integer v;

    public Post() {
    }

    public Post(String id, String title, String subTitle, String image, String url, Integer v) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.image = image;
        this.url = url;
        this.v = v;
    }
}
