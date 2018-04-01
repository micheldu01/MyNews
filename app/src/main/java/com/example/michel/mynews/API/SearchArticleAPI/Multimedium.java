package com.example.michel.mynews.API.SearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michel on 25/02/2018.
 */

public class Multimedium {

    // DECLARE VALUES
    @SerializedName("rank")
    @Expose
    public Integer rank;
    @SerializedName("subtype")
    @Expose
    public String subtype;
    @SerializedName("caption")
    @Expose
    public Object caption;
    @SerializedName("credit")
    @Expose
    public Object credit;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("height")
    @Expose
    public Integer height = 75;
    @SerializedName("width")
    @Expose
    public Integer width = 75;
    @SerializedName("legacy")
    @Expose
    public Legacy legacy;
    @SerializedName("subType")
    @Expose
    public String subType;
    @SerializedName("crop_name")
    @Expose
    public String cropName;

    // CREATE CONSTRUCTOR
    public Multimedium(Integer rank, String subtype, Object caption, Object credit, String type, String url, Integer height, Integer width, Legacy legacy, String subType, String cropName) {
        this.rank = rank;
        this.subtype = subtype;
        this.caption = caption;
        this.credit = credit;
        this.type = type;
        this.url = url;
        this.height = height;
        this.width = width;
        this.legacy = legacy;
        this.subType = subType;
        this.cropName = cropName;
    }

    // CREATE GETTERS
    public Integer getRank() {
        return rank;
    }

    public String getSubtype() {
        return subtype;
    }

    public Object getCaption() {
        return caption;
    }

    public Object getCredit() {
        return credit;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public Legacy getLegacy() {
        return legacy;
    }

    public String getSubType() {
        return subType;
    }

    public String getCropName() {
        return cropName;
    }
}
