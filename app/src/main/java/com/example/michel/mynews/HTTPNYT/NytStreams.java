package com.example.michel.mynews.HTTPNYT;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by michel on 02/02/2018.
 */

public class NytStreams {

    public static io.reactivex.Observable<NYresult> streamTopStories() {
        NytService nytService = NytService.retrofit.create(NytService.class);
        return nytService.getTopStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
