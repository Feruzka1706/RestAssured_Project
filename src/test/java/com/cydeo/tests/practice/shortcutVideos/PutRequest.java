package com.cydeo.tests.practice.shortcutVideos;

import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class PutRequest extends SpartanTestBase {

    @Test
    public void testWithPutMethod(){

        //Different ways sending json body >>String
        // >> as a Map
        // >> POJO Objects

        Spartan spartan1 =new Spartan("Bla Bla","Female",26378190232L);

      given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id",156)
                .body(spartan1)
                .when()
                .put("/spartans/{id}")
              .then()
              .assertThat().statusCode(204);


    }


    @Test
    public void testWithPatchMethod(){

        Map<String,Object> patchMap= new LinkedHashMap<>();
        patchMap.put("name","Mandalorian");
        patchMap.put("gender","Male");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id",155)
                .body(patchMap)
                .when()
                .patch("/spartans/{id}")
                .then()
                .assertThat().statusCode(204);

        Response response =given()
                .contentType(ContentType.JSON)
                .log().all()
                .pathParam("id",155)
                .when().get("/spartans/{id}");

        response.prettyPrint();


    }


}
