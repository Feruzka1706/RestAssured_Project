package com.cydeo.tests.day12;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;



public class MockAPIEndPointTest {

    @BeforeAll
    public static void setUp(){
       baseURI="https://93653bd9-25d3-4116-b92c-0acb51afb724.mock.pstmn.io";
       //we did not add anything after baseURI in mock api endpoint so we do not have basepath here
    }


    @AfterAll
    public static void tearDown(){
     reset();
    }



    @Test
    public void testNemo(){
        //get("/nemo").prettyPeek();

        given()
                .log().uri()
           .when()
                .get("/nemo")
          .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("type",is("B23 let's have a lunch"));


    }


    /**
     * When sending larger amount of data to the server it's common to
     * use the multipart form data technique. Rest Assured provide methods
     * called multiPart that allows you to specify a file, byte-array, input stream
     *  or text to upload
     */
    @Test
    public void testMultiPartFormDataFileUpload(){

        given()
                .log().all()
                .contentType(ContentType.MULTIPART)
                .multiPart(new File("src/test/java/com/cydeo/tests/day12/Java_Image.png") )
          .when()
                .post("/upload")
           .then()
                .log().body()
                .statusCode(201);

    }


}
