package com.example.michel.mynews.FragmentsView;

import com.example.michel.mynews.API.ArticlesTest;
import com.example.michel.mynews.API.SearchArticleAPI.SearchActicleAPI;
import com.example.michel.mynews.RecyclerView.MonObjet;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by michel on 04/04/2018.
 */
public class BusinessFragmentTest {

    // DECLARE VALUES
    private ArticlesTest articlesTest;
    private List<MonObjet> monObjets = new ArrayList<>();
    private List<MonObjet> monObjets2 = new ArrayList<>();
    // ARRAY FOR GET URL
    private List<String> urlArray = new ArrayList<>();
    // DECLARE SEARCH ARTICLES API
    SearchActicleAPI searchActicleAPI = mock(SearchActicleAPI.class);



    // TEST IF TEST RUN
    @Test
    public void myTest() throws Exception {

        assertEquals(2, 1 + 1);
    }

    // TEST ADD DATA INTO OBJECT
    @Test
    public void testAddData() throws Exception {

        // ADD DATE
        monObjets.add(new MonObjet("robert", "24/10", "Lyon"));

        // TEST METHOD
        assertEquals("robert", monObjets.get(0).getTitle());
    }


    // TEST ADD DATA INTO OBJECT
    @Test
    public void testAddData2() throws Exception {

        monObjets.add(new MonObjet("robert", "24/10", "Lyon"));


        // ADD DATA FROM MONOBJECT INTO ARTICLESTEST OBJECT
        articlesTest = new ArticlesTest(monObjets);


        // TEST METHOD IF TITLE MONOBJECTS EQUAL TITLE MONOBJECTS2 IN LOCAL
        assertEquals(monObjets.get(0).getDate(), articlesTest.getMesarticlesTest().get(0).getDate());

    }


    // TEST ADD DATA INTO OBJECT
    @Test
    public void testAddData3() throws Exception {


        monObjets.add(new MonObjet("robert", "24/10", "Lyon"));


        // ADD DATA FROM MONOBJECT INTO ARTICLESTEST OBJECT
        articlesTest = new ArticlesTest(monObjets);

        monObjets2.add(new MonObjet(articlesTest.getMesarticlesTest().get(0).getTitle(),
                articlesTest.getMesarticlesTest().get(0).getDate(),
                articlesTest.getMesarticlesTest().get(0).getSection()));


        // TEST METHOD IF TITLE MONOBJECTS EQUAL TITLE MONOBJECTS2
        assertEquals(articlesTest.getMesarticlesTest().get(0).getSection(), monObjets.get(0).getSection());

        // TEST WITH MOCKITO
        assertEquals("Lyon", monObjets.get(0).getSection());

    }


    // TEST ADD DATA INTO OBJECT
    @Test
    public void testAddData4() throws Exception {

        monObjets.add(new MonObjet("robert", "24/10", "Lyon"));


        // ADD DATA FROM MONOBJECT INTO ARTICLESTEST OBJECT
        articlesTest = new ArticlesTest(monObjets);

        monObjets2.add(new MonObjet(articlesTest.getMesarticlesTest().get(0).getTitle(),
                articlesTest.getMesarticlesTest().get(0).getDate(),
                articlesTest.getMesarticlesTest().get(0).getSection()));


        // TEST METHOD IF TITLE MONOBJECTS EQUAL TITLE MONOBJECTS2
        assertEquals(articlesTest.getMesarticlesTest().get(0).getSection(), monObjets.get(0).getSection());
    }


    // TEST ADD DATA INTO OBJECT
    @Test
    public void testAddData5() throws Exception {

        monObjets.add(new MonObjet("robert", "24/10", "Lyon"));
        // ADD NEW LINE
        monObjets.add(new MonObjet("nathalie", "24/10", "Druillat"));


        articlesTest = new ArticlesTest(monObjets);

        // USE IF FOR ADD DIFFERENTS LINE IN OBJECT
        // TAKE THE SIZE FOR DEFINE i
        for (int i = 0; i < articlesTest.getMesarticlesTest().size(); i++) {

            monObjets2.add(new MonObjet(articlesTest.getMesarticlesTest().get(i).getTitle(),
                    articlesTest.getMesarticlesTest().get(i).getDate(),
                    articlesTest.getMesarticlesTest().get(i).getSection()));
        }


        // TEST METHOD IF TITLE MONOBJECTS EQUAL TITLE MONOBJECTS2 (SECOND FLOOR)
        assertEquals("Druillat", monObjets.get(1).getSection());
    }


    // TEST ADD DATA INTO OBJECT
    @Test
    public void testAddData6() throws Exception {

        monObjets.add(new MonObjet("robert", "4/10", "Lyon"));
        monObjets.add(new MonObjet("nathalie", "2/11", "Druillat"));

        // ADD NEW LINE WITH IMAGE
        monObjets.add(new MonObjet("pierre", "24/03", "Marseille", "image1"));


        articlesTest = new ArticlesTest(monObjets);

        for (int i = 0; i < articlesTest.getMesarticlesTest().size(); i++) {

            // ADD IF FOR AKS IF THERE ARE IMAGE AND SAVE WHITOUT IMAGE / WITH IMAGE
            if (articlesTest.getMesarticlesTest().get(i).getImage() == null) {
                monObjets2.add(new MonObjet(articlesTest.getMesarticlesTest().get(i).getTitle(),
                        articlesTest.getMesarticlesTest().get(i).getDate(),
                        articlesTest.getMesarticlesTest().get(i).getSection()));

            }
            // ADD LINE FOR SAVE IMAGE
            monObjets2.add(new MonObjet(articlesTest.getMesarticlesTest().get(i).getTitle(),
                    articlesTest.getMesarticlesTest().get(i).getDate(),
                    articlesTest.getMesarticlesTest().get(i).getSection(),
                    articlesTest.getMesarticlesTest().get(i).getImage()));

        }

        // TEST METHOD image1
        assertEquals("image1", monObjets.get(2).getImage());
    }

}
















































