package com.example.michel.mynews.RecyclerView;

/**
 * Created by michel on 04/02/2018.
 */

public class MonObjet {

    // CREATE VALUE
    private String Title;

    private String date;

    private String section;

    private String image;


    // CREATE CONSTRUCTOR WITHOUT VALUE
    public MonObjet() {}

    // CREATE CONSTRUCTOR WITH OVER VALUE
    public MonObjet(String title, String date, String section, String image) {
        Title = title;
        this.date = date;
        this.section = section;
        this.image = image;
    }

    // CREATE CONSTRUCTOR WITHOUT IMAGE VALUE
    public MonObjet(String title, String date, String section) {
        Title = title;
        this.date = date;
        this.section = section;
    }

    // CREATE GETTER OF ALL VALUES
    public String getTitle() {
        return Title;
    }

    public String getDate() {
        return date;
    }

    public String getSection() {
        return section;
    }

    public String getImage() {
        return image;
    }
}
