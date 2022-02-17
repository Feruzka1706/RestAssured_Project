package com.cydeo.tests.office_Hours.day02_Practice;

import com.cydeo.utility.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class P3_JsonPath extends HRTestBase {

/*
    Given
             accept type is application/json
     When
             user sends get request to /locaitons
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK

  */

    @Test
     public void test1(){

        JsonPath jsonPath=  given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/locations")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        //jsonPath.prettyPeek();


      String secondCity=jsonPath.getString("items[1].city");
        //System.out.println("secondCity = " + secondCity);

        String lastCity=jsonPath.getString("items.city[-1]");
        //System.out.println("lastCity = " + lastCity);

        List<String> allIds= jsonPath.getList("items.country_id");
        System.out.println("allIds = " + allIds);

        System.out.println("======================================");

        //get all city where their country id is UK
      List<String> cityNamesWithUK=jsonPath.getList("items.findAll {it.country_id=='UK'}.city");
        System.out.println("cityNamesWithUK = " + cityNamesWithUK);


    }


    @Test
    public void getEmployees(){


        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .when()
                .get("/employees").prettyPeek();


        JsonPath jsonPath = response.jsonPath();

        //get me all employees first_name who is making salary more than 15000
      List<String> allNamesWithSalaryHigh= jsonPath.getList("items.findAll {it.salary>=15000}.first_name");
        System.out.println("allNamesWithSalaryHigh = " + allNamesWithSalaryHigh);

        /**
         * Get all /countries
         *
         * get all country names where region id 2
         */


    }


}
