package com.example.michel.mynews.API.TopStories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michel on 28/01/2018.
 */

public class TopStoriesAPI {


    // DECLARE VALUE
    @SerializedName("results")
    @Expose
    public List<Result> results = null;

    // CREATE CONSTRUCTOR WITHOUT VALUE
    public TopStoriesAPI() {}

    // CREATE CONSTRUCTOR
    public TopStoriesAPI(List<Result> results) {
        this.results = results;
    }

    // CREATE GETTER
    public List<Result> getResults() {
        return results;
    }
}
