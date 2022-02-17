package com.cydeo.tests.day09;


import com.cydeo.utility.DB_Util;
import com.cydeo.utility.HRTestBase;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;




public class API_DB_Test extends HRTestBase {


   @Test
   public void testRegion(){


       String query="Select * from regions";
       DB_Util.runQuery(query);

       DB_Util.displayAllData();



   }

    /**
     * Send API request to http://52.87.222.1:1000/ords/hr/regions
     * Get your response
     * Get the expected result count
     * Assert the API response has same count as database row count
     */

    @DisplayName("Checking db and api request comparision")
    @Tag("db")
    @Test
    public void testRegionCount(){

        //prepare expected result here
        String query="Select * from regions";
        DB_Util.runQuery(query);

        //find out the row count
        int expectedRowCount= DB_Util.getRowCount();

        given()
                .log().uri()
                .when()
                .get("/regions")
                .then()
                .log().all()
                .statusCode(200)
                .body("count",is(expectedRowCount) )
                .body("items",hasSize(expectedRowCount) );
                //.body("")



    }

    @DisplayName("Testing 1 region db and api comparision")
    @Test
    public void testSingleRegion(){
        /**
         * Send request to GET/regions/{region_id}  region_id=1
         * prepare expected result from the database by running
         * Select * from regions where region_id=1
         * save the expected result query as Map
         * then verify the region_id and region_name match between api and db response
         */

        DB_Util.runQuery("Select * from regions where region_id =1");
        Map<String,String> dbResultMap= DB_Util.getRowMap(1);
        System.out.println("dbResultMap = " + dbResultMap);

        int expectedRegionId= Integer.parseInt(dbResultMap.get("REGION_ID") );
        String expectedRegionName=dbResultMap.get("REGION_NAME");

        given().
                log().uri()
                .pathParam("region_id",1)
                .when()
                .get("/regions/{region_id}")
                .then()
                .log().body()
                .statusCode(200)
                .body("region_id",is(expectedRegionId))
                .body("region_name",equalTo(expectedRegionName)) ;



    }

    /**
     * Write a Parameterized Test to test all regions instead of one above
     * try couple different way
     * 1. @ValueSource  to provide all 4 regions id
     * 2. @MethodSource
     *       -- get all id s from api response GET /regions and return List<Integer>
     * 3. @MethodSource
     *       -- get all id s from SELECT * FROM REGIONS query and return List<String>
     *           and use it as a source
     */


     @Tag("ValueSource")
      @DisplayName("Testing with Value Source each region by one")
      @ParameterizedTest()
      @ValueSource(ints = {1,2,3,4})
      public void testWithValueSourceRegions(int regionId){

        given().
                log().uri()
                .pathParam("region_id",regionId)
                .when()
                .get("/regions/{region_id}")
                .then()
                .log().all()
                .statusCode(200);

    }




    @Tag("MethodSource")
    @DisplayName("Testing with MethodSource each region from API Response body")
    @ParameterizedTest()
    @MethodSource("getAllRegionIdWithRequest")
    public void testWithMethodSourceRegions(int regionId){

        given().
                log().uri()
                .pathParam("region_id",regionId)
                .when()
                .get("/regions/{region_id}")
                .then()
                .log().all()
                .statusCode(200);

    }


    public static List<Integer>  getAllRegionIdWithRequest(){

      JsonPath jsonPath= given()
               .log().uri()
               .when()
               .get("/regions")
               .then()
               .log().body()
               .statusCode(200).extract().jsonPath();

       return new ArrayList<>( jsonPath.getList("items.region_id"));

    }



    @Tag("MethodSource")
    @DisplayName("Testing with MethodSource each region from DB info")
    @ParameterizedTest()
    @MethodSource("getAllRegionIDWithDBSource")
    public void testWithMethodSourceRegions2(String regionId){

          int idNum=Integer.parseInt(regionId);

        given().
                log().uri()
                .pathParam("region_id",idNum)
                .when()
                .get("/regions/{region_id}")
                .then()
                .log().all()
                .statusCode(200);

    }


    public static List<String> getAllRegionIDWithDBSource(){

          DB_Util.runQuery("Select * from regions");
          //DB_Util.displayAllData();
         List<String> allRegionID= DB_Util.getColumnDataAsList(1);

          return allRegionID;
    }



}
