package com.cydeo.tests.practice.shortcutVideos;


import static io.restassured.RestAssured.*;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PathParameter extends SpartanTestBase {


    @Test
    public void test1(){

        Response response=given()
                .contentType(ContentType.JSON)
                .log().all()
                .and()
                .pathParam("id", 18)
                .when()
                .get("/spartans/{id}");

        response.prettyPrint();

        //verify content type is json
       assertEquals(ContentType.JSON.toString(),response.getContentType());

       //verify status code is 200
       assertEquals(200,response.statusCode());

       //verify response payload contains "Allen" name
       assertTrue(response.body().asString().contains("Allen"));






    }


    @Test
    public void negativePathParam(){

        Response response=given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id",200) //because we don't have 200 id inside database
                .when()
                .log().all()
                .get("/spartans/{id}");

        response.prettyPrint();

        assertEquals(404,response.statusCode());
        assertEquals(ContentType.JSON.toString(),response.contentType());


    }



}
