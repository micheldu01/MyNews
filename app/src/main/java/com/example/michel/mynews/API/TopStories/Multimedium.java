package com.example.michel.mynews.API.TopStories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michel on 19/02/2018.
 */

public class Multimedium {


    // DECLARE VALUES
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("format")
    @Expose
    public String format;

    // CREATE CONSTRUCTOR WITHOUT VALUE
    public Multimedium() {
    }

    // CREATE CONSTRUCTOR
    public Multimedium(String url, String format) {
        this.url = url;
        this.format = format;
    }

    // CREATE GETTERS
    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }
}
