package com.example.michel.mynews.view;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by michel on 12/04/2018.
 */
public class SearchActivityTest {


    @Test
    public void test() throws Exception {

        assertEquals(4,2+2);
    }

    // METHOD FOR ADD "0" INTO CURRENT DATE IF < 10
    @Test
    public void testConvertDate() throws Exception {

        // FOR TEST IF INPUT >= 10
        int input = 8;

        // STRING FOR MAKE TEST
        String tst;

        // IF INPUT IS >= 10 DO NOT ADD BEFORE "0"
        if (input >= 10) {
            // FOR TEST
            tst = "0";
            // DONT ADD "0"
            // return = String.valueOf(input);

        } else {
            // FOR TEST
            tst = "0";
            // IF INPUT < 10 ADD "0" BEFORE
            //return = "0" + String.valueOf(input);
        }

        assertEquals("0",tst);

    }



}


















































