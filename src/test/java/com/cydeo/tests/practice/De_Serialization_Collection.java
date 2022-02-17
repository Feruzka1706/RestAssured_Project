package com.cydeo.tests.practice;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class De_Serialization_Collection {


    @BeforeAll
    public static void setUp(){
        baseURI="http://52.87.222.1:8000";
        basePath="/api";
    }

    @AfterAll
    public static void teardown(){
        reset();
    }

    @Test
    public void testDe_Serialization(){
        Response response=given()
                .accept(ContentType.JSON)
                .pathParam("id",11)
                .and().when().get("/spartans/{id}");

        //converting Json response to Java data structures(Map)
        Map<String,Object> spartanMap=response.body().as(Map.class);
        System.out.println(spartanMap.get("name"));
        System.out.println(spartanMap.get("gender"));
        System.out.println(spartanMap.get("phone"));
        System.out.println(spartanMap.get("id"));

        assertEquals("Nona",spartanMap.get("name"));
        assertEquals("Female",spartanMap.get("gender"));
        assertEquals(11.0,spartanMap.get("id"));
    }

    @Test
    public void testListOfMap(){
        Response response=given()
                .accept(ContentType.JSON)
                .when().get("/spartans");

        //converting Json body(payload) response to List<Map<String,Object>> :))))
        //inside list we have many maps which each map keeps one json object as key & value
      List<Map<String,Object>> listOfMap=  response.body().as(List.class);

       //Object nameOfFirstMap= listOfMap.get(0).get("name");
       // System.out.println(nameOfFirstMap);

        Map<String,Object> firstMap= listOfMap.get(0);
        System.out.println("firstMap = " + firstMap);
        System.out.println("firstMap.get(\"name\") = " + firstMap.get("name"));

       //printing all names from listOfMap
        int count=1;
        for (Map<String, Object> map : listOfMap) {
            System.out.println(count+" - spartan "+map);
            count++;
        }


    }
}
