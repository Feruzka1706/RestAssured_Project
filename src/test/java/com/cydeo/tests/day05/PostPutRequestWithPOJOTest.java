package com.cydeo.tests.day05;

import com.cydeo.pojo.Spartan;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.cydeo.utility.SpartanTestBase;
import com.cydeo.utility.SpartanUtil;
import io.restassured.http.ContentType;


import java.util.Map;

import static io.restassured.RestAssured.*;

@Tag("tc1")
public class PostPutRequestWithPOJOTest extends SpartanTestBase {


    /**
     POST /spartans
     content type is json
     body is
     {
     "name":"API POST",
     "gender":"Male",
     "phone":1231231231
     }

     We learned how to represent json body using Map
     and let Jackson?Gson data-bind library to serialize it into Json when sending to the server

     Another common way of representing Json data is using special purpose Java Object created from A class
     That have fields matched to Json keys and have getters and setters
     This type of Object, sole purpose is to represent data, known as POJO >>Plain Old Java Object
     The class to create POJO known as POJO class,
     it's used for creating POJO< just like you create any other object

     For example: in order to represent a Json data with 3 fields , name, gender, phone
     we can create a java class with 3 instance fields with same name




    */

    @Test
    public void testPostWithPOJOasBody(){
       /**
         given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON);

     */

        Spartan spartan1=new Spartan("B23 POJO","Female",5555555555L);


        spartan1.setName("Feruza");
        System.out.println("spartan1 = " + spartan1);

        //now we are going to use this body in POST request
        //and expect jackson-databind to convert that to json string when sending as body
        //so it can be added to the server as new data
        //only thing different in here is using POJO as body
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan1)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON);





    }

    @Test
    public void testWithPutRequestPOJO(){
        // HOMEWORK : Use POJO for Update 1 Data PUT Request
        // use your getRandomSpartanPOJO utility method for body

        Map<String,Object> mapPOST=SpartanUtil.getRandomSpartanMapBody();
       Spartan spartan1=new Spartan();
       spartan1.setName(mapPOST.get("name").toString());
       spartan1.setGender(mapPOST.get("gender").toString());
       spartan1.setPhone((Long) mapPOST.get("phone"));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan1)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON);


        //System.out.println("spartan1 = " + spartan1);
        int idNum=SpartanUtil.getRandomId();

        Map<String,Object> mapPUT=SpartanUtil.getRandomSpartanMapBody();
        Spartan spartan2=new Spartan();
        spartan2.setName(mapPUT.get("name").toString());
        spartan2.setGender(mapPUT.get("gender").toString());
        spartan2.setPhone((Long) mapPUT.get("phone"));


        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id",50)
                .and().body(spartan2)
                .when()
                .put("/spartans/{id}")
                .then()
                .log().body()
                .statusCode(204)
                .contentType(ContentType.JSON);





    }

}
