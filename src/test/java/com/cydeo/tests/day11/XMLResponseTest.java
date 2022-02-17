package com.cydeo.tests.day11;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class XMLResponseTest {

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


    //http://ergast.com/api/f1/drivers/{driverId} driverId-? abate
    @DisplayName("GET /drivers/{driverId} XML response")
    @Test
    public void testSingleDriverXMLResponse(){

        given()
                .log().all()
                .contentType(ContentType.XML)
                .pathParam("driverId","abate")
                .when()
                .get("/drivers/{driverId}")
                .then()
                .log().all()
                .contentType(ContentType.XML)
                .statusCode(200)
                .body("MrData.DriverTable.Driver.GivenName",is("Carlo"))
                .body("MrData.DriverTable.Driver.FamilyName", is("Abate"))
                .body("MrData.DriverTable.Driver.DateOfBirth", is("1932-07-10"))
                .body("MrData.DriverTable.Driver.Nationality", is("Italian"));


    }

    //http://ergast.com/api/f1/drivers/{driverId}  driverId -? abate
    @DisplayName("Test GET /drivers/{driverId} extract xml element text")
    @Test
    public void testSingleDriverXMLResponse2() {

        Response response = given()
                .pathParam("driverId", "abate")
                .log().uri().
                when()
                .get("/drivers/{driverId}");

        //just like JsonPath class, there is specialized class for xml --> XMLPath
      XmlPath xp= response.xmlPath();

      //save the Firstname field into string variable
        String firstName=xp.getString("MrData.DriverTable.Driver.GivenName");
        System.out.println("firstName = " + firstName);

        String lastName=xp.getString("MrData.DriverTable.Driver.FamilyName");
        System.out.println("lastName = " + lastName);

        //what is the XMLPath to get attribute of an element
        //if your element tag is Person and attribute is gender
        //here is the xmlPath to get the attribute Person.@gender
        //TagName.@attributeName syntax

        //get the value of MRData.DriverTable
        String driverIdAttribute = xp.getString("MRData.DriverTable.@driverId");
        System.out.println("driverIdAttribute = " + driverIdAttribute);

        String urlAttribute= xp.getString("MRData.DriverTable.Driver.@url");
        System.out.println("urlAttribute = " + urlAttribute);

    }


    @DisplayName("Getting")
    @Test
    public void test(){

       XmlPath xp= given()
                .log().all()
                .when()
                .get("/drivers")
                .then()
                //.log().all()
                .statusCode(200).extract().xmlPath();

      String firstDriverName= xp.getString("MRData.DriverTable.Driver[0].GivenName");
        System.out.println("firstDriverName = " + firstDriverName);

        //get all the GivenNames of all drivers
        List<String> allNames=xp.getList("MRData.DriverTable.Driver.GivenName",String.class);
        System.out.println("allNames = " + allNames);

        //how to get all driverId attributes of each driver and save it into the List
        List<String> allDriverIdAttribute=xp.getList("MRData.DriverTable.Driver.@driverId");
        System.out.println("allDriverIdAttribute = " + allDriverIdAttribute);


        /**
         * Homework: Create a method to get RandomValidDriverId
         * so you can pass to the test /drivers/{driverId} instead of guessing valid driver id
         *
         * Additional Homework:
         * Explore other the rest of Collection
         * http://ergast.com/api/f1/2021/drivers
         * practice getting xml element text value, xml element attributes
         * same goes for the rest of the Collection
         *
         *
         * Homework 2:
         * Send this request in Movie API
         *
         * GET http://www.omdbapi.com/?t=The Mandalorian&r=xml?apiKey=YourKeyGoesHere
         * print out all the movie attributes
         *
         * Send the request GET  http://www.omdbapi.com/?t=The Mandalorian&r=xml?apiKey=YourKeyGoesHere
         * verify root element attribute totalResults="11"
         * store all the titles on List<String> and print
         * verify the size of list match the attribute  totalResults="11"
         *
         *
         * Homework 3:
         * Send request to GET /spartans provide accpet header to get XML response
         * practice getting the value of xml elements for single elements
         * or list of elements
         *
         *
         * Homework 4 :
         * Send GET Request to https://vpic.nhtsa.dot.gov/api/vehicles/GetMakeForManufacturer/988?format=xml
         * and verify the
         * count element text is 2
         * message Results returned successfully
         * first Make_ID is  474 , Make_Name Honda
         */


    }


}
