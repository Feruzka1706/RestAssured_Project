package com.cydeo.tests.practice.shortcutVideos;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class JsonPath_Test extends SpartanTestBase {

    @Test
    public void testWithJsonPath(){

      JsonPath jsonPath= given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id",11)
                .when()
                .log().all()
                .get("/spartans/{id}").jsonPath();

        //JsonPath jsonPath=response.jsonPath();

        jsonPath.prettyPeek();

       assertEquals(11,jsonPath.getInt("id"));

       assertEquals("Nona",jsonPath.getString("name"));

       assertEquals(7959094216L,jsonPath.getLong("phone"));



    }



}
