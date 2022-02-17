package com.cydeo.tests.day03;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class SpartanMethodChainTest extends SpartanTestBase {

    @Test
    public void getOneSpartanTest(){
        /** in one shot,
         * send request to GET /spartans/{id} with id of 1
         * log everything
         * verify statusCode is 200
         * contentType is JSON
         * JSON body(payload) : id is 1, name OliverTheKing
         */
        given()
                .accept(ContentType.JSON)
                .pathParam("id",1)
                .log().all()
                .when()
                .get("/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .assertThat().contentType(ContentType.JSON)
                .and().assertThat().body("id",equalTo(1),
                        "name",equalTo("Meade"));


    }

    @Test
    public void testSearch(){
        //
        //verify status code is 200
        //content-type is Json
        //totalElement: 3
        //json array has size 3
        //all names has item Sean
        //every gender is Male
        //every name should contain ignoring case ea

        given()
                .log().all()
                .accept(ContentType.JSON)
                .queryParam("nameContains","Ea")
                .queryParam("gender","Male")
                .when().get("/spartans/search")
                .then()
                .log().body()
                .and().statusCode(200)
                .contentType(ContentType.JSON)
                .body("totalElement",equalTo(3))
                .body("content",hasSize(3) )
                .body("content.name",hasItem("Sean") )
                .body("content.gender",everyItem(is("Male") ) )
                .body("content.name",everyItem(containsStringIgnoringCase("ea")) );




    }


}
