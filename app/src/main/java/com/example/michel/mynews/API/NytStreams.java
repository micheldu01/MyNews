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
        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getBusiness("business")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
