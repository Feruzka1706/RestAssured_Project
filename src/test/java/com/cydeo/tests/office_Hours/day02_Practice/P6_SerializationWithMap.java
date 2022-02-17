package com.cydeo.tests.office_Hours.day02_Practice;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class P6_SerializationWithMap extends SpartanTestBase {

/**
 * We will do workflow for spartan E2E
 *
 * POST One Spartan
 * get ID from this POST request
 * and use it for PUT and PATCH request
 * GET One Spartan to verify it is updated
 * DELETE same spartan
 * Get same spartan again to see 404
 */

    int spartanId=0;
   @Test
    public void PostSpartan(){

       Map<String,Object> newSpartan= new LinkedHashMap<>();

       newSpartan.put("name","John Doe");
       newSpartan.put("gender","Male");
       newSpartan.put("phone",1282930232L);


    spartanId=  given()
               .log().all()
               .contentType(ContentType.JSON)
               .body(newSpartan)
               .when().post("/spartans").prettyPeek()
               .then()
               .body("succes",is("A Spartan is Born!"))
               .extract().jsonPath().getInt("data.id");

      System.out.println(spartanId);


   }

}
