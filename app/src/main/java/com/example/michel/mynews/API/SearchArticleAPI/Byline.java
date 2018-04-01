package com.example.michel.mynews.API.SearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michel on 25/02/2018.
 */

public class Byline {

    // DECLARE VALUES
    @SerializedName("original")
    @Expose
    public String original;
    @SerializedName("person")
    @Expose
    public List<Person> person = null;
    @SerializedName("organization")
    @Expose
    public String organization;

    // CREATE CONSTRUCTOR
    public Byline(String original, List<Person> person, String organization) {
        this.original = original;
        this.person = person;
        this.organization = organization;
    }

    // CREATE GETTERS
    public String getOriginal() {
        return original;
    }

    public List<Person> getPerson() {
        return person;
    }

    public String getOrganization() {
        return organization;
    }
}
