package com.example.michel.mynews.HTTPNYT;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michel on 28/01/2018.
 */

class RelatedUrl {


    @SerializedName("suggested_link_text")
    @Expose
    public String suggestedLinkText;
    @SerializedName("url")
    @Expose
    public String url;

    public RelatedUrl(String suggestedLinkText, String url) {
        this.suggestedLinkText = suggestedLinkText;
        this.url = url;
    }

    public String getSuggestedLinkText() {
        return suggestedLinkText;
    }

    public String getUrl() {
        return url;
    }
}

