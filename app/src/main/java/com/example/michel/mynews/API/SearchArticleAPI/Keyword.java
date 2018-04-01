package com.example.michel.mynews.API.SearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michel on 25/02/2018.
 */

public class Keyword {

    // CREATE VALUES
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("value")
    @Expose
    public String value;
    @SerializedName("rank")
    @Expose
    public Integer rank;
    @SerializedName("major")
    @Expose
    public String major;

    // CREATE CONSTRUCTOR
    public Keyword(String name, String value, Integer rank, String major) {
        this.name = name;
        this.value = value;
        this.rank = rank;
        this.major = major;
    }

    // CREATE GETTER
    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Integer getRank() {
        return rank;
    }

    public String getMajor() {
        return major;
    }
}
