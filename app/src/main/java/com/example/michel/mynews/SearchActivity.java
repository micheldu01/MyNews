package com.example.michel.mynews;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity
    implements BtnSearchFragment.OnButtonClickedListener{

    //EditText
    private EditText editText;
    //CheckBox
    private CheckBox arts;
    private CheckBox business;
    private CheckBox entrepreneurs;
    private CheckBox politics;
    private CheckBox travel;
    private CheckBox sport;
    private CheckBox[] arrayBox;
    private int[] arrayIdBox;
    private int numberarray = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.configureToolbar();

        //EditText
        editText = (EditText)findViewById(R.id.search_query_term);
        //CheckBox
        while(numberarray <6){
            arrayBox[numberarray] = (CheckBox)findViewById(arrayIdBox[numberarray]);
            Log.i("mycoursviewpager","number = " + numberarray);
            numberarray++;
        }

    }
    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onButonClicked(View view) {
        // receive the editText
        String et = editText.getText().toString();

        Log.i("mycoursviewpager", "Button clicked = ");
        Toast.makeText(SearchActivity.this," Button clicked ", Toast.LENGTH_LONG).show();


    }
}
