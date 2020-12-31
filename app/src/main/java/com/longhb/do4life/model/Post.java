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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                ", v=" + v +
                '}';
    }
}
