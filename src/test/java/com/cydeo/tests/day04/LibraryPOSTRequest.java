package com.cydeo.tests.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class LibraryPOSTRequest {

    @BeforeAll
    public static void setUp(){

       baseURI="https://library2.cybertekschool.com";
       basePath="/rest/v1";
    }

    @AfterAll
    public static void tearDown(){
        reset();

    }



    @Test
    public void testLogin(){
        /**
         * This is the link for doc : https://library2.cybertekschool.com/rest/v1/#/User/post_login
         *
         * POST /login
         * content type : application/x-www-form-urlencoded
         * body :
         *   email      : librarian52@library
         *   password   : Sdet2022*
         *
         * According to the doc
         *  we get 200 status code
         *  json body with 2 key  : token  , redirect_url
         *  content-type json
         */
        //String contentType="application/x-www-form-urlencoded";

        given()
                .log().all()
                //header("Content-Type" , "application/x-www-form-urlencoded
                .contentType(ContentType.URLENC)//this line is simple version to provide form body
                .formParam("email","librarian52@library")
                .formParam("password","Sdet2022*")
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(200);

        //in this POST request we saw different content type known as url encoded form data
        //if content type is this, rest assured make it easy to provide the body
        //using the method called form param, if you have more than one you can keep calling the method
        //and provide the key value pair according to the document


    }

    @Test
    public void testLogin2(){

        Response response=given()
                .log().all()
                .contentType(ContentType.URLENC)//this line is simple version to provide form body
                .formParam("email","librarian52@library")
                .formParam("password","Sdet2022*")
                .when()
                .post("/login");


       String token =response.path("token");

       given()
               .log().all()
               .header("x-library-token",token)
               .when()
               .get("/dashboard_stats")
               .then().log().all()
               .statusCode(200);



    }



}
