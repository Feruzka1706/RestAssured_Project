package com.cydeo.tests.day01;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;

@Tag("smokeAll")
public class TestSpartanAPI {

    @Test
    public void testHello(){
        System.out.println("Hello World");
        //send the request to below request url and save the response
        //GET request http://52.87.222.1:8000/api/hello
        //get() method is coming from RestAssured class to send get request to the url defined
        //the result of sending request can be stored in Response object coming from restAssured
       Response response = get("http://100.26.44.167:8000/api/hello");
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        System.out.println("response.getBody() = " + response.getBody());
        System.out.println("response.prettyPrint() = " + response.prettyPrint());

        Assertions.assertEquals(200,response.statusCode());



    }



}
