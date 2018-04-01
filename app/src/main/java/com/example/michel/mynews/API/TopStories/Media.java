package com.example.michel.mynews.API.TopStories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michel on 22/02/2018.
 */

public class Media {

    // DECLARE VALUE
    @SerializedName("url")
    @Expose
    public String url;

    // CREATE CONSTRUCTOR
    public Media(String url) {
        this.url = url;
    }

    // CREATE GETTER
    public String getUrl() {
        return url;
    }
}
