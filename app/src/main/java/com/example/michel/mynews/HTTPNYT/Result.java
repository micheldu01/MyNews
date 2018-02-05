package com.example.michel.mynews.HTTPNYT;

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
    @SerializedName("thumbnail_standard")
    @Expose
    public String thumbnailStandard;
    @SerializedName("short_url")
    @Expose
    public String shortUrl;
    @SerializedName("byline")
    @Expose
    public String byline;
    @SerializedName("item_type")
    @Expose
    public String itemType;
    @SerializedName("updated_date")
    @Expose
    public String updatedDate;
    @SerializedName("created_date")
    @Expose
    public String createdDate;
    @SerializedName("published_date")
    @Expose
    public String publishedDate;
    @SerializedName("material_type_facet")
    @Expose
    public String materialTypeFacet;
    @SerializedName("des_facet")
    @Expose
    public List<String> desFacet = null;
    @SerializedName("org_facet")
    @Expose
    public List<String> orgFacet = null;
    @SerializedName("per_facet")
    @Expose
    public List<String> perFacet = null;
    @SerializedName("geo_facet")
    @Expose
    public List<String> geoFacet = null;
    @SerializedName("multimedia")
    @Expose
    public List<Multimedium> multimedia = null;
    @SerializedName("related_urls")
    @Expose
    public List<RelatedUrl> relatedUrls = null;

    public Result(String section, String subsection, String title, String _abstract, String url, String thumbnailStandard, String shortUrl, String byline, String itemType, String updatedDate, String createdDate, String publishedDate, String materialTypeFacet, List<String> desFacet, List<String> orgFacet, List<String> perFacet, List<String> geoFacet, List<Multimedium> multimedia, List<RelatedUrl> relatedUrls) {
        this.section = section;
        this.subsection = subsection;
        this.title = title;
        this._abstract = _abstract;
        this.url = url;
        this.thumbnailStandard = thumbnailStandard;
        this.shortUrl = shortUrl;
        this.byline = byline;
        this.itemType = itemType;
        this.updatedDate = updatedDate;
        this.createdDate = createdDate;
        this.publishedDate = publishedDate;
        this.materialTypeFacet = materialTypeFacet;
        this.desFacet = desFacet;
        this.orgFacet = orgFacet;
        this.perFacet = perFacet;
        this.geoFacet = geoFacet;
        this.multimedia = multimedia;
        this.relatedUrls = relatedUrls;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
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

    public String getThumbnailStandard() {
        return thumbnailStandard;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getByline() {
        return byline;
    }

    public String getItemType() {
        return itemType;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    public List<String> getDesFacet() {
        return desFacet;
    }

    public List<String> getOrgFacet() {
        return orgFacet;
    }

    public List<String> getPerFacet() {
        return perFacet;
    }

    public List<String> getGeoFacet() {
        return geoFacet;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    public List<RelatedUrl> getRelatedUrls() {
        return relatedUrls;
    }
}

