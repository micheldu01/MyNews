package com.example.michel.mynews.API.SearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michel on 25/02/2018.
 */

public class Legacy {

    // DECLARE VALUES
    @SerializedName("xlargewidth")
    @Expose
    public Integer xlargewidth;
    @SerializedName("xlarge")
    @Expose
    public String xlarge;
    @SerializedName("xlargeheight")
    @Expose
    public Integer xlargeheight;
    @SerializedName("wide")
    @Expose
    public String wide;
    @SerializedName("widewidth")
    @Expose
    public Integer widewidth;
    @SerializedName("wideheight")
    @Expose
    public Integer wideheight;
    @SerializedName("thumbnailheight")
    @Expose
    public Integer thumbnailheight;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;
    @SerializedName("thumbnailwidth")
    @Expose
    public Integer thumbnailwidth;

    // CREATE CONSTRUCTOR
    public Legacy(Integer xlargewidth, String xlarge, Integer xlargeheight, String wide, Integer widewidth, Integer wideheight, Integer thumbnailheight, String thumbnail, Integer thumbnailwidth) {
        this.xlargewidth = xlargewidth;
        this.xlarge = xlarge;
        this.xlargeheight = xlargeheight;
        this.wide = wide;
        this.widewidth = widewidth;
        this.wideheight = wideheight;
        this.thumbnailheight = thumbnailheight;
        this.thumbnail = thumbnail;
        this.thumbnailwidth = thumbnailwidth;
    }

    // CREATE GETTERS
    public Integer getXlargewidth() {
        return xlargewidth;
    }

    public String getXlarge() {
        return xlarge;
    }

    public Integer getXlargeheight() {
        return xlargeheight;
    }

    public String getWide() {
        return wide;
    }

    public Integer getWidewidth() {
        return widewidth;
    }

    public Integer getWideheight() {
        return wideheight;
    }

    public Integer getThumbnailheight() {
        return thumbnailheight;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Integer getThumbnailwidth() {
        return thumbnailwidth;
    }
}
