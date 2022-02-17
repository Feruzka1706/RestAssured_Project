package com.cydeo.tests.day05;

import com.cydeo.utility.SpartanTestBase;
import com.cydeo.utility.SpartanUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Serialization: Converting from Java Object to Json (or any other text)
 *
 * De-Serialization: Process of Converting from Json (any text) to Java Object
 */

public class PostRequestWithObject extends SpartanTestBase {

    /**
     POST /spartans
     content type is json
     body is
     {
     "name":"API POST",
     "gender":"Male",
     "phone":1231231231
     }

     instead of providing above body in String,
     We want to be able to provide the body as Java Object (like Map or POJO )
     and let any kind of Serialization library convert that into String for us
     while sending the request

     **/

    @Test
    public void testPostWithMap(){
;
        Map<String,Object> bodyMap=new LinkedHashMap<>();

        bodyMap.put("name","API POST");
        bodyMap.put("gender","Male");
        bodyMap.put("phone",12312312312L);

        System.out.println("bodyMap = " + bodyMap);

        /**
         Jackson-databind is the library for serialization and de-serialization-->
         rest assured need it in dependency so it can automatically
         convert the java object to json without additional code
         */

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON);

    }


    @Test
    public void testPostWithMapWithRandomData(){
        /*
        Map<String,Object> bodyMap=new LinkedHashMap<>();
        Faker faker =new Faker();

        bodyMap.put("name",faker.name().firstName());
        bodyMap.put("gender",faker.demographic().sex());//Male or Female
        bodyMap.put("phone",faker.numerify("###########"));
        //bodyMap.put("phone", faker.number().numberBetween(5000000000L,9999999999L));

        System.out.println("bodyMap = " + bodyMap);

         */

    // Having utility method, so we can just call a method as below
       Map<String,Object> bodyMap= SpartanUtil.getRandomSpartanMapBody();

        /**
         Jackson-databind is the library for serialization and de-serialization-->
         rest assured need it in dependency so it can automatically
         convert the java object to json without additional code
         */

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON);

    }

    //send request to PUT  /spartans/{id} with Random Map Body
    @Test
    public void testUpdate1DataWithRandomBody(){

      //  int lastId=get("/spartans").path("id[-1]");
        int lastId=SpartanUtil.getRandomId();
        System.out.println("lastId = " + lastId);

        //prepare Updated body:
        Map<String,Object> updatedBody=SpartanUtil.getRandomSpartanMapBody();

        given()
                .log().all()
                .pathParam("id",lastId)
                .contentType(ContentType.JSON)
                .body(updatedBody)
                .when()
                .put("/spartans/{id}")
                .then()
                .statusCode(204)
                .log().all();


    }

    //send another get request to see if it was updated to the new info
    @Test
    public void testGetRequestToCheckResult(){
        int lastId=get("/spartans").path("id[-1]");

        given()
                .log().all()
                .pathParam("id",lastId)
                .contentType(ContentType.JSON)
                .when()
                .get("/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name",is("Hank"),"gender",equalToIgnoringCase("Female"))
                .log().all();
    }



}
