package com.cydeo.tests.day06;

import static io.restassured.RestAssured.*;

import com.cydeo.pojo.Article;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NewsAPI_Deserialization {



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


        //try to get first Article into POJO
        Article article1=jsonPath.getObject("articles[0]",Article.class);
        System.out.println("article1 = " + article1);
      //  String name=jsonPath.getString("articles[0].author");
       // System.out.println("article1 = " + article1);

        //check if the source id is not null or not
        //the source id is inside the source Map field
        //we can use getter to private field to get the map
        //the using this map use map get method to get the value of the key
        System.out.println(article1.getSource().get("id"));


        List<Article> allArticles=jsonPath.getList("articles",Article.class);

        for (Article eachArticle : allArticles) {
            if(eachArticle.getSource().get("id")!=null){
                System.out.println("eachArticle.getAuthor() = " + eachArticle.getAuthor());
            }

        }

        //Article article3=new Article();

    }



}
