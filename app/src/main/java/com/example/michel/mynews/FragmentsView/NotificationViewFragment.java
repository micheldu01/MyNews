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

import static com.example.michel.mynews.view.NotificationsActivity.MyCheckBoxNoti;
import static com.example.michel.mynews.view.NotificationsActivity.MyEditTextNoti;
import static com.example.michel.mynews.view.SearchActivity.MyCheckBox;
import static com.example.michel.mynews.view.SearchActivity.MyDateEnd;
import static com.example.michel.mynews.view.SearchActivity.MyDateStart;
import static com.example.michel.mynews.view.SearchActivity.MyEditText;
import static com.example.michel.mynews.view.SearchActivity.MyShared;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationViewFragment extends Fragment {

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
    private SharedPreferences preferences;


    //IMPLEMENT RECYCLER VIEW
    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.fragment_main_swipe_container) SwipeRefreshLayout refreshLayout;

    //IMPLEMENT TEXT VIEW IF THEY NO ARE RESPONSE IN API
    @BindView(R.id.text_notification_articles) TextView no_response;



    public static NotificationViewFragment newInstance() {return (new NotificationViewFragment());
    }

    @Override
    // Inflate the layout for this fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_view, container, false);

        //DECLARE SHARED
        preferences = this.getActivity().getSharedPreferences (MyShared, Context.MODE_PRIVATE);

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

        //---------------------------
        //  IMPLEMENT AND USE SHARED
        //---------------------------


        String term = preferences.getString(MyEditTextNoti,"");
        String dateStart = preferences.getString(MyDateStart,"");
        String dateEnd = preferences.getString(MyDateEnd,"");
        String[] choix = {"choix1","choix2","choix3","choix4","choix5","choix6"} ;

        int a = 0;
        while (a < 6){
            choix[a] = preferences.getString(MyCheckBoxNoti[a],"");
            a ++;
        }

        // 1.2 - Execute the stream subscribing to Observable defined inside GithubStream
        this.disposable = NytStreams.streamSearchActivity("michel",choix[0],choix[1],choix[2],choix[3],choix[4],choix[5], true)
                .subscribeWith(new DisposableObserver<SearchActicleAPI>() {

                    @Override
                    public void onNext(SearchActicleAPI searchActicleAPI) {

                        monObjetList.clear();

                        if (searchActicleAPI.getResponse().getDocs().size() == 0){

                            no_response.setText(R.string.no_article);

                            Log.e("mynew","zzzzzzzzzzzzzz  je fais un test zzzzzzzzzzzzzzz");
                        }
                        else {

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
