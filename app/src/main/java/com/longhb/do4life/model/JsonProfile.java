package com.longhb.do4life.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonProfile {


    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("CMND")
    @Expose
    public String cMND;
    @SerializedName("fontCMND")
    @Expose
    public String fontCMND;
    @SerializedName("backCMND")
    @Expose
    public String backCMND;
    @SerializedName("checked")
    @Expose
    public Boolean checked;
    @SerializedName("status")
    @Expose
    public Boolean status;

    /**
     * No args constructor for use in serialization
     *
     */
    public JsonProfile() {
    }

    /**
     *
     * @param backCMND
     * @param checked
     * @param id
     * @param fontCMND
     * @param cMND
     * @param status
     */
    public JsonProfile(String id, String cMND, String fontCMND, String backCMND, Boolean checked, Boolean status) {
        super();
        this.id = id;
        this.cMND = cMND;
        this.fontCMND = fontCMND;
        this.backCMND = backCMND;
        this.checked = checked;
        this.status = status;
    }

}
