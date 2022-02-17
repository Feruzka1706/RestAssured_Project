package com.cydeo.tests.day06;

import com.cydeo.pojo.Article2;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class NewsAPI_Deserialization_CoolTest {



    @BeforeAll
    public static void setUp(){
        baseURI="https://newsapi.org/";
        basePath="/v2";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }


    /**
     * GET https://newsapi.org/v2/top-headlines?country=us
     * Header :  Authorization = Bearer e0188dafa3fb4698accd00fec60b2a95
     */

    @Test
    public void test(){

        String header="Authorization";
        String myToken="Bearer e0188dafa3fb4698accd00fec60b2a95";

        JsonPath jsonPath=given()
                .log().all()
                .queryParam("country","us")
                .header(header,myToken)
                .when()
                .get("/top-headlines").jsonPath();

        //save first article json into Article2 Pojo
        Article2 article2=jsonPath.getObject("articles[0]",Article2.class);
        //System.out.println("article2 = " + article2);

      //  System.out.println("article2.getSource().getId() = " + article2.getSource().getId());

        //find out all article if source id is not null

        List<Article2> allArticles = jsonPath.getList("articles",Article2.class);

        for (Article2 eachArticle : allArticles) {

           if(eachArticle.getSource().getId()!=null){
               //System.out.println("eachArticle.getSource().getId() = " + eachArticle.getSource().getId());
               System.out.println("eachArticle.getAuthor() = " + eachArticle.getAuthor());
           }

        }


    }



}
