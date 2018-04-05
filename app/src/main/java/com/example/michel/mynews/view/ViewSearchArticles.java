package com.example.michel.mynews.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.michel.mynews.API.NytStreams;
import com.example.michel.mynews.API.SearchArticleAPI.SearchActicleAPI;
import com.example.michel.mynews.API.TopStories.TopStoriesAPI;
import com.example.michel.mynews.R;
import com.example.michel.mynews.RecyclerView.ItemClickSupport;
import com.example.michel.mynews.RecyclerView.MonObjet;
import com.example.michel.mynews.RecyclerView.NYTAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.example.michel.mynews.view.SearchActivity.MyCheckBox;
import static com.example.michel.mynews.view.SearchActivity.MyDateEnd;
import static com.example.michel.mynews.view.SearchActivity.MyDateStart;
import static com.example.michel.mynews.view.SearchActivity.MyEditText;
import static com.example.michel.mynews.view.SearchActivity.MyShared;

public class ViewSearchArticles extends AppCompatActivity {


    //DECLARE DISPOSABLE
    private Disposable disposable;
    //RECYCLER VIEW NYT
    private NYTAdapter nytAdapter;
    private TopStoriesAPI nYresult;
    private List<MonObjet> monObjetList = new ArrayList<>();
    private Context context;
    // CREATE ARRAY FOR GET URL
    private List<String> urlArray = new ArrayList<>();
    //PREFERENCES
    private SharedPreferences mpreferences;
    //STRING
    private String[] choix = {"","","","","",""};
    // TEST
    String dateStart;
    String dateEnd;
    String term;
    String testShared;




    //IMPLEMENT RECYCLER VIEW
    @BindView(R.id.fragment_main_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_main_swipe_container)
    SwipeRefreshLayout refreshLayout;

    //IMPLEMENT TEXT VIEW IF THEY NO ARE RESPONSE IN API
    @BindView(R.id.text_search_articles)
    TextView no_response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_search_articles);


        //DECLARE SHARED
        mpreferences = getSharedPreferences (MyShared, Context.MODE_PRIVATE);

        //DECLARE BUTTERKNIFE
        ButterKnife.bind(this);

        //DECLARE THE SWIPE FOR ADD NEW ARTICLES
        this.configureSwipeRefreshLayout();

        //DECLARE THE ONCLICK FOR USE THE  METHOD TO COLL THE ARTICLE VIEW IN A NEW VIEW
        this.configureOnClickRecyclerView();

        //DECLARE THE METHOD TO SHOW ARTICLES OF NYT
        this.recyclerViewHTTPNYT();

        //show the toolbar
        this.configureToolbar();



    }

    private void configureToolbar() {
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    //IMPLEMENT METHOD FOR REFRESH ARTICLES
    private void configureSwipeRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerViewHTTPNYT();
            }
        });
    }

    //configure item click on RecyclerView
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.activity_view_search_articles)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        // INTENT FOR SHOW ARTICLES NYT
                        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(urlArray.get(position)));
                        startActivity(browserIntent);
                    }
                });
    }

    // 1 - Execute our Stream
    private void recyclerViewHTTPNYT(){

        //---------------------------
        //  IMPLEMENT AND USE SHARED
        //---------------------------

        dateStart = mpreferences.getString(MyDateStart,"");
        dateEnd = mpreferences.getString(MyDateEnd,"");
        term = mpreferences.getString(MyEditText,"");
        Log.e("mynews","recuperation du term = "+ term);
        // ----------------------------
        // PROBLEM WITH SHARED
        //-----------------------------
        Log.e("mynews","recuperation de la date = "+ dateStart);


        int a = 0;
        while (a < 6){
            choix[a] = mpreferences.getString(MyCheckBox[a],"");
            mpreferences.edit().putString(MyCheckBox[a],"").commit();
            Log.e("mynews","choix des cases à cocher = " + choix[a]);
            a ++;
        }


        // 1.2 - Execute the stream subscribing to Observable defined inside GithubStream
        this.disposable = NytStreams.streamSearchActivity( term +choix[0] + choix[1] + choix[2] + choix[3] + choix[4] + choix[5],dateStart,dateEnd, true)
                .subscribeWith(new DisposableObserver<SearchActicleAPI>() {

                    @Override
                    public void onNext(SearchActicleAPI searchActicleAPI) {

                        monObjetList.clear();

                        if (searchActicleAPI.getResponse().getDocs().size() == 0){

                            no_response.setText(R.string.no_article);

                        }
                        else {

                            String[] strstories = new String[searchActicleAPI.getResponse().getDocs().size()];
                            for(int i = 0; i < searchActicleAPI.getResponse().getDocs().size(); i++){


                                //--------------------------------
                                //  CREATE IF AND ELSE
                                //  IF THEY ARE OR NOT ARE PICTURE
                                //--------------------------------

                                if(searchActicleAPI.getResponse().getDocs().get(i).getMultimedia().size() == 0){


                                    //implement monObjetList for set data in recycler view
                                    monObjetList.add(new MonObjet(searchActicleAPI.getResponse().getDocs().get(i).getHeadline().getMain(),
                                            searchActicleAPI.getResponse().getDocs().get(i).getPubDate(),
                                            searchActicleAPI.getResponse().getDocs().get(i).getSectionName()));


                                    // implement urlArray for get URL
                                    urlArray.add(new String(searchActicleAPI.getResponse().getDocs().get(i).getWebUrl()));
                                }

                                else {

                                    //implement monObjetList for set data in recycler view
                                    monObjetList.add(new MonObjet(searchActicleAPI.getResponse().getDocs().get(i).getHeadline().getMain(),
                                            searchActicleAPI.getResponse().getDocs().get(i).getPubDate(),
                                            searchActicleAPI.getResponse().getDocs().get(i).getSectionName(),
                                            "https://static01.nyt.com/"+searchActicleAPI.getResponse().getDocs().get(i).getMultimedia().get(0).getUrl()));


                                    // implement urlArray for get URL
                                    urlArray.add(new String(searchActicleAPI.getResponse().getDocs().get(i).getWebUrl()));
                                }

                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setAdapter(new NYTAdapter(monObjetList));

                            // 3 - Stop refreshing and clear actual list of users
                            refreshLayout.setRefreshing(false);

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG","On Error"+Log.getStackTraceString(e));
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG","On Complete !!");
                    }
                });
    }

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    public void onDestroy(){
        super.onDestroy();
        this.disposeWhenDestroy();
    }
}




