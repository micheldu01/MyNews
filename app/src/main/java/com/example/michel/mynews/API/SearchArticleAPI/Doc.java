package com.example.michel.mynews.API.SearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michel on 25/02/2018.
 */

public class Doc {

    // DECLARE VALUES
    @SerializedName("web_url")
    @Expose
    public String webUrl;
    @SerializedName("snippet")
    @Expose
    public String snippet;
    @SerializedName("blog")
    @Expose
    public Blog blog;
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("multimedia")
    @Expose
    public List<Multimedium> multimedia = null;
    @SerializedName("headline")
    @Expose
    public Headline headline;
    @SerializedName("keywords")
    @Expose
    public List<Keyword> keywords = null;
    @SerializedName("pub_date")
    @Expose
    public String pubDate;
    @SerializedName("document_type")
    @Expose
    public String documentType;
    @SerializedName("new_desk")
    @Expose
    public String newDesk;
    @SerializedName("section_name")
    @Expose
    public String sectionName;
    @SerializedName("byline")
    @Expose
    public Byline byline;
    @SerializedName("type_of_material")
    @Expose
    public String typeOfMaterial;
    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("word_count")
    @Expose
    public Integer wordCount;
    @SerializedName("score")
    @Expose
    public Float score;
    @SerializedName("uri")
    @Expose
    public String uri;

    // CREATE CONSTRUCTOR
    public Doc(String webUrl, String snippet, Blog blog, String source, List<Multimedium> multimedia, Headline headline, List<Keyword> keywords, String pubDate, String documentType, String newDesk, String sectionName, Byline byline, String typeOfMaterial, String id, Integer wordCount, Float score, String uri) {
        this.webUrl = webUrl;
        this.snippet = snippet;
        this.blog = blog;
        this.source = source;
        this.multimedia = multimedia;
        this.headline = headline;
        this.keywords = keywords;
        this.pubDate = pubDate;
        this.documentType = documentType;
        this.newDesk = newDesk;
        this.sectionName = sectionName;
        this.byline = byline;
        this.typeOfMaterial = typeOfMaterial;
        this.id = id;
        this.wordCount = wordCount;
        this.score = score;
        this.uri = uri;
    }

    // CREATE GETTER
    public String getWebUrl() {
        return webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public Blog getBlog() {
        return blog;
    }

    public String getSource() {
        return source;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    public Headline getHeadline() {
        return headline;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getNewDesk() {
        return newDesk;
    }

    public String getSectionName() {
        return sectionName;
    }

    public Byline getByline() {
        return byline;
    }

    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public String getId() {
        return id;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public Float getScore() {
        return score;
    }

    public String getUri() {
        return uri;
    }
}
