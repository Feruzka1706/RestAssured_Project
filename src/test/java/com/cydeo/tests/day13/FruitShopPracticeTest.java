package com.cydeo.tests.day13;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class FruitShopPracticeTest {

    /**
     * FruitShop Exercise:
     *
     * 1.Get all customers
     *  -verify you get 200
     *  -then extract first customer id
     *
     * 2.Get All the orders of that customer
     *  -verify status code is 200
     *  -extract first order id
     *
     * 2.Create a customer
     */

    @BeforeAll
    public static void setup(){
        baseURI = "https://api.predic8.de:443/shop";

    }

    @AfterAll
    public static void teardown(){
        reset();
    }

    @Test
    public void testExtractPractice(){
        //Send the request GET /customers and verify 200 then get the id

           Response response= get("/customers/");
           assertEquals(200,response.statusCode());

           String firstCustomerUrl=response.path("customers.customer_url[0]");
        System.out.println("firstCustomerUrl = " + firstCustomerUrl);

        //Can we verify in method chain then extract the data after verification in method chain
        //once you get to then section, it will return ValidatableResponse Object
        //we can use extract() method after that to continue to extract data out of if needed

        //do it in one chain
       String firstUrl = given()
                .log().uri()
                .get("/customers/")
         .then()
                .statusCode(200)
                .extract() //from here it will stop verifying and will extract response data
                .response() //this will return response Object
                .path("customers.customer_url[0]");

        System.out.println("firstUrl = " + firstUrl);


    }


    @Test
    public void testBreakingTheMethodChain(){
        given()
                 .log().uri()
                 .contentType(ContentType.JSON)
        .when()
                 .get("/customers/")
        .then()
                 .statusCode(200);

        //if we break the chain and just store given part of the chain in the variable
        //it will be RequestSpecification object because everything inside given section
        //return RequestSpecification
        RequestSpecification givenPart=given().log().uri().contentType(ContentType.JSON);

        //now lets try to break up the when section followed by givenPart
        Response response=givenPart.when().get("/customers/");

        //now let's try to get the then part of the chain
       ValidatableResponse thenPart = response.then().statusCode(200);

       //now let's verify content Type is json for the response
        thenPart.contentType(ContentType.JSON);




    }


}
