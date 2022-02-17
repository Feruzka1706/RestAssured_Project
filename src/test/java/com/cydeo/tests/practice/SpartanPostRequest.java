package com.cydeo.tests.practice;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class SpartanPostRequest extends SpartanTestBase {

    @Test
    public void testPostSpartan(){
        /**
         * {
         *     "name":"Feruza",
         *     "gender":"Female",
         *     "phone":1231231231
         * }
         */

        String jsonBody="{\n" +
                "    \"name\":\"Kuku\",\n" +
                "    \"gender\":\"Female\",\n" +
                "    \"phone\":2930487290\n" +
                "}";

        given().accept(ContentType.JSON)
                .log().all()
                .and().contentType(ContentType.JSON)
                .body(jsonBody)
                .when().post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success",is( containsStringIgnoringCase("A Spartan is Born!")))
                .body("data.name",is("Kuku"))
                .body("data.gender",equalTo("Female"))
                .body("data.phone.toLong()",equalTo(2930487290L));



    }


    @Test
    public void PostWithMap(){
        //create a Map to be used as a body for post request
        Map<String ,Object> requestMap= new LinkedHashMap<>();
        requestMap.put("name","Bla bla");
        requestMap.put("gender","Male");
        requestMap.put("phone",2390459293L);

        given().accept(ContentType.JSON)
                .log().all()
                .and().contentType(ContentType.JSON)
                .body(requestMap)
                .when().post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success",is( containsStringIgnoringCase("A Spartan is Born!")))
                .body("data.name",is("Bla bla"))
                .body("data.gender",equalTo("Male"))
                .body("data.phone.toLong()",equalTo(2390459293L));


    }


    @Test
    public void testWithPutRequest(){

        //we will send PUT request body (update the existing data), verify it
        //first way -string
        //-Using collection(Map)
        //-POJO object help
        String jsonBody="{\n" +
                "    \"name\":\"Kuku\",\n" +
                "    \"gender\":\"Female\",\n" +
                "    \"phone\":2930487290\n" +
                "}";
        String statusMsg="A Spartan is Born!";
        given()
                .contentType(ContentType.JSON)
                .pathParam("id",127)
                .log().all()
                .and().body(jsonBody)
                .when().put("/spartans/{id}")
                .then().statusCode(204);


    }

    @Test
    public void testPatchOneDataSpartan(){
        //id 127
        Map<String ,Object> patchMap= new LinkedHashMap<>();
        String statusMsg="A Spartan is Born!";
        given()
                .contentType(ContentType.JSON)
                .pathParam("id",127)
                .log().all()
                .and().body(patchMap)
                .when().patch("/spartans/{id}")
                .then()
                .log().all().statusCode(204);

    }

    @Test
    public void testDeleteOneDataSpartan(){

        //delete existing data which id is 127 from Spartans and verify it's not existed
        given()
                .pathParam("id",127)
                .log().all()
                .when()
                .delete("/spartans/{id}")
                .then()
                .log().all().statusCode(204);

        given()
                .pathParam("id",127)
                .when().delete("/spartans/{id}")
                .then()
                .log().all().statusCode(404);
    }


}
