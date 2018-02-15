package com.example.michel.mynews;

import com.example.michel.mynews.API.NytStreams;
import com.example.michel.mynews.API.TopStoriesMostPopular.TopStoriesAPI;

import org.junit.Test;

import java.util.List;
import java.util.Observable;

import io.reactivex.observers.TestObserver;

/**
 * Created by michel on 15/02/2018.
 */

public class NyStreamsTest {

    @Test
    public void topStoriesTest() throws Exception {

        // 1 - Get the stream
        //     Recupération de la stream
        io.reactivex.Observable<TopStoriesAPI> observableTopStoriesAPI =
                NytStreams.streamTopStories();

        // - 2 Create a new TestObserver
        //     Création d'un nouveau TestObserver
        TestObserver<TopStoriesAPI> testObserver = new TestObserver<>();

        // 3 - Launch observable
        //     Lancement d'un observable
        observableTopStoriesAPI.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if  no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // - Await the stream terminated before continue

    }

    @Test
    public void MostPopularTest() throws Exception {

        // 1 - Get the stream
        //     Recupération de la stream
        io.reactivex.Observable<TopStoriesAPI> observableTopStoriesAPI =
                NytStreams.streamMostPopular();

        // - 2 Create a new TestObserver
        //     Création d'un nouveau TestObserver
        TestObserver<TopStoriesAPI> testObserver = new TestObserver<>();

        // 3 - Launch observable
        //     Lancement d'un observable
        observableTopStoriesAPI.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if  no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // - Await the stream terminated before continue


    }
}