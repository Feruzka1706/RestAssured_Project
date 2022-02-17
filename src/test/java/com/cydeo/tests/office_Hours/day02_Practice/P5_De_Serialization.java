package com.cydeo.tests.office_Hours.day02_Practice;

import com.cydeo.pojo.LocationsPOJO;
import com.cydeo.utility.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class P5_De_Serialization extends HRTestBase {

    /**
     *  Create a test called getLocation
     *    1. Send request to GET /locations
     *    2. log uri to see
     *    3. Get all Json as a map and print out screen all the things with the help of  map
     *            System.out.println("====== GET FIRST LOCATION  ======");
     *            System.out.println("====== GET FIRST LOCATION LINKS  ======");
     *            System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
     *            System.out.println("====== FIRST LOCATION ======");
     *            System.out.println("====== FIRST LOCATION ID ======");
     *            System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     *            System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     *            System.out.println("====== LAST LOCATION ID ======");
     */


    @Test
    public void getLocationWithMap(){

      JsonPath jsonPath= given()
                .log().uri()
                .contentType(ContentType.JSON)
                .when()
                .get("/locations").prettyPeek().jsonPath();

        System.out.println("====== GET FIRST LOCATION  ======");
        Map<String,Object> firstLocation=jsonPath.getMap("items[0]");
        System.out.println("firstLocation = " + firstLocation);



        System.out.println("====== GET FIRST LOCATION LINKS  ======");
        Map<String,Object> firstLocationLinks=jsonPath.getMap("items[0].links[0]");
        System.out.println("firstLocationLinks = " + firstLocationLinks);


        System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
        List<Map<String, Object>> allLocations = jsonPath.getList("items");
        System.out.println("allLocations = " + allLocations);


        System.out.println("====== FIRST LOCATION ======");
        System.out.println("allLocations.get(0) = " + allLocations.get(0));


        System.out.println("====== FIRST LOCATION ID ======");
        System.out.println( allLocations.get(0).get("location_id"));


        System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
        System.out.println(allLocations.get(0).get("country_id"));


        System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
       System.out.println(allLocations.get(0).get("links"));


      System.out.println("====== LAST LOCATION ID ======");
      System.out.println(allLocations.get(allLocations.size() - 1).get("location_id"));

    }




    @Test
    public void getLocationsWithPOJO(){

        JsonPath jsonPath=given()
                .log().uri()
                .when().get("/locations").prettyPeek().jsonPath();

      List<LocationsPOJO> allLocations= jsonPath.getList("items", LocationsPOJO.class);

        System.out.println("====== GET FIRST LOCATION  ======");



        System.out.println("====== GET FIRST LOCATION LINKS AS MAP ======");
       // System.out.println(allLocations.get(0).getLinks().get(0).get("rel"));

        System.out.println("====== ALL LOCATIONS ======");
        System.out.println("allLocations = " + allLocations);


        System.out.println("====== FIRST LOCATION ID ======");
        System.out.println(allLocations.get(0).getLocationId());

        System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
        System.out.println(allLocations.get(0).getCountryId());

        System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");

        System.out.println("====== LAST LOCATION ID ======");
        System.out.println(allLocations.get(allLocations.size() - 1));


        System.out.println(allLocations.get(0).getLinks().get(0).getHref());

    }



}
