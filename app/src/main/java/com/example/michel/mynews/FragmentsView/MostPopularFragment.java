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

import com.example.michel.mynews.API.MostPopular.MostPopular;
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

    //DECLARE DISPOSABLE FOR GET STREAMS
    private Disposable disposable;
    //DECLARE OBJECT FOR GET TREAMS DATA INSIDE
    private List<MonObjet> monObjetList = new ArrayList<>();
    private Context context;
    // CREATE ARRAY FOR GET URL
    private List<String> urlArray = new ArrayList<>();


    //IMPLEMENT RECYCLER VIEW
    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.fragment_main_swipe_container) SwipeRefreshLayout refreshLayout;


    public static MostPopularFragment newInstance() {
        return (new MostPopularFragment());
    }


    @Override
    // Inflate the layout for this fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_most_popular, container, false);


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

    // METHOD SWIPE
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
                .subscribeWith(new DisposableObserver<MostPopular>() {

                    @Override
                    public void onNext(MostPopular mostPopular) {

                        //CLEAR  THE ARRAY FOR USE SWIPE REFRESH
                        monObjetList.clear();
                        urlArray.clear();

                        Log.e("mynews","test most populare ============   " + mostPopular.getResults().get(0).getTitle());


                        // GET SIZE
                        for(int i = 0; i <mostPopular.getResults().size(); i++){

                            //--------------------------------
                            //  CREATE IF AND ELSE
                            //  IF THEY ARE OR NOT ARE PICTURE
                            //--------------------------------

                            // GET SIZE OF MOST POPULAR OBJECT
                            if(mostPopular.getResults().get(i).getMedia().size() == 0){

                                //implement monObjetList for set data in recycler view
                                monObjetList.add(new MonObjet(mostPopular.getResults().get(i).getTitle(),
                                        mostPopular.getResults().get(i).getPublishedDate(),
                                        mostPopular.getResults().get(i).getSection()));

                                // implement urlArray for get URL
                                urlArray.add(new String(mostPopular.getResults().get(i).getUrl()));
                            }

                            else {

                                //implement monObjetList for set data in recycler view
                                monObjetList.add(new MonObjet(mostPopular.getResults().get(i).getTitle(),
                                        mostPopular.getResults().get(i).getPublishedDate(),
                                        mostPopular.getResults().get(i).getSection(),
                                        mostPopular.getResults().get(i).getMedia().get(0).getMedia_metadata().get(0).getUrl()));

                                // implement urlArray for get URL
                                urlArray.add(new String(mostPopular.getResults().get(i).getUrl()));

                            }


                        }
                        // IMPLEMENT RECYCLER VIEW
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
