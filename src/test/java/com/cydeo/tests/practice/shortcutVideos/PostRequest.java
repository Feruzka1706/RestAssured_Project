package com.cydeo.tests.practice.shortcutVideos;

import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanTestBase;
import com.cydeo.utility.SpartanUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class PostRequest extends SpartanTestBase {


    @Test
    public void testWithPost(){

       JsonPath jsonPath= given()
                .log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(SpartanUtil.getRandomSpartanMapBody())
                .when()
                .log().all()
                .post("/spartans")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON).extract().jsonPath();

       String name =jsonPath.getString("data.name");
       // System.out.println(name);

        Response response=given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(SpartanUtil.getRandomSpartanMapBody())
                .when()
                .post("/spartans");

        response.prettyPrint();

        int idNum=response.path("data.id");
        System.out.println("idNum = " + idNum);

    }


    @Test
    public void testPostWithPOJO(){

        //first we will create object from POJO class

        Spartan spartan =new Spartan("Kuku","Male",28394303820L);


     Response response=  given()
                .contentType(ContentType.JSON)
                .and().log().all()
                .body(spartan)
                .when().post("/spartans");


     assertEquals(201,response.getStatusCode());
     assertEquals(ContentType.JSON.toString(),response.contentType());

     response.prettyPrint();


     //check with GET request
        Response response2=given().contentType(ContentType.JSON)
                .log().all()
                .pathParam("id",153)
                .and().when().get("/spartans/{id}");

        Spartan spartanResponse=response2.body().as(Spartan.class);

        System.out.println("spartanResponse = " + spartanResponse.toString());


    }

}
