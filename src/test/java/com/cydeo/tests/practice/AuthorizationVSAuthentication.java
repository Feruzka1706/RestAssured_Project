package com.cydeo.tests.practice;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthorizationVSAuthentication {

    @BeforeAll
    public static void setUp(){
        baseURI="https://cybertek-reservation-api-qa3.herokuapp.com";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }


    @Test
    public void test1(){

        String myToken="Bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ." +
                "lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";
      given()
                .header("Authorization",myToken)
                .when()
                .get("/api/campuses")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().all()
                .and()
              .body("clusters[0].name[0]",equalToIgnoringCase("light-side"),
                      "location[1]",is(equalToIgnoringCase("IL")));




    }

}
