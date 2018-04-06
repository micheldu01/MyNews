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


public class BusinessFragment extends Fragment {

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


    public static BusinessFragment newInstance() {
        return (new BusinessFragment());
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
        this.disposable = NytStreams.streamBusiness()
                .subscribeWith(new DisposableObserver<SearchActicleAPI>() {

                    // CALL METHOD ON NEXT FOR GET DATE INTO ARRAYS
                    @Override
                    public void onNext(SearchActicleAPI searchActicleAPI) {

                        // CLEAR MONOBJETLIST
                        monObjetList.clear();
                        urlArray.clear();

                        // CALL METHOD FOR GET ARTICLES INTO RECYCLER VIEW
                        methodGetArticles(searchActicleAPI,monObjetList);
                    }

                    // IF THERE ARE ERROR USE LOG
                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG","On Error"+Log.getStackTraceString(e));
                    }

                    // IF THERE ARE OK USE LOG
                    @Override
                    public void onComplete() {
                        Log.e("TAG","On Complete !!");
                    }
                });
    }

    // CLOSE THE STREAM FOR DISPOSABLE
    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // CLOSE THE STREAM
    public void onDestroy(){
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void methodGetArticles(SearchActicleAPI searchActicleAPI, List<MonObjet> monObjetList) {



        // GET SIZE OF ARTICLES
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
        // IMPLEMENT RECYCLER VIEW WITH MON OBJET LIST
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new NYTAdapter(monObjetList));

        // 3 - Stop refreshing and clear actual list of users
        refreshLayout.setRefreshing(false);

    }


}







































