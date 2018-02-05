package com.example.michel.mynews.RecyclerView;

/**
 * Created by michel on 04/02/2018.
 */

public class MonObjet {

    private String Title;

    private String date;

    private String section;

    private String subsection;

    public MonObjet(String title, String date, String section, String subsection) {
        Title = title;
        this.date = date;
        this.section = section;
        this.subsection = subsection;
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

    public String getSubsection() {
        return subsection;
    }
}
