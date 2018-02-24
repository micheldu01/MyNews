package com.example.michel.mynews;

import com.example.michel.mynews.API.NytStreams;
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
        assertThat("result NYT",  storiesAPI.getResults() !=  null);

        assertThat("result NYT",  storiesAPI.getResults().get(0).getTitle() !=  null);

        assertThat("result NYT",  storiesAPI.getResults().get(0).getMultimedia().get(0).getUrl() !=  null);

    }
}