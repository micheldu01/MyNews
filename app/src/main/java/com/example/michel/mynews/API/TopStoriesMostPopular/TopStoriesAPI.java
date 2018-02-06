package com.example.michel.mynews.API.TopStoriesMostPopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michel on 28/01/2018.
 */

public class TopStoriesAPI {


    @SerializedName("results")
    @Expose
    public List<Result> results = null;

    public TopStoriesAPI() {}

    public TopStoriesAPI(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }
}
