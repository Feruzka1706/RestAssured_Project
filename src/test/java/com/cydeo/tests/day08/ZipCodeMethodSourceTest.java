package com.cydeo.tests.day08;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static io.restassured.RestAssured.*;


public class ZipCodeMethodSourceTest {

    @BeforeAll
    public static void setup() {
        baseURI = "https://api.zippopotam.us/";
        basePath = "/us";
    }

    @AfterAll
    public static void teardown() {
        reset();
    }

     /**
     *     Write a parametrized Test to test each and every zipcode in Fairfax VA
     *     using GET https://api.zippopotam.us/us/{zip}
     *     use  @MethodSource to use the method you created above as a source
     */

     //we have option to customize the display name of parametrized test
     //using name= "something" and you have option to use the actual argument
     //using {the location index of argument} so for first arg it will be {0}
     //for second it will be {1} and next {2} if you have more argument
    @ParameterizedTest(name = "Getting one zipcode {0}")//{0} here means first argument
    @MethodSource("getAllZipCodes")
    public void testAllZipCode(String zipCode){

        System.out.println("zipCode = " + zipCode);
        given().log().uri()
                .pathParam("zip",zipCode)
         .when()
                .get("/{zip}")
         .then()
                .statusCode(200);
    }



/**
 * Write a static method that return all zipcodes for Fairfax VA
 * using https://api.zippopotam.us/us/fairfax
 * and return List<String>
 */
    public static List<String> getAllZipCodes(){

        //Send request GET https://api.zippopotam.us/us/va/fairfax and store all zipcodes
        //since this is not a @Test method, @BeforeAll will not affect this method
        //and baseURI and basePATH will not be set, so we need to do it ourselves
        //one way to do it is to provide full url directly
        //or we have use .baseURI("base uri here")  .basePath("base path here")
        //in given section for one time

        List<String> allZipCodes=get("https://api.zippopotam.us/us/va/fairfax")
                .path("places.'post code'");

        return allZipCodes;
    }



}
