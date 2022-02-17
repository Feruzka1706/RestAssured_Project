package com.cydeo.tests.homeworks;

import com.cydeo.pojo.PlacePOJO;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;


public class ZipCodeApiHomework {
    /**
     *  << Homework >>
     */

    @BeforeAll
    public static void setup() {
        baseURI = "https://api.zippopotam.us";
        basePath = "/us";
    }

    @AfterAll
    public static void teardown() {
        reset();
    }


    @DisplayName("City to Zip Testing")
    @Test
    public void test1(){
        /**
         *  Task1
         *  in one test
         *       send request to GET https://api.zippopotam.us/us/va/fairfax
         *       log request all parts
         *       use va and fairfax as path variables with name state / city
         *       send get request verify
         *       status code 200 , json format
         */

        given()
                .log().all()
                .pathParam("state","CA")
                .pathParam("city","South Lake Tahoe")
                .when()
                .get("/{state}/{city}")
                .then()
                .statusCode(200).contentType(ContentType.JSON)
                .body("places[0].'place name' ",equalTo("South Lake Tahoe"),
                        "places[0].'post code'",is("96150"));


    }



    @DisplayName("De-Serialization with POJO testing")
    @Test
    public void test2(){

        /**  Task2
         * in another test
         *      send same request and store the response object
         *      get JsonPath object from the response
         *      print last place name
         *      print all zip codes after storing it into the list
         *      create a pojo called Place to represent place json object
         *          with these specific fields :
         *         - name as String
         *         - postCode as int
         *         - latitude as float
         *         - longitude as float
         *            {
         *                "place name": "Fairfax",
         *                "longitude": "-77.3242",
         *                "post code": "22030",
         *                "latitude": "38.8458"
         *            }
         *     de-serialize the first response into Place pojo and print it out
         *     save all the place json array into List<Place> and print it out.
         *
         */

       Response response= given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("state","VA")
                .pathParam("city","Fairfax")
                .when()
                .get("/{state}/{city}");

        JsonPath jsonPath=response.jsonPath();

        String lastPlaceName=jsonPath.getString("places[-1].'place name'");
        System.out.println("lastPlaceName = " + lastPlaceName);

        List<String> allZipCodes= jsonPath.getList("places.'post code'");
        System.out.println("allZipCodes = " + allZipCodes);


        PlacePOJO firstPlace=jsonPath.getObject("places[0]",PlacePOJO.class);
        System.out.println("firstPlace = " + firstPlace);

        List<PlacePOJO> allPlaces = jsonPath.getList("places",PlacePOJO.class);
        for (PlacePOJO eachPlaceInfo : allPlaces) {

            System.out.println("eachPlaceInfo = " + eachPlaceInfo);
        }



    }




    @DisplayName("CSV file DDT for City to zip code")
    @ParameterizedTest
    @CsvFileSource(resources = "/state_city.csv",numLinesToSkip = 1)
    public void test3(String stateName, String cityName){


        /**
         *     Task3.
         *    Create a file called state_city.csv under resources folder
         *      it has two columns  state , city
         *       add some valid data
         *
         *      send request to GET https://api.zippopotam.us/us/{state}/{city}
         *           *  log request uri
         *           *   use state and city as path variables with name state / city
         *           *   for actual value of path params get it from csv file
         *           *   send get request verify
         *           *   status code 200 , json format
         */


        given()
                .log().all()
                .pathParam("state",stateName)
                .pathParam("city",cityName)
                .when()
                .get("/{state}/{city}")
                .then().statusCode(200).contentType(ContentType.JSON);



    }


}
