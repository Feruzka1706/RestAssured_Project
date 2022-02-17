package com.cydeo.tests.office_Hours.day02_Practice;

import com.cydeo.utility.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;


public class P3_HamCrest extends HRTestBase {

   /*
      Given
               accept type is application/json
       When
               user sends get request to /regions
       Then
               response status code must be 200
               verify Date has values
               first region name is Europe
               Regions name should be same order as "Europe","Americas","Asia"
               region ids needs to be 1,2,3,4
               ...
               ..
               .
    */

    @DisplayName("Get Region")
    @Test
    public void getRegion(){

      Response response=  given()
                .accept(ContentType.JSON)
                .log().all()

        .when()
                .get("/regions")
                .prettyPeek()
        .then()
                .statusCode(200)
              .header("Date",notNullValue())
              .body("items[0].region_name",equalTo("Europe") )
              .body( "items[0].region_id",is(1),"items",hasSize(4) )
              .body( "items.region_name",
                      containsInRelativeOrder("Europe","Americas","Asia","Middle East and Africa") )
              .body(  "items.region_id",containsInRelativeOrder(1,2,3,4) )

              .extract().response();





    }

}
