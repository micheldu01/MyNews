package com.example.michel.mynews.FragmentsView;

import com.example.michel.mynews.API.ArticlesTest;
import com.example.michel.mynews.RecyclerView.MonObjet;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by michel on 04/04/2018.
 */
public class BusinessFragmentTest {

    // DECLARE VALUES
    private ArticlesTest articlesTest;
    private List<MonObjet> monObjets = new ArrayList<>();
    private List<MonObjet> monObjets2 = new ArrayList<>();




    // TEST IF TEST RUN
    @Test
    public void myTest() throws Exception {

        assertEquals(2,1+1);
    }



    @Test
    public void TtestMethodGetArticle() throws Exception {



        monObjets.add(new MonObjet("robert", "24/10", "Lyon", "image1"));
        monObjets.add(new MonObjet("nathalie", "24/10", "Druillat"));


        articlesTest = new ArticlesTest(monObjets);

        for (int i = 0; i < articlesTest.getMesarticlesTest().size(); i++) {

                if (articlesTest.getMesarticlesTest().get(i).getImage() == null) {

                monObjets2.add(new MonObjet(articlesTest.getMesarticlesTest().get(i).getTitle(),
                        articlesTest.getMesarticlesTest().get(i).getDate(),
                        articlesTest.getMesarticlesTest().get(i).getSection()));
            } else {

                monObjets2.add(new MonObjet(articlesTest.getMesarticlesTest().get(i).getTitle(),
                        articlesTest.getMesarticlesTest().get(i).getDate(),
                        articlesTest.getMesarticlesTest().get(i).getSection(),
                        articlesTest.getMesarticlesTest().get(i).getImage()));

            }
        }

        //Assert.assertEquals(articlesTest.getMesarticlesTest().get(0).getTitle(),monObjets2.get(0).getTitle());

    }

    @Test
    public void testObjectObject6() throws Exception {

        monObjets.add(new MonObjet("robert", "24/10", "Lyon", "image1"));
        monObjets.add(new MonObjet("nathalie", "24/10", "Druillat"));

        ArticlesTest articles = new ArticlesTest(monObjets);

        for (int i = 0; i < articles.getMesarticlesTest().size(); i++) {

            if (articles.getMesarticlesTest().get(i).getImage() == null) {

                monObjets2.add(new MonObjet(articles.getMesarticlesTest().get(i).getTitle(),
                        articles.getMesarticlesTest().get(i).getDate(),
                        articles.getMesarticlesTest().get(i).getSection()));
            } else {

                monObjets2.add(new MonObjet(articles.getMesarticlesTest().get(i).getTitle(),
                        articles.getMesarticlesTest().get(i).getDate(),
                        articles.getMesarticlesTest().get(i).getSection(),
                        articles.getMesarticlesTest().get(i).getImage()));

            }
        }

        assertEquals(articles.getMesarticlesTest().get(0).getTitle(),monObjets2.get(0).getTitle());

    }

    // TEST ADD DATA INTO OBJECT
    @Test
    public void testAddData() throws Exception {

        // ADD DATE
        monObjets.add(new MonObjet("robert", "24/10", "Lyon"));

        // TEST METHOD
        assertEquals("robert",monObjets.get(0).getTitle());
    }


    // TEST ADD DATA INTO OBJECT
    @Test
    public void testAddData2() throws Exception {

        monObjets.add(new MonObjet("robert", "24/10", "Lyon"));


        // ADD DATA FROM MONOBJECT INTO ARTICLESTEST OBJECT
        articlesTest= new ArticlesTest(monObjets);


        // TEST METHOD IF TITLE MONOBJECTS EQUAL TITLE MONOBJECTS2
        assertEquals(monObjets.get(0).getDate(),articlesTest.getMesarticlesTest().get(0).getDate());
    }

    // TEST ADD DATA INTO OBJECT
    @Test
    public void testAddData3() throws Exception {

        monObjets.add(new MonObjet("robert", "24/10", "Lyon"));


        // ADD DATA FROM MONOBJECT INTO ARTICLESTEST OBJECT
        articlesTest= new ArticlesTest(monObjets);

        monObjets2.add(new MonObjet(articlesTest.getMesarticlesTest().get(0).getTitle(),
                articlesTest.getMesarticlesTest().get(0).getDate(),
                articlesTest.getMesarticlesTest().get(0).getSection()));


        // TEST METHOD IF TITLE MONOBJECTS EQUAL TITLE MONOBJECTS2
        assertEquals(articlesTest.getMesarticlesTest().get(0).getSection(),monObjets.get(0).getSection());
    }

    // TEST ADD DATA INTO OBJECT
    @Test
    public void testAddData4() throws Exception {

        monObjets.add(new MonObjet("robert", "24/10", "Lyon"));


        // ADD DATA FROM MONOBJECT INTO ARTICLESTEST OBJECT
        articlesTest= new ArticlesTest(monObjets);

        monObjets2.add(new MonObjet(articlesTest.getMesarticlesTest().get(0).getTitle(),
                articlesTest.getMesarticlesTest().get(0).getDate(),
                articlesTest.getMesarticlesTest().get(0).getSection()));


        // TEST METHOD IF TITLE MONOBJECTS EQUAL TITLE MONOBJECTS2
        assertEquals(articlesTest.getMesarticlesTest().get(0).getSection(),monObjets.get(0).getSection());
    }

    // TEST ADD DATA INTO OBJECT
    @Test
    public void testAddData5() throws Exception {

        monObjets.add(new MonObjet("robert", "24/10", "Lyon"));
        // ADD NEW LINE
        monObjets.add(new MonObjet("nathalie", "24/10","Druillat"));


        articlesTest= new ArticlesTest(monObjets);

        // USE IF FOR ADD DIFFERENTS LINE IN OBJECT


        monObjets2.add(new MonObjet(articlesTest.getMesarticlesTest().get(0).getTitle(),
                articlesTest.getMesarticlesTest().get(0).getDate(),
                articlesTest.getMesarticlesTest().get(0).getSection()));


        // TEST METHOD IF TITLE MONOBJECTS EQUAL TITLE MONOBJECTS2
        assertEquals(articlesTest.getMesarticlesTest().get(0).getSection(),monObjets.get(0).getSection());
    }

}
















































