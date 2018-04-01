package com.example.michel.mynews.API;

import com.example.michel.mynews.API.MostPopular.MostPopular;
import com.example.michel.mynews.API.SearchArticleAPI.SearchActicleAPI;
import com.example.michel.mynews.API.TopStories.TopStoriesAPI;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by michel on 03/02/2018.
 */

public interface NytService {

    // CREATE REQUEST FOR TOP STORIES API NYT
    @GET("svc/topstories/v2/home.json?api-key=c69e095eadba4c708c5d4ffeb0699a41")
    Observable<TopStoriesAPI> getTopStories();

    // CREATE REQUEST FOR MOST POPULAR API NYT
    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=c69e095eadba4c708c5d4ffeb0699a41")
    Observable<MostPopular> getMostPopular();

    // CREATE REQUEST FOR BUSINESS API NYT
    @GET("svc/search/v2/articlesearch.json?api-key=c69e095eadba4c708c5d4ffeb0699a41")
    Observable<SearchActicleAPI> getBusiness(@Query("q") String term);

    // CREATE REQUEST FOR SEARCH ARTICLES API NYT
    @GET("svc/search/v2/articlesearch.json?api-key=c69e095eadba4c708c5d4ffeb0699a41")
    Observable<SearchActicleAPI> getSearchActicles(
            @Query("q") String term,
            @Query("begin_date") String begin_date,
            @Query("end_date") String end_date,
            @Query("facet_filter") boolean bb);

    // CREATE REQUEST FOR ARTICLES NOTIFICATION API NYT
    @GET("svc/search/v2/articlesearch.json?api-key=c69e095eadba4c708c5d4ffeb0699a41")
    Observable<SearchActicleAPI> getNotification(
            @Query("q") String term,
            @Query("facet_filter") boolean bb);

    // USE RETROFIT FOR GET DATA
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}

