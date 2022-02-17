package com.cydeo.tests.office_Hours.day02_Practice;

import com.cydeo.utility.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P2_ResponsePath extends HRTestBase {

    /*
       Given
                accept type is application/json
        When
                user sends get request to /regions/2
        Then
                response status code must be 200
                region_name is Americas
                region_id is 2
                print out all the links

     */


    @DisplayName("Get Region")
    @Test
    public void getRegion() {

        Response response = given()
                .accept(ContentType.JSON)
                .log().all()
                .pathParam("id", 2)
                .when().get("/regions/{id}")
                .prettyPeek();

        Assertions.assertEquals(200,response.statusCode());

        String regionName=response.path("region_name");
        System.out.println("regionName = " + regionName);
        int regionId =response.path("region_id");
        System.out.println("regionId = " + regionId);

        List<Map<String,String>> allLinks=response.path("links");

        for (Map<String, String> eachLink : allLinks) {

            System.out.println("eachLink.get(\"rel\") = " + eachLink.get("rel"));
            System.out.println("eachLink.get(\"href\") = " + eachLink.get("href"));
            System.out.println("=====================================");
        }


    }



    @ParameterizedTest
    @CsvFileSource(resources = "/regions.csv",numLinesToSkip = 1)
    public void getRegionParametrized(int id, String regionName) {

        Response response = given()
                .accept(ContentType.JSON)
                .log().all()
                .pathParam("id", id)
                .when().get("/regions/{id}")
                .prettyPeek();

        Assertions.assertEquals(200,response.statusCode());

        String region_Name=response.path("region_name");
        System.out.println("regionName = " + regionName);
        int regionId =response.path("region_id");
        System.out.println("regionId = " + regionId);

        List<Map<String,String>> allLinks=response.path("links");

        for (Map<String, String> eachLink : allLinks) {

            System.out.println("eachLink.get(\"rel\") = " + eachLink.get("rel"));
            System.out.println("eachLink.get(\"href\") = " + eachLink.get("href"));
            System.out.println("=====================================");
        }


        Assertions.assertEquals(regionName,region_Name);
        Assertions.assertEquals(id,regionId);


    }

}