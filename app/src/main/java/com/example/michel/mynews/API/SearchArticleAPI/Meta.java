package com.example.michel.mynews.API.SearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michel on 25/02/2018.
 */

public class Meta {

    // DECLARE VALUES
    @SerializedName("hits")
    @Expose
    public Integer hits;
    @SerializedName("offset")
    @Expose
    public Integer offset;
    @SerializedName("time")
    @Expose
    public Integer time;

    // CREATE CONSTRUCTOR
    public Meta(Integer hits, Integer offset, Integer time) {
        this.hits = hits;
        this.offset = offset;
        this.time = time;
    }

    // CREATE GETTERS
    public Integer getHits() {
        return hits;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getTime() {
        return time;
    }
}
