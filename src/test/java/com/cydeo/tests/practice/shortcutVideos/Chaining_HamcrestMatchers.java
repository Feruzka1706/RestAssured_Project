package com.cydeo.tests.practice.shortcutVideos;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class Chaining_HamcrestMatchers extends SpartanTestBase {


    @Test
    public void test1(){

        given().contentType(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .assertThat().body("id",equalTo(15),
                        "name",is(containsStringIgnoringCase("meta"))
                ,"gender",equalTo("Female"),"phone.toLong()",equalTo(1938695106L));

    }


}
