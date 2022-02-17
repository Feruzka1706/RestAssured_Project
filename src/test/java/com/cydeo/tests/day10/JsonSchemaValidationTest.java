package com.cydeo.tests.day10;

import com.cydeo.pojo.Spartan;
import com.cydeo.tests.practice.SpartanPOJO;
import com.cydeo.utility.SpartanTestBase;
import com.cydeo.utility.SpartanUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class JsonSchemaValidationTest extends SpartanTestBase {


    @DisplayName("Testing GET /spartans/{id} response schema")
    @Test
    public void testSingleSpartanSchema(){

        /**
         * Send GET request to /spartans/{id}
         * and verify the response json match the schema document under resources folder
         * with the name of SingleSpartanSchema.json
         */
        given()
                .log().uri()
                .pathParam("id", SpartanUtil.getRandomId())
                .when()
                .get("/spartans/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body( matchesJsonSchemaInClasspath("SingleSpartanSchema.json") ) ;


    }


    @DisplayName("Testing POST /spartans response schema")
    @Test
    public void testingSingleSpartanWithPOSTMethod(){
        /**
         * Send POST request to /spartans
         * and verify the response json match the schema document under resources folder
         * with the name of PostSpartanSchema.json
         */

        Spartan spartan1=new Spartan("Akbar","Male",273923017283L);

        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .body(spartan1)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .body( matchesJsonSchemaInClasspath("PostSpartanSchema.json") )
                .body("data.name",containsString("Akbar"));

    }


    @DisplayName("Testing Search Spartan response schema")
    @Test
    public void testSearchSpartanMethod(){

        /**
         * Send GET request to /spartans/search with queryParam
         * and verify the response json match the schema document under resources folder
         * with the name of SearchSpartanSchema.json
         */

        given()
                .log().uri()
                .queryParam("nameContains","Kuku")
                .queryParam("gender","Male")
                .when()
                .get("/spartans/search")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("SearchSpartanSchema.json"));

    }



    @DisplayName("Testing All Spartan response schema")
    @Test
    public void testAllSpartanSchema(){

        /**
         * Send GET request to /spartans
         * and verify the response json match the schema document under resources folder
         * with the name of AllSpartanSchema.json
         */


        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .when()
                .get("/spartans")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("AllSpartanSchema.json"));
    }



}
