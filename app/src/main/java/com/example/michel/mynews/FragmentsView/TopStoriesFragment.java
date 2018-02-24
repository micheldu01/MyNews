package com.example.michel.mynews.FragmentsView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.michel.mynews.API.TopStories.TopStoriesAPI;
import com.example.michel.mynews.API.NytStreams;
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

    private Disposable disposable;
    //RECYCLER VIEW NYT
    private NYTAdapter nytAdapter;
    private TopStoriesAPI nYresult;
    private List<MonObjet> monObjetList = new ArrayList<>();
    private Context context;
    //SharedPreferences
    protected SharedPreferences mSharedPreferences;
    public static final String MYSHARED = "MyShared";
    public static final String URL_NYT = "UrlNYT";
    // create Array for get and save URL
    private List<String> urlArray = new ArrayList<>();


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

        //implement SharedPreferences
        mSharedPreferences = this.getActivity().getSharedPreferences(MYSHARED, context.MODE_PRIVATE);

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


                        //--------
                        // TEST
                        //--------
                        //get URL for save it in a SharedPreferences
                        //implement ShredPreferences
                        mSharedPreferences.edit().putString(URL_NYT, urlArray.get(position)).commit();

                        // create intent between this activity and ShowArticleActivity
                        //startActivity(new Intent(getActivity(), ShowArticles.class));

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


                        //CREATE ARRAY FOR GET DATA FROM NYT AND RETURN IT IN RECYCLER VIEW

                        // Create array string for get data from nyt api
                        String[] strstories = new String[nYresult.getResults().size()];
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

