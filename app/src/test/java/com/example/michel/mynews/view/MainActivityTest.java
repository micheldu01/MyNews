package com.example.michel.mynews.view;

import android.widget.Toolbar;

import org.junit.Test;
import org.mockito.Mock;

import static junit.framework.Assert.assertEquals;

/**
 * Created by michel on 30/03/2018.
 */
public class MainActivityTest {


     @Mock
     Toolbar toolbar;



     @Test
     public void configureToolbarTest() throws Exception {

          assertEquals(toolbar,toolbar);

     }
}