package com.cydeo.utility;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;


public class BookIT_TestBase {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "https://cybertek-reservation-api-qa3.herokuapp.com/";

    }

    @AfterAll
    public static void teardown(){
        reset();
    }
}
