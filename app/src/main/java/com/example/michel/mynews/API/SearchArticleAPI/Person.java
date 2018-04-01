package com.example.michel.mynews.API.SearchArticleAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michel on 25/02/2018.
 */

public class Person {

    // DECLARE VALUES
    @SerializedName("firstname")
    @Expose
    public String firstname;
    @SerializedName("middlename")
    @Expose
    public Object middlename;
    @SerializedName("lastname")
    @Expose
    public String lastname;
    @SerializedName("qualifier")
    @Expose
    public Object qualifier;
    @SerializedName("title")
    @Expose
    public Object title;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("organization")
    @Expose
    public String organization;
    @SerializedName("rank")
    @Expose
    public Integer rank;

    // CREATE CONSTRUCTOR
    public Person(String firstname, Object middlename, String lastname, Object qualifier, Object title, String role, String organization, Integer rank) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.qualifier = qualifier;
        this.title = title;
        this.role = role;
        this.organization = organization;
        this.rank = rank;
    }

    // CREATE GETTERS
    public String getFirstname() {
        return firstname;
    }

    public Object getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public Object getQualifier() {
        return qualifier;
    }

    public Object getTitle() {
        return title;
    }

    public String getRole() {
        return role;
    }

    public String getOrganization() {
        return organization;
    }

    public Integer getRank() {
        return rank;
    }
}
