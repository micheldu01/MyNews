package com.example.michel.mynews.Notification;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by michel on 06/04/2018.
 */
public class NotificationServiceTest {

    // DELCLARE VALUE
    private String str;
    private String result;

    // TEST
    @Test
    public void name() throws Exception {

        assertEquals(2, 1 + 1);
    }

    // TEST GET STRING BOX
    @Test
    public void myHTTPalarmLOOP() throws Exception {

        // TEST THE LOOP
        String[] choix = {"", "", "", "", "", ""};
        String[] choix2 = {"arts", "business", "entrepreneurs", "politics", "sports", "travel"};

        int a = 0;
        while (a < 6) {
            choix[a] = choix2[a];
            a++;
        }

        assertEquals("business", choix2[1]);

    }



    // COMPARE THE TITLE ACTUAL AND SAVE TITLE
    @Test
    public void myHTTPalarmCOMPARE() throws Exception {

        // ACTUAL TITLE
        String actualTitle = "michel";

        // SAVED TITLE
        String savedTitle = "laurent";

        // COMPARE THEM
        // if actual titre is different to the saved title do ....
        if(!actualTitle.equals(savedTitle)){
            result = "the title is different";
        }
        else{
            result = "the title is the same";
        }



        assertEquals("the title is different",result);

    }
}

























