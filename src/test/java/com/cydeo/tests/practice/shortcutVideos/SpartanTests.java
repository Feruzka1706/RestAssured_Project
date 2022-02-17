package com.cydeo.tests.practice.shortcutVideos;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class SpartanTests extends SpartanTestBase {


    @Test
    public void test1(){

        Response response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .get("/spartans");


        //print the status code, make sure it's 200
        System.out.println("response.statusCode() = " + response.statusCode());

        //print body()
        response.prettyPrint();


    }

    @Test
    public void test2(){

        /**
         * When user send GET request to /api/spartans end point
         * Then status code must be 200
         * And body should contains Allen
         */

        Response response=given().contentType(ContentType.JSON)
                .log().all()
                .get("/spartans");


        int statusCode=response.statusCode();
        //Then status code must be 200
        assertEquals(200,statusCode);

        //And body should contain Allen
        assertTrue(response.body().asString().contains("Allen"));

        assertEquals(ContentType.JSON.toString(),response.contentType());



    }


}
