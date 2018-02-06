package com.example.michel.mynews.API;

import com.example.michel.mynews.API.TopStories.TopStoriesAPI;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by michel on 02/02/2018.
 */

public class NytStreams {

    public static io.reactivex.Observable<TopStoriesAPI> streamTopStories() {
        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getTopStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static io.reactivex.Observable<TopStoriesAPI> streamMostPopular() {
        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
