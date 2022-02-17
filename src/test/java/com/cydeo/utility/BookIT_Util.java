package com.cydeo.utility;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class BookIT_Util {



    public static String getToken(){

       String accessToken= given()
                .log().uri()
                .queryParam("email","sbirdbj@fc2.com")
                .queryParam("password","asenorval")
         .when()
                .get("/sign")
          .then()
                .statusCode(200)
                .extract().jsonPath().getString("accessToken");

       return "Bearer "+accessToken;
    }

}
