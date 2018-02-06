package com.example.michel.mynews.API.TopStoriesMostPopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michel on 28/01/2018.
 */

class Multimedium {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("format")
    @Expose
    public String format;

    public Multimedium(String url, String format) {
        this.url = url;
        this.format = format;
    }

    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }
}

