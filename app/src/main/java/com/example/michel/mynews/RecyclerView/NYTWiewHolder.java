package com.example.michel.mynews.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.michel.mynews.R;

/**
 * Created by michel on 03/02/2018.
 */

public class NYTWiewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private TextView textViewdate;
    private ImageView imageView;
    private TextView section;
    private TextView subsection;


    public NYTWiewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.title_recycler);
        textViewdate = (TextView) itemView.findViewById(R.id.date_recycler);
        imageView =(ImageView) itemView.findViewById(R.id.image_recycler);
        section = (TextView) itemView.findViewById(R.id.section_recycler);
        subsection = (TextView) itemView.findViewById(R.id.subsection_recycler);


    }

    public void bindtwo(MonObjet monObjet){
        textView.setText(monObjet.getTitle());
        textViewdate.setText(monObjet.getDate());
        section.setText(monObjet.getSection());
        Glide.with(imageView.getContext()).load(monObjet.getImage()).into(imageView);

    }
}
