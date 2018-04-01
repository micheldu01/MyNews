package com.example.michel.mynews.API.TopStories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michel on 28/01/2018.
 */

public class RelatedUrl {


    // DECLARE VALUES
    @SerializedName("suggested_link_text")
    @Expose
    public String suggestedLinkText;
    @SerializedName("url")
    @Expose
    public String url;

    // CREATE CONSTRUCTOR
    public RelatedUrl(String suggestedLinkText, String url) {
        this.suggestedLinkText = suggestedLinkText;
        this.url = url;
    }

    // CREATE GETTERS
    public String getSuggestedLinkText() {
        return suggestedLinkText;
    }

    public String getUrl() {
        return url;
    }
}

