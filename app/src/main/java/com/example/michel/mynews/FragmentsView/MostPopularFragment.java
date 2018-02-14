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

import com.example.michel.mynews.API.TopStoriesMostPopular.TopStoriesAPI;
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


public class MostPopularFragment extends Fragment {

    private Disposable disposable;
    //RECYCLER VIEW NYT
    private NYTAdapter nytAdapter;
    private TopStoriesAPI nYresult;
    private List<MonObjet> monObjetList = new ArrayList<>();
    private Context context;
    // CREATE ARRAY FOR GET URL
    private List<String> urlArray = new ArrayList<>();


    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.fragment_main_swipe_container) SwipeRefreshLayout refreshLayout;


    public static MostPopularFragment newInstance() {
        return (new MostPopularFragment());
    }


    @Override
    // Inflate the layout for this fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_most_popular, container, false);

        ButterKnife.bind(this,view);

        this.configureSwipeRefreshLayout();

        this.recyclerViewHTTPNYT();

        return view;

    }

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
        this.disposable = NytStreams.streamMostPopular()
                .subscribeWith(new DisposableObserver<TopStoriesAPI>() {

                    @Override
                    public void onNext(TopStoriesAPI nYresult) {

                        //CLEAR  THE ARRAY FOR USE SWIPE REFRESH
                        monObjetList.clear();
                        urlArray.clear();



                        String[] strstories = new String[nYresult.getResults().size()];
                        for(int i = 0; i < nYresult.getResults().size(); i++){
                            monObjetList.add(new MonObjet(nYresult.getResults().get(i).getTitle(),
                                    nYresult.getResults().get(i).getPublishedDate(),
                                    nYresult.getResults().get(i).getSection()));

                            // Implement urlArray for get URL
                            urlArray.add(new String(nYresult.getResults().get(i).getUrl()));


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
