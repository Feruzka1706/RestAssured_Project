package com.cydeo.tests.practice;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
public class Chaining_Hamcrest {

    @BeforeAll
    public static void setUp(){
       baseURI="http://52.87.222.1:8000";
       basePath="/api";
    }

    @AfterAll
    public static void teardown(){
      reset();
    }

    @Test
    public void testChaining(){
        //Response response=
                            given()
                           .accept(ContentType.JSON)
                            .pathParam("id",15)
                            .when().get("/spartans/{id}")
                //above lines are request
                //below lines are response
                            .then().statusCode(200).and().
                             assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void testChaining2(){

        given()
                .accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/spartans/{id}")
                .then().statusCode(200).and()
                .assertThat().contentType("application/json")
                .and().assertThat().body("id", equalTo(15),
                        "name",equalTo("Meta"),
                        "gender",equalTo("Female"),
                        "phone",equalTo(1938695106) );



    }

}
