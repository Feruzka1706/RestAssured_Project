package com.cydeo.tests.practice;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public class JsonSchemaValidation {

    @BeforeAll
    public static void setUp(){
        baseURI="http://52.87.222.1:8000";
    }

    @AfterAll
    public static void teardown(){
        reset();
    }

    @Test
    public void test1(){

    }

}
