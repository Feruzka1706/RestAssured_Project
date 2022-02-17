package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanTestBase {
    @BeforeAll
    public static void setUp(){
        baseURI="http://100.26.44.167:8000";
        basePath="/api";

    }

    @AfterAll
    public static void teardown(){
        reset();
    }


}
