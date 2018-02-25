package com.example.michel.mynews.API.MostPopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michel on 25/02/2018.
 */

public class MediaMetadata {

    @SerializedName("url")
    @Expose
    public String url;

    public MediaMetadata(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
