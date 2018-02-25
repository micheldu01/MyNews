package com.example.michel.mynews.API.SearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michel on 25/02/2018.
 */

public class Response {


    @SerializedName("docs")
    @Expose
    public List<Doc> docs = null;
    @SerializedName("meta")
    @Expose
    public Meta meta;

    public Response(List<Doc> docs, Meta meta) {
        this.docs = docs;
        this.meta = meta;
    }

    public List<Doc> getDocs() {
        return docs;
    }

    public Meta getMeta() {
        return meta;
    }
}
