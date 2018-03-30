package com.example.michel.mynews.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.mynews.R;

import java.util.List;

/**
 * Created by michel on 03/02/2018.
 */

public class NYTAdapter extends RecyclerView.Adapter<NYTWiewHolder> {

    // CREATE LIST OBJECT
    List<MonObjet> monObjets;

    // CREATE CONSTRUCTOR WITH OBJECT VALUE
    public NYTAdapter(List<MonObjet> monObjets) {
        this.monObjets = monObjets;
    }

    // CEATE METHOD MonObjet WHITH INT
    public MonObjet myObjectAdapter(int position){
        return this.monObjets.get(position);
    }

    // CALL METHOD ONCREATEVIEWHOLDER TO CONNECT layout fragment item
    @Override
    public NYTWiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_topstories, parent, false);
        return new NYTWiewHolder(view);
    }

    // CREATE METHOD ONBINDVIEWHOLDER
    // GIVE POSITION
    @Override
    public void onBindViewHolder(NYTWiewHolder nytWiewHolder, int position) {
        MonObjet monObjet = monObjets.get(position);
        nytWiewHolder.bindtwo(monObjet);
    }

    // CREATE GETITEMCOUNT
    // CALCUL SIZE
    @Override
    public int getItemCount() {
        return monObjets.size();
    }
}



