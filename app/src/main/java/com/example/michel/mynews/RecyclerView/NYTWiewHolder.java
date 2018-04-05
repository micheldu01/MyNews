package com.example.michel.mynews.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.michel.mynews.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by michel on 03/02/2018.
 */

public class NYTWiewHolder extends RecyclerView.ViewHolder {


    // ADD VALUE
    private TextView textView;
    private TextView textViewdate;
    private ImageView imageView;
    private TextView section;
    private TextView subsection;


    // CREATE CONSTRUCTOR WITH THE VALUES
    public NYTWiewHolder(View itemView) {
        super(itemView);

        //CALL VALUE
        textView = (TextView) itemView.findViewById(R.id.title_recycler);
        textViewdate = (TextView) itemView.findViewById(R.id.date_recycler);
        imageView = (ImageView) itemView.findViewById(R.id.image_recycler);
        section = (TextView) itemView.findViewById(R.id.section_recycler);
        subsection = (TextView) itemView.findViewById(R.id.subsection_recycler);

    }

    //------------------------------
    //  METHOD FOR IMPLEMENT VALUE
    //  AND REFACTORING STRING DATE
    //------------------------------

    // CREATE METHOD
    public void bindtwo(MonObjet monObjet) {

        // IMPLEMENT VALUE
        textView.setText(monObjet.getTitle());

        // ASK IF THERE ARE DATE
        if (monObjet.getDate() != null) {

            // IF THERE ARE DATE TRY TO CHANGE STRING DATE
            try {

                // CALL FORMAT DATE NYT DATE
                SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");

                // CREATE FORMAT DATE FOR APPLI
                SimpleDateFormat newsp = new SimpleDateFormat("dd/MM/yy");

                // CONVERT DATE NYT INTO STRING
                Date date = sp.parse(monObjet.getDate());

                // CONVERT STRING DATE INTO NEW FORMAT DATE FOR APPLI
                String string = newsp.format(date);

                // IMPLEMENT VALUE
                textViewdate.setText(String.valueOf(string));
                section.setText(monObjet.getSection());
                Glide.with(imageView.getContext()).load(monObjet.getImage()).into(imageView);


                // IF EXCEPTION
            } catch (ParseException e) {

                Log.e("mynews", "View Holder recuperation de la date ne marche pas  ");

                // IMPLEMENT VALUE
                textViewdate.setText(monObjet.getDate());
                section.setText(monObjet.getSection());
                Glide.with(imageView.getContext()).load(monObjet.getImage()).into(imageView);

                e.printStackTrace();
            }
        }
    }
}
