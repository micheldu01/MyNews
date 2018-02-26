package com.example.michel.mynews.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.michel.mynews.API.MostPopular.MostPopular;
import com.example.michel.mynews.API.SearchArticleAPI.SearchActicleAPI;
import com.example.michel.mynews.API.TopStories.TopStoriesAPI;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.michel.mynews.view.SearchActivity.MyCheckBox;
import static com.example.michel.mynews.view.SearchActivity.MyDateEnd;
import static com.example.michel.mynews.view.SearchActivity.MyDateStart;
import static com.example.michel.mynews.view.SearchActivity.MyEditText;
import static com.example.michel.mynews.view.SearchActivity.MyShared;

/**
 * Created by michel on 02/02/2018.
 */

public class NytStreams {


    public static Observable<TopStoriesAPI> streamTopStories() {
        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getTopStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);

    }

    public static Observable<MostPopular> streamMostPopular() {
        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);

    }

    public static Observable<SearchActicleAPI> streamBusiness() {

        String str = "business";

        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getBusiness(str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<SearchActicleAPI> streamSearchActivity(Context ctx) {

        SharedPreferences preferences = ctx.getSharedPreferences(MyShared, Context.MODE_PRIVATE);
        String term = preferences.getString(MyEditText, "");
        String beginDate = preferences.getString(MyDateStart, "");
        String endDate = preferences.getString(MyDateEnd, "");
        String section = preferences.getString(MyCheckBox[0], "");
        boolean bb = true;

        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getSearchActicles(term, beginDate, endDate, section, bb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}

