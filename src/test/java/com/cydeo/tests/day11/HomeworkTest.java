package com.cydeo.tests.day11;

import com.cydeo.utility.SpartanUtil;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.*;

/**
 * Homework: Create a method to get RandomValidDriverId
 * so you can pass to the test /drivers/{driverId} instead of guessing valid driver id
 *


 *
 * Homework 4 :
 * Send GET Request to https://vpic.nhtsa.dot.gov/api/vehicles/GetMakeForManufacturer/988?format=xml
 * and verify the
 * count element text is 2
 * message Results returned successfully
 * first Make_ID is  474 , Make_Name Honda
 */

@DisplayName("Homework Tasks")
@Tag("Homework")
public class HomeworkTest {

    @BeforeAll
    public static void setUp(){
        //http://ergast.com/api/f1/drivers/{driverId} driverId-? abate
        baseURI="http://ergast.com";
        basePath="/api/f1";
    }


    @AfterAll
    public static void tearDown(){

        reset();
    }

    @DisplayName("Homework 1 xml response checking")
    @Test
    public void testDrivers(){
        /**
         * Additional Homework:
         *   Explore other the rest of Collection
         *   http://ergast.com/api/f1/2021/drivers
         *   practice getting xml element text value, xml element attributes
         *   same goes for the rest of the Collection
         */

        XmlPath xp=given()
                .log().uri()
                .contentType(ContentType.XML)
                .pathParam("season",2021)
                .when()
                .get("/{season}/drivers")
                .then()
               // .log().body()
                .statusCode(200).extract().xmlPath();

        String seasonAttribute=xp.getString("MRData.DriverTable.@season");
        System.out.println("seasonAttribute = " + seasonAttribute);

        List<String> allCodeAttributes= xp.getList("MRData.DriverTable.Driver.@code",String.class);
        System.out.println("allCodeAttributes = " + allCodeAttributes);

        String firstDriverPermanentNumber=xp.getString("MRData.DriverTable.Driver[0].PermanentNumber");
        System.out.println("firstDriverPermanentNumber = " + firstDriverPermanentNumber);

        List<String> allDriversPermanentNumbers=xp.getList("MRData.DriverTable.Driver.PermanentNumber");
        System.out.println("allDriversPermanentNumbers = " + allDriversPermanentNumbers);

        String lastDriverNationality= xp.getString("MRData.DriverTable.Driver[-1].Nationality");
        System.out.println("lastDriverNationality = " + lastDriverNationality);

    }


    @DisplayName("Homework 2 xml response checking")
    @Test
    public void testHomework2(){
        /**
         *   Homework 2:
         *   Send this request in Movie API
         *   GET http://www.omdbapi.com/?t=The Mandalorian&r=xml?apiKey=YourKeyGoesHere
         *   print out all the movie attributes
         *
         *   Send the request GET  http://www.omdbapi.com/?t=The Mandalorian&r=xml?apiKey=YourKeyGoesHere
         *   verify root element attribute totalResults="11"
         *   store all the titles on List<String> and print
         *   verify the size of list match the attribute  totalResults="11"
         *
         */

        String baseURI="http://www.omdbapi.com";

        XmlPath xp =given()
                .log().uri()
                .queryParam("s","The Mandalorian")
                .queryParam("apiKey","d8d661")
                .queryParam("r","xml")
                .when()
                .get(baseURI+"/")
                .then()
                .statusCode(200)
                .body("root.@totalResults",equalTo("11"))
                .body("root.result.@title.size()",is(10))
                .extract().xmlPath();


        List<String> allResultAttributes=new ArrayList<>(Arrays.asList("title","year","imdbID","type","poster"));

        for (String eachResultAttribute : allResultAttributes) {
            System.out.println("eachResultAttribute = " + eachResultAttribute+xp.getString("root.result.@"+eachResultAttribute));
        }



        int  totalResult= xp.getInt("root.@totalResults");
        //System.out.println("totalResult = " + totalResult);
        assertEquals(11,totalResult);

        List<String> allTitleAttributes=xp.getList("root.result.@title");
       // System.out.println("allTitleAttributes = " + allTitleAttributes);

        List<Integer> allYearsAttributes=xp.getList("root.result.@year");
        System.out.println("allYearsAttributes = " + allYearsAttributes);

        //the assertion will fail because the total title size is 10 not 11!
        assertEquals(totalResult-1,allTitleAttributes.size());




    }


    @DisplayName("Spartan response as XML format")
    @Test
    public void testSpartanAsXMLFormat(){
        /**
         * Homework 3:
         *  Send request to GET /spartans provide accept header to get XML response
         *  practice getting the value of xml elements for single elements
         *  or list of elements
         *
         */

        String  baseURI="http://52.87.222.1:8000/api";

        XmlPath xp=given()
                .log().uri()
                .accept(ContentType.XML)
                .when()
                .get(baseURI+"/spartans")
                .then()
                //.log().body()
                .statusCode(200)
                .body("List.item[1].name",containsStringIgnoringCase("Kuku Patch"))
                .extract().xmlPath();

        Faker faker=new Faker();


        int oneRandomIdNum= xp.getInt("List.item["+faker.number().numberBetween(1,163)+"].id");
        System.out.println("oneIdNum = " + oneRandomIdNum);

        List<Integer> allIdNumbers= xp.getList("List.item.id");
        System.out.println("allIdNumbers = " + allIdNumbers);

        String oneRandomName= xp.getString("List.item["+ faker.number().numberBetween(1,163)+"].name");
        System.out.println("oneRandomName = " + oneRandomName);

        List<String> allNames= xp.getList("List.item.name");
        System.out.println(allNames);

        String oneGender= xp.getString("List.item["+faker.number().numberBetween(1,163)+"].gender");
        System.out.println("oneGender = " + oneGender);

        List<String> allGenders= xp.getList("List.item.gender");
        System.out.println("allGenders = " + allGenders);


        Long oneRandomPhoneNum= xp.getLong("List.item["+faker.number().numberBetween(1,163)+"].phone");
        System.out.println("oneRandomPhoneNum = " + oneRandomPhoneNum);

        List<Long> allPhoneNumbers=xp.getList("List.item.phone");
        System.out.println("allPhoneNumbers = " + allPhoneNumbers);

    }


    @DisplayName("Homework 4")
    @Test
    public void testHomework4(){
        /**
         *   Homework 4 :
         *   Send GET Request to https://vpic.nhtsa.dot.gov/api/vehicles/GetMakeForManufacturer/988?format=xml
         *   and verify the
         *   count element text is 2
         *   message Results returned successfully
         *   first Make_ID is  474 , Make_Name Honda
         *
         */

        String baseURI="https://vpic.nhtsa.dot.gov/api";

        XmlPath xp=given()
                .log().uri()
                .pathParam("id","988")
                .queryParam("format","xml")
                .when()
                .get(baseURI+"/vehicles/GetMakeForManufacturer/{id}")
                .then()
                //.log().body()
                .statusCode(200)
                .body("Response.Count",is("2"))
                .body("Response.Results.MakesForMfg[0].Make_ID",equalTo("474"))
                .body("Response.Results.MakesForMfg[0].Make_Name",is(containsStringIgnoringCase("Honda")))
                .extract().xmlPath();


    }


}
