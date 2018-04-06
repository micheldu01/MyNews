package com.example.michel.mynews.FragmentsView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.mynews.API.NytStreams;
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


// create TopStoriesFragment
public class TopStoriesFragment extends Fragment {

    //DECLARE DISPOSABLE FOR GET STREAMS
    private Disposable disposable;
    //DECLARE MONOBJET FOR GET STREAM IN A OBJET
    private List<MonObjet> monObjetList = new ArrayList<>();
    private Context context;
    // create Array for get and save URL
    private List<String> urlArray = new ArrayList<>();
    // DECLARE TOPSTORIES
    private TopStoriesAPI nYresult = new TopStoriesAPI();


    // IMPLEMENT RECYCLER VIEW
    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.fragment_main_swipe_container) SwipeRefreshLayout refreshLayout;



    // create constructor
    public static TopStoriesFragment newInstance() {
        return (new TopStoriesFragment());
    }

    @Override
    // Inflate the layout for this fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_top_stories, container, false);

        //implement butterKnife
        ButterKnife.bind(this,view);

        //call method for refreshLayout
        this.configureSwipeRefreshLayout();

        // call method for call Articles
        this.recyclerViewHTTPNYT();

        // call method for use recycler view
        this.configureOnClickRecyclerView();

        return view;

}

    //configure item click on RecyclerView
    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_item_topstories)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.e("TAG", "Position  :  " + position);


                        // INTENT FOR SHOW ARTICLES NYT
                        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(urlArray.get(position)));
                        startActivity(browserIntent);


                    }
                });
    }

    //methode for use SwipeRefresh
    private void configureSwipeRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerViewHTTPNYT();

            }
        });
    }

    // 1 - Execute our Stream
    private void recyclerViewHTTPNYT(){

        // 1.2 - Execute the stream subscribing to Observable defined inside GithubStream
        this.disposable = NytStreams.streamTopStories()
                .subscribeWith(new DisposableObserver<TopStoriesAPI>() {


                    @Override
                    public void onNext(TopStoriesAPI nYresult) {

                        //delete date in array for refresh articles
                        monObjetList.clear();
                        urlArray.clear();

                        Log.e("mynews","TopStoriesFragment URL image" + nYresult.getResults().get(0).getMultimedia().get(0).getUrl());

                        // CREATE ARRAY FOR GET DATA FROM NYT AND RETURN IT IN RECYCLER VIEW

                        methodGetArticles(nYresult, monObjetList);


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

    private void methodGetArticles(TopStoriesAPI nYresult, List<MonObjet> monObjetList){


        // GET SIZE
        for(int i = 0; i < nYresult.getResults().size(); i++){

            //--------------------------------
            //  CREATE IF AND ELSE
            //  IF THEY ARE OR NOT ARE PICTURE
            //--------------------------------

            if(nYresult.getResults().get(i).getMultimedia().size() == 0){

                //implement monObjetList for set data in recycler view
                monObjetList.add(new MonObjet(nYresult.getResults().get(i).getTitle(),
                        nYresult.getResults().get(i).getPublishedDate(),
                        nYresult.getResults().get(i).getSection()));
                Log.e("mynews","TopStorie  recupération du titre  = " + nYresult.getResults().get(i).getTitle());


                // implement urlArray for get URL
                urlArray.add(new String(nYresult.getResults().get(i).getUrl()));
            }

            else {

                //implement monObjetList for set data in recycler view
                monObjetList.add(new MonObjet(nYresult.getResults().get(i).getTitle(),
                        nYresult.getResults().get(i).getPublishedDate(),
                        nYresult.getResults().get(i).getSection(),
                        nYresult.getResults().get(i).getMultimedia().get(0).getUrl()));


                // implement urlArray for get URL
                urlArray.add(new String(nYresult.getResults().get(i).getUrl()));
            }



        }
        // implement recycler view with setLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        // implement recycler view with adapter
        recyclerView.setAdapter(new NYTAdapter(monObjetList));

        // 3 - Stop refreshing and clear actual list of users
        refreshLayout.setRefreshing(false);

    }

}

