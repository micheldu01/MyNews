package com.example.michel.mynews.API.MostPopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michel on 24/02/2018.
 */

public class Result2 {

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("media")
    @Expose
    public List<Media> media;

    @SerializedName("publishedDate")
    @Expose
    public String publishedDate;

    @SerializedName("section")
    @Expose
    public String section;

    @SerializedName("url")
    @Expose
    public String url;

    public Result2(String title, List<Media> media, String publishedDate, String section) {
        this.title = title;
        this.media = media;
        this.publishedDate = publishedDate;
        this.section = section;
    }

    public Result2(String title, List<Media> media, String publishedDate, String section, String url) {
        this.title = title;
        this.media = media;
        this.publishedDate = publishedDate;
        this.section = section;
        this.url = url;
    }

    public Result2(List<Media> media) {
        this.media = media;
    }

    public List<Media> getMedia() {
        return media;
    }

    public Result2(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getSection() {
        return section;
    }

    public String getUrl() {
        return url;
    }
}
