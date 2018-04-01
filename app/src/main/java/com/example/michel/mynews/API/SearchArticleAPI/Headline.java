package com.example.michel.mynews.API.SearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michel on 25/02/2018.
 */

public class Headline {

    // CREATE VALUES
    @SerializedName("main")
    @Expose
    public String main;
    @SerializedName("kicker")
    @Expose
    public Object kicker;
    @SerializedName("content_kicker")
    @Expose
    public Object contentKicker;
    @SerializedName("print_headline")
    @Expose
    public String printHeadline;
    @SerializedName("name")
    @Expose
    public Object name;
    @SerializedName("seo")
    @Expose
    public Object seo;
    @SerializedName("sub")
    @Expose
    public Object sub;

    // CREATE CONSTRUCTOR
    public Headline(String main, Object kicker, Object contentKicker, String printHeadline, Object name, Object seo, Object sub) {
        this.main = main;
        this.kicker = kicker;
        this.contentKicker = contentKicker;
        this.printHeadline = printHeadline;
        this.name = name;
        this.seo = seo;
        this.sub = sub;
    }

    // CREATE GETTER
    public String getMain() {
        return main;
    }

    public Object getKicker() {
        return kicker;
    }

    public Object getContentKicker() {
        return contentKicker;
    }

    public String getPrintHeadline() {
        return printHeadline;
    }

    public Object getName() {
        return name;
    }

    public Object getSeo() {
        return seo;
    }

    public Object getSub() {
        return sub;
    }
}
