package com.cydeo.tests.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class TestSpartan3 {


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI ="http://52.87.222.1:8000";
        RestAssured.basePath="/api";

    }

    @AfterAll
    public static void cleanUp(){
        reset();
    }

    @Test
    public void getAllSpartansTest(){
        // Send request to Get /spartans and check status code 200 and content type is json
        Response response=get("/spartans");

       // response.prettyPrint();//print the payload(body) and return it as string at the same time
        Assertions.assertEquals(200,response.getStatusCode());
        Assertions.assertEquals(ContentType.JSON.toString(),response.getContentType() );

        System.out.println("response.path(\"[0].gender\") = " + response.path("[0].gender"));

        System.out.println("response.path(\"gender[0]\") = " + response.path("gender[0]"));
        //System.out.println("response.path(\"id\") = " + response.path("id"));

        List<Integer> list=response.path("id");
        System.out.println("list = " + list);

        System.out.println(""+response.path("name[3]"));


    }

    // Send request to Get /spartans and provide accept header is application xml
    //and check status code 200 and content type application xml
    @Test
    public void testGetXMLResponse(){
        //RestAssured use method chaining extensively to combine all part of requests
        //and verify the response in one shot
        //here since we need to provide additional header information to get xml response
        //we wil start learning some method chaining to see
        //how we can provide additional information to the request
        /*
        Response response= given()
                                .header("Accept","application/xml")
                          .when()
                                .get("/spartans");

         */

       /* Response response= given()
                .header("Accept",ContentType.XML)
                .when()
                .get("/spartans");

        */

        Response response= given()
                //.header("Accept",ContentType.XML)
                //.header("Accept","application/xml")
                .accept(ContentType.XML)//special support method for accept header
                .when()
                .get("/spartans");

        response.prettyPrint();

        //write assertions
        Assertions.assertEquals(200,response.statusCode() );
        Assertions.assertEquals(ContentType.XML.toString(),response.contentType() );

    }

    //SEND REQUEST TO GET http://54.236.150.168:8000/api/spartans/search?nameContains=Ea&gender=Male
    @Test
    public void testSearch(){
        Response response=given()
                           // .accept(ContentType.JSON) //it's already JSOn file so no need to provide again header type
                           .queryParam("nameContains","B")
                           .queryParam("gender","Male")
                           .when()
                           .get("/spartans/search");

         response.prettyPrint();

        System.out.println("response.path(\"totalElement\") = " + response.path("totalElement"));

        System.out.println("response.path(\"content[0].name\") = " + response.path("content[0].name"));
        System.out.println("response.path(\"content.name[0]\") = " + response.path("content.name[0]"));


        System.out.println("response.path(\"content[1].id\") = " + response.path("content[1].id"));
        System.out.println("response.path(\"content.id[1]\") = " + response.path("content.id[1]"));

        List<String> allNames=response.path("content.name");
        System.out.println("allNames = " + allNames);

        System.out.println("response.body().path(\"content.name[1]\") = " + response.body().path("content.name[1]"));
    }


    @Test
    public void testOneSpartanPathParam(){

        Response response=given()
                .pathParam("id",4)
                .log().all()//this will log everything about the request
                .when()
                .get("/spartans/{id}");

        response.prettyPrint();

    }



}
