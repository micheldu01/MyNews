package com.example.michel.mynews.API.MostPopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michel on 24/02/2018.
 */

public class MostPopular {

    @SerializedName("results")
    @Expose
    public List<Result2> results = null;

    public MostPopular(List<Result2> results) {
        this.results = results;
    }

    public List<Result2> getResults() {
        return results;
    }
}
