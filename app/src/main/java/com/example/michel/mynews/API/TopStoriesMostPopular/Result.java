package com.example.michel.mynews.API.TopStoriesMostPopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michel on 28/01/2018.
 */

public class Result {

    @SerializedName("section")
    @Expose
    public String section;
    @SerializedName("subsection")
    @Expose
    public String subsection;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("abstract")
    @Expose
    public String _abstract;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("published_date")
    @Expose
    public String publishedDate;
    @SerializedName("multimedia")
    @Expose
    public List<Multimedium> multimedia = null;
    @SerializedName("related_urls")
    @Expose
    public List<RelatedUrl> relatedUrls = null;



    public Result(String section, String subsection, String title, String _abstract, String url, String publishedDate, List<Multimedium> multimedia, List<RelatedUrl> relatedUrls) {
        this.section = section;
        this.subsection = subsection;
        this.title = title;
        this._abstract = _abstract;
        this.url = url;
        this.publishedDate = publishedDate;
        this.multimedia = multimedia;
        this.relatedUrls = relatedUrls;
    }


    public String getSection() {
        return section;
    }

    public String getSubsection() {
        return subsection;
    }

    public String getTitle() {
        return title;
    }

    public String get_abstract() {
        return _abstract;
    }

    public String getUrl() {
        return url;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    public List<RelatedUrl> getRelatedUrls() {
        return relatedUrls;
    }

}

