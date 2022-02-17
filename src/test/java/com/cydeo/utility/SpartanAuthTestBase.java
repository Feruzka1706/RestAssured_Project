package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 * This class will serve as Base Class to set up BaseURI and BasePath
 * and clean up after all test for any Spartan related test classes
 */

import static io.restassured.RestAssured.*;

public class SpartanAuthTestBase {
    @BeforeAll
    public static void setUp(){
        baseURI="http://52.87.222.1:7000";
        basePath="/api";

    }

    @AfterAll
    public static void teardown(){
        reset();
    }


}
