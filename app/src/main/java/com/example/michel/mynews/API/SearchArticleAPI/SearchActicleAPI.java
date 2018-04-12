package com.example.michel.mynews.API.SearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michel on 06/02/2018.
 */

public class SearchActicleAPI {


    // DECLARE VALUES
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("copyright")
    @Expose
    public String copyright;
    @SerializedName("response")
    @Expose
    public Response response;

    // CREATE CONSTRUCTOR EMPTY


    public SearchActicleAPI() {
    }

    // CREATE CONSTRUCTOR
    public SearchActicleAPI(String status, String copyright, Response response) {
        this.status = status;
        this.copyright = copyright;
        this.response = response;
    }

    // CREATE GETTERS
    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public Response getResponse() {
        return response;
    }
}
