package com.cydeo.tests.practice;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class BookIT_Authorization {

    @BeforeAll
    public static void setUp(){
        baseURI="https://cybertek-reservation-api-qa3.herokuapp.com";
        //basePath="/api";

    }

    @AfterAll
    public static void teardown(){
        reset();
    }


    @Test
    public void test1(){

        String accessToken="Bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJqdGkiOiIxNDAiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0." +
                "xNvdQRyrYMb3Ec5QByHwXowBo3zPK2jQQS1eJ2RYIto";
        given()
                .header("Authorization",accessToken)
                .when().get("/api/users/me")
                .then()
                .log().all()
                .statusCode(200)
                ;

    }



}
