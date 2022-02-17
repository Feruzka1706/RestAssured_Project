package com.cydeo.tests.office_Hours.day02_Practice;

import com.cydeo.utility.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class P1_Contains extends HRTestBase {

     /*
       Given
                accept type is application/json
        When
                user sends get request to /regions/2
        Then
                response status code must be 200
                content type equals to application/json
                response body contains   Americas

     */



    @DisplayName("Get Region")
    @Test
    public void getRegion(){

       Response response=given()
                .accept(ContentType.JSON)
                .log().all()
                .pathParam("id",2)
                .when().get("/regions/{id}")
                .prettyPeek();

      assertEquals( 200,response.statusCode() );

      assertAll(
              ()-> assertEquals(ContentType.JSON.toString(),response.getContentType()),
              ()-> System.out.println("This line needs to be executed"),
              ()-> assertTrue(response.body().asString().contains("Americas"))

      );



    }


}
