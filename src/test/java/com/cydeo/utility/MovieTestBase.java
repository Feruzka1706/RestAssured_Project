package com.cydeo.utility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;



public class MovieTestBase {


    @BeforeAll
    public static void setUp(){
        baseURI="http://www.omdbapi.com";

    }


    @AfterAll
    public static void tearDown(){
        reset();
    }


}
