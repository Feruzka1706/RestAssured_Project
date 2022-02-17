package com.cydeo.tests.day07;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;



public class ZipCodeAPI_Test {

    @BeforeAll
    public static void setup() {
        baseURI = "https://api.zippopotam.us";
        basePath = "/us";
    }

    @AfterAll
    public static void teardown() {
        reset();
    }


    @Test
    public void testZipToCity(){
         //https://api.zippopotam.us/us/95843
        //log your request, provide 95843 as path variable|parameter
        //send get request and assert status is 200
        //assert response is json response
        // assert "country": "United States",
        // assert "state": "California"
        //assert "postal code": "95843"
        //assert that "place name": "Antelope"

        given()
                .log().all()
                .pathParam("zip",95843)
                .contentType(ContentType.JSON)
                .when()
                .get("/{zip}")
                .then()
                .log().all()
                .statusCode( is(200) )
                .contentType(ContentType.JSON)
                .body("country",is(containsStringIgnoringCase("United States")))
                .body("places[0].state",is("California"))
                .body("'post code'", equalTo("95843"))
                .body("places[0].'place name' ",equalTo("Antelope"));


    }

    /**
     * Create a Parametrized Test to check
     * using given zipcodes : 95843, 22030 , 10019 , 12345
     * send out request to
     * GET https://api.zippopotam.us/us/{zip}
     * test the status code is 200
     *
     * In @Parameterized test using @ValueSource
     * you can only provide one data per iteration to the test.
     * and sometimes we want to externilize the data we used to external file.
     *
     */

    @ParameterizedTest
    //@ValueSource(ints = {95843, 22030 , 10019 ,12345,10000, 19152})
    @CsvFileSource(resources = "/zipcodes.csv")
    public void testZipToCityDDT(int zipParam){

        given().log().all()
                .pathParam("zip",zipParam)
                .when()
                .get("/{zip}")
                .then().statusCode(200);
    }


    /**
     * Create a file called state_city.csv under resources folder
     * it has two columns state, city
     * add same valid data
     *
     *  send request to GET https://api.zippopotam.us/us/{state}/{city}
     *           *   log request uri
     *           *   use state and city as path variables with name state / city
     *           *   for actual value of path params get it from csv file
     *           *   send get request verify
     *           *   status code 200 , json format
     *
     */




}
