package com.cydeo.tests.office_Hours.day01_Practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

;

public class HRTest {

    @Test
    public void simpleGetRequest(){
      //http://52.87.222.1:1000/ords/hr/regions
        Response response= RestAssured.get("http://52.87.222.1:1000/ords/hr/regions");

        System.out.println("response.headers() = " + response.headers());

        System.out.println("===========================================");
        response.prettyPrint();
        System.out.println("===========================================");

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println(response.header("Date"));

        //verify data exist
        System.out.println(response.headers().hasHeaderWithName("Date"));

    }


}
