package com.cydeo.tests.day11;

import com.cydeo.utility.SpartanTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

@DisplayName("Json Schema Validation Tests")
public class JsonSchemaValidationPracticeTest extends SpartanTestBase {

    /*
    ## JsonSchemaValidation Practice

   How do you check the structure of the json response without checking the field values ?

   -> JsonSchema Validation

   1. Add SearchSpartanSchema.json into your resources folder.
   2. Add the content from the note channel
   3. and Create a class under day11. JsonSchemaValidationPracticeTest
   4. Send request to GET /spartans/search
   5. Verify you got 200 and the schema match the SearchSpartanSchema.json in resources folder.
     */

    @DisplayName("Test GET /spartans/search schema ")
    @Test
    public void testSearchSchema(){
        //http://54.236.150.168:8000/api/spartans/search?nameContains=Ea&gender=Male
        //When there are more than couple key value pairs
        //like many headers, or many query params, form params
        //RestAssured provides method to let you pass it as a map in one shot

        Map<String,Object> queryMap=new LinkedHashMap<>();
        queryMap.put("nameContains","Ea");
        queryMap.put("gender","Male");

        given()
                .log().uri()
                .queryParams(queryMap)
                .when()
                .get("/spartans/search")
                .then()
                .statusCode(200)
                .body( matchesJsonSchemaInClasspath("SearchSpartanSchema.json"));



    }

}
