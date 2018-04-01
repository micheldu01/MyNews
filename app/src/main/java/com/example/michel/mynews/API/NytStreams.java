package com.example.michel.mynews.API;

import com.example.michel.mynews.API.MostPopular.MostPopular;
import com.example.michel.mynews.API.SearchArticleAPI.SearchActicleAPI;
import com.example.michel.mynews.API.TopStories.TopStoriesAPI;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by michel on 02/02/2018.
 */

public class NytStreams {

    // CREATE OBSERVABLE FOR GET STREAM TOP STORIES NYT
    public static Observable<TopStoriesAPI> streamTopStories() {
        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getTopStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);

    }

    // CREATE OBSERVABLE FOR GET STREAM MOST POPULAR
    public static Observable<MostPopular> streamMostPopular() {
        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);

    }

    // CREATE OBSERVABLE FOR GET STREAM BUSINESS
    public static Observable<SearchActicleAPI> streamBusiness() {
        String str = "business";
        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getBusiness(str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    // CREATE OBSERVABLE FOR GET STREAM SEARCH ACTIVITY
    public static Observable<SearchActicleAPI> streamSearchActivity(String term,String beguin_date,String end_date, Boolean bb) {

        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getSearchActicles(term, beguin_date,end_date, bb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    // CREATE OBSERVABLE FOR GET STREAM NOTIFICATION
    public static Observable<SearchActicleAPI> streamNotification(String term, Boolean bb) {
        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getNotification(term, bb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}








