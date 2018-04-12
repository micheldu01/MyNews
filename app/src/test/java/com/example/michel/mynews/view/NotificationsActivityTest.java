package com.example.michel.mynews.view;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by michel on 08/04/2018.
 */
public class NotificationsActivityTest {

    // IF BOX IS CHECK OR NOT CHECK
    private String str_box;

    // IF  BOOLEAN IS TRUE OR FALSE
    private String booleen;

    // SHARED EDIT TEXT
    private String str_shared;

    // ARRAY FOR CHECK BOX
    private String[] arrayBox = new String[]{"arts", "business", "entrepreneurs", "politics", "travel", "sport"};
    private String[] arrayIdBox = new String[]{"arts_id", "business_id", "entrepreneurs_id", "politics_id", "travel_id", "sport_id"};

    // STRING EDIT TEXT
    private String edt;
    String str_test;



    @Test
    public void test() throws Exception {

        assertEquals(4,2+2);

    }

    // TEST THE METHOD SWITCH
    @Test
    public void testMethodSwitch() throws Exception {

        //----------------------
        // INTO ON TEXT CHANGED
        //----------------------

        // CHECKED BOOLEEN OF SWITCH
        // IF BOOLEAN EQUALS TRUE
        booleen = "b";
        if(booleen.equals("b")){

            // GET EDIT TEXT FOR KNOW IF IS IT NULL
            // IF TEXT
            String str_edit_text = "TEXT";


            // GET THE CHECK TEST OF BOX
            // IF CHECK
            str_box = "CHECK";

            // GET EDIT TEXT
            str_shared = "michel";

                // THE BOX IS CKEKED
                if(str_box.equals("CHECK") && !str_shared.equals("")){

                    //start alarmManager

                    // clear the shared
                    str_box = "NO_CHECK";

                }else{
                    String toast = "text for explain to check section and term";
                    // FOR TEST
                    str_box = "";
                }

                // TEST IF TEXT
                assertEquals("NO_CHECK",str_box);
        }

    }


    // FOR THE EDIT TEXT
    @Test
    public void testMethodEditText() throws Exception {

        //-------------------------
        // INTO AFTER TEXT CHANGED
        //------------------------

        // GET EDIT TEXT

        // SAVE THE EDIT TEXT IN SHARED

        // AND GET THE SHARED OF EDIT TEXT
        str_shared = "michel";

        // ASK IF EDIT TEXT IS EMPTY
        // if is not empty save NO_EMPTY in shared

        if(str_shared.equals("")){
            // save NO_TEXT in Shared
            // FOR TEST
            str_test = "test";

        }
        else {
            // SAVE TEXT IN SHARED
           // FOR TEST
            str_test = "";
        }

        // TEST IF EDIT TEXT IS EMPTY
        assertEquals("",str_test);


    }

    // METHOD FOR IMPLEMENT CHECKBOX
    @Test
    public void textMethodCheckBox() throws Exception {

        // CREATE NUMBER FOR IMPLEMENT CHECK BOX
        int numberarray = 0;

        // IMPLEMENT ARRAY BOX WITH WHILE
        while (numberarray < 6){

            arrayBox[numberarray] = arrayIdBox[numberarray];
            numberarray++;
        }

        assertEquals("arts_id", arrayBox[0]);

    }

}









































