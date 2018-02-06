package com.example.michel.mynews.API;

import com.example.michel.mynews.API.TopStoriesMostPopular.TopStoriesAPI;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by michel on 03/02/2018.
 */

public interface NytService {

    @GET("svc/topstories/v2/home.json?api-key=c69e095eadba4c708c5d4ffeb0699a41")
    Observable<TopStoriesAPI> getTopStories();

    @GET("svc/mostpopular/v2/mostemailed/all-sections/7.json?api-key=c69e095eadba4c708c5d4ffeb0699a41")
    Observable<TopStoriesAPI> getMostPopular();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
