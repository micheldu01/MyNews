package com.example.michel.mynews.API.MostPopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michel on 25/02/2018.
 */

public class MediaMetadata {

    // DECLARE VALUE
    @SerializedName("url")
    @Expose
    public String url;

    // CREATE CONSTRUCTOR
    public MediaMetadata(String url) {
        this.url = url;
    }

    // CREATE GETTER
    public String getUrl() {
        return url;
    }
}
