package com.cydeo.tests.practice.shortcutVideos;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class DeSerialization_Collection extends SpartanTestBase {


    //Converting JSON to Java is De-Serialization
    @Test
    public void testDe_Serialization(){

        //Converting JSON to Java is De-Serialization

      JsonPath jsonPath= given()
                .contentType(ContentType.JSON)
                .pathParam("id",11)
               .when()
               .get("/spartans/{id}").jsonPath();

      //convert Json response to Java Collections
        /*
       Map<String,Object> spartanMap=response.body().as(Map.class);
        System.out.println("spartanMap = " + spartanMap);

         */

        Map<String,Object> spartanMap=jsonPath.getMap("");
        System.out.println("spartanMap = " + spartanMap);

        System.out.println("spartanMap.get(\"name\") = " + spartanMap.get("name"));

        assertEquals(11,spartanMap.get("id"));

        assertEquals("Nona",spartanMap.get("name"));

        assertEquals(7959094216L,spartanMap.get("phone"));

    }


    @Test
    public void test2(){

        JsonPath jsonPath=given()
                .contentType(ContentType.JSON)
                .when().get("/spartans").jsonPath();

        //jsonPath.prettyPeek();

        //converting full JSON body to list of Map
        List<Map<String,Object>> allSpartansInfo =jsonPath.getList("");

        for (Map<String, Object> eachSpartanMap : allSpartansInfo) {

            if(eachSpartanMap.get("name").toString().contains("Feruza")){
                System.out.println("eachSpartanMap = " + eachSpartanMap);
            }

        }


        Map<String,Object> firstSpartan= allSpartansInfo.get(0);
        //System.out.println("firstSpartan = " + firstSpartan);

    }


}
