package com.cydeo.tests.practice.shortcutVideos;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Authentication_Authorization {

    String baseUrl="https://cybertek-reservation-api-qa3.herokuapp.com";

    @Test
    public void testWithAuthorization(){

        String myToken="Bearer yJhbGciOiJIUzI1NiJ9." +
                "eyJqdGkiOiIxNDAiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0." +
                "xNvdQRyrYMb3Ec5QByHwXowBo3zPK2jQQS1eJ2RYIto";

       Response response= given()
                .log().all()
                .header("Authorization",myToken)
                .when().get(baseUrl+"/api/campuses");

       response.prettyPrint();

      assertEquals(200,response.getStatusCode());
       assertEquals(ContentType.JSON.toString(),response.getContentType());


    }


}
