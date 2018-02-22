package com.example.michel.mynews.RecyclerView;

/**
 * Created by michel on 04/02/2018.
 */

public class MonObjet {

    private String Title;

    private String date;

    private String section;

    private String image;


    public MonObjet(String title, String date, String section, String image) {
        Title = title;
        this.date = date;
        this.section = section;
        this.image = image;
    }

    public MonObjet(String title, String date, String section) {
        Title = title;
        this.date = date;
        this.section = section;
    }

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
