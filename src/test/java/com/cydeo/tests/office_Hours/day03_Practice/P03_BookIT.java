package com.cydeo.tests.office_Hours.day03_Practice;

import com.cydeo.pojo.Campus;
import com.cydeo.utility.BookIT_TestBase;
import com.cydeo.utility.BookIT_Util;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class P03_BookIT extends BookIT_TestBase {
/*
    3.get("/api/campuses")

    credentials:
        email : sbirdbj@fc2.com
        password : asenorval
    baseUri: "https://cybertek-reservation-api-qa3.herokuapp.com/"
    doc: https://cybertek-reservation-api-docs.herokuapp.com/#get-all-rooms

        Task 1 :  Deserialization Exercise
                send a request to get all campuses
                verify status code 200
                Create an POJO to get all data
                        //Find out how many room  we have in light-side
                        //Find out how many cluster we have in VA
                        //Find out how many campus we have
     */

    @Test
    public void task1(){

       String accessToken= BookIT_Util.getToken();
        System.out.println("accessToken = " + accessToken);

        List<Campus> campusList=given()
                .log().uri()
                .header("Authorization",accessToken)
        .when()
                .get("/api/campuses")
         .then()
                .statusCode(200)
                .extract().jsonPath().getList("", Campus.class);

        System.out.println("campusList = " + campusList);

        System.out.println("campusList.size() = " + campusList.size());

        System.out.println("campusList.get(0).getClusterList().size() = " + campusList.get(0).getClusterList().size());

        System.out.println("campusList.get(0).getClusterList().get(0).getRooms().size() = " + campusList.get(0).getClusterList().get(0).getRooms().size());


    }
}
