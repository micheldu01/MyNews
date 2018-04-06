package com.example.michel.mynews;

import com.example.michel.mynews.API.MostPopular.MostPopular;
import com.example.michel.mynews.API.NytStreams;
import com.example.michel.mynews.API.SearchArticleAPI.SearchActicleAPI;
import com.example.michel.mynews.API.TopStories.TopStoriesAPI;

import org.junit.Test;

import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;

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

        // 4 - Test if streamTopStories is empty
        TopStoriesAPI storiesAPI = testObserver.values().get(0);
        // 5 - Ask if getResults is different to null
        assertThat("result NYT", storiesAPI.getResults() != null);

        assertThat("result NYT", storiesAPI.getResults().get(0).getTitle() != null);

        assertThat("result NYT", storiesAPI.getResults().get(0).getMultimedia().get(0).getUrl() != null);

    }

    @Test
    public void mostPopularTest() throws Exception {

        // 1 - Get the stream
        //     Recupération de la stream
        io.reactivex.Observable<MostPopular> observableMostPopular =
                NytStreams.streamMostPopular();

        // - 2 Create a new TestObserver
        //     Création d'un nouveau TestObserver
        TestObserver<MostPopular> testObserver = new TestObserver<>();

        // 3 - Launch observable
        //     Lancement d'un observable
        observableMostPopular.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if  no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // - Await the stream terminated before continue

        // 4 - Test if streamTopStories is empty
        MostPopular mostPopular = testObserver.values().get(0);
        // 5 - Ask if getResults is different to null
        assertThat("result NYT",  mostPopular.getResults() !=  null);

    }

    @Test
    public void businessTest() throws Exception {

        // 1 - Get the stream
        //     Recupération de la stream
        io.reactivex.Observable<SearchActicleAPI> observableBusiness =
                NytStreams.streamBusiness();

        // - 2 Create a new TestObserver
        //     Création d'un nouveau TestObserver
        TestObserver<SearchActicleAPI> testObserver = new TestObserver<>();

        // 3 - Launch observable
        //     Lancement d'un observable
        observableBusiness.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if  no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // - Await the stream terminated before continue

        // 4 - Test if streamTopStories is empty
        SearchActicleAPI searchActicleAPI = testObserver.values().get(0);
        // 5 - Ask if getResults is different to null
        assertThat("result NYT",  searchActicleAPI.getResponse() !=  null);

        //assertThat("result NYT",  searchActicleAPI.getResponse().getDocs().get(0).getMultimedia().get(0).getUrl() !=  null);


    }

    @Test
    public void searchArticlesTest() throws Exception {

        // 1 - Get the stream
        //     Recupération de la stream
        io.reactivex.Observable<SearchActicleAPI> observableBusiness =
                NytStreams.streamSearchActivity("art","20180321","20180328",true);

        // - 2 Create a new TestObserver
        //     Création d'un nouveau TestObserver
        TestObserver<SearchActicleAPI> testObserver = new TestObserver<>();

        // 3 - Launch observable
        //     Lancement d'un observable
        observableBusiness.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if  no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // - Await the stream terminated before continue

        // 4 - Test if streamTopStories is empty
        SearchActicleAPI searchActicleAPI = testObserver.values().get(0);
        // 5 - Ask if getResults is different to null
        assertThat("result NYT",  searchActicleAPI.getResponse() !=  null);

        assertThat("result NYT",  searchActicleAPI.getResponse().getDocs().get(0).getMultimedia().get(0).getUrl() !=  null);

    }

    @Test
    public void searchNotification() throws Exception {

        // 1 - Get the stream
        //     Recupération de la stream
        io.reactivex.Observable<SearchActicleAPI> observableBusiness =
                NytStreams.streamNotification("art",false);

        // - 2 Create a new TestObserver
        //     Création d'un nouveau TestObserver
        TestObserver<SearchActicleAPI> testObserver = new TestObserver<>();

        // 3 - Launch observable
        //     Lancement d'un observable
        observableBusiness.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if  no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // - Await the stream terminated before continue

        // 4 - Test if streamTopStories is empty
        SearchActicleAPI searchActicleAPI = testObserver.values().get(0);
        // 5 - Ask if getResults is different to null
        assertThat("result NYT",  searchActicleAPI.getResponse() !=  null);

        assertThat("result NYT",  searchActicleAPI.getResponse().getDocs().get(0).getMultimedia().get(0).getUrl() !=  null);

    }
}

