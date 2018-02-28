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

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchArticlesFragment extends Fragment {

    //DECLARE DISPOSABLE
    private Disposable disposable;
    //RECYCLER VIEW NYT
    private NYTAdapter nytAdapter;
    private TopStoriesAPI nYresult;
    private List<MonObjet> monObjetList = new ArrayList<>();
    private Context context;
    // CREATE ARRAY FOR GET URL
    private List<String> urlArray = new ArrayList<>();

    //IMPLEMENT RECYCLER VIEW
    @BindView(R.id.fragment_main_recycler_view)    RecyclerView recyclerView;
    @BindView(R.id.fragment_main_swipe_container) SwipeRefreshLayout refreshLayout;


    public static SearchArticlesFragment newInstance() {
        return (new SearchArticlesFragment());
    }

    @Override
    // Inflate the layout for this fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_articles, container, false);


        //DECLARE BUTTERKNIFE
        ButterKnife.bind(this,view);

        //DECLARE THE SWIPE FOR ADD NEW ARTICLES
        this.configureSwipeRefreshLayout();

        //DECLARE THE ONCLICK FOR USE THE  METHOD TO COLL THE ARTICLE VIEW IN A NEW VIEW
        this.configureOnClickRecyclerView();

        //DECLARE THE METHOD TO SHOW ARTICLES OF NYT
        this.recyclerViewHTTPNYT();

        return view;

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
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_most_popular)
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

        // 1.2 - Execute the stream subscribing to Observable defined inside GithubStream
        this.disposable = NytStreams.streamSearchActivity()
                .subscribeWith(new DisposableObserver<SearchActicleAPI>() {

                    @Override
                    public void onNext(SearchActicleAPI searchActicleAPI) {

                        monObjetList.clear();

                        Log.e("mynews","(((((((((((((((((   URL  ))))))))))))))))" + searchActicleAPI.getResponse().getDocs().get(0).getMultimedia().get(0).getUrl());

                        String[] strstories = new String[searchActicleAPI.getResponse().getDocs().size()];
                        for(int i = 0; i < searchActicleAPI.getResponse().getDocs().size(); i++){
                            //--------------------------------
                            //  CREATE IF AND ELSE
                            //  IF THEY ARE OR NOT ARE PICTURE
                            //--------------------------------

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
                                        searchActicleAPI.getResponse().getDocs().get(i).getMultimedia().get(0).getUrl()));

                                // implement urlArray for get URL
                                urlArray.add(new String(searchActicleAPI.getResponse().getDocs().get(i).getWebUrl()));
                            }

                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
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

