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

    @GET("svc/topstories/v2/home.json?api-key=c69e095eadba4c708c5d4ffeb0699a41")
    Observable<TopStoriesAPI> getTopStories();

    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=c69e095eadba4c708c5d4ffeb0699a41")
    Observable<MostPopular> getMostPopular();

    @GET("svc/search/v2/articlesearch.json?api-key=c69e095eadba4c708c5d4ffeb0699a41")
    Observable<SearchActicleAPI> getBusiness(@Query("q") String term);

    @GET("svc/search/v2/articlesearch.json?api-key=c69e095eadba4c708c5d4ffeb0699a41")
    Observable<SearchActicleAPI> getSearchActicles(
            @Query("q") String term,
            @Query("fq=section_name:") String section1,
            @Query("fq=section_name:") String section2,
            @Query("fq=section_name:") String section3,
            @Query("fq=section_name:") String section4,
            @Query("fq=section_name:") String section5,
            @Query("fq=section_name:") String section6,
            @Query("facet_filter") boolean bb);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}

