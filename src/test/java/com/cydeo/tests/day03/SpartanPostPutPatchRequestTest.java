package com.cydeo.tests.day03;

import com.cydeo.utility.SpartanTestBase;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanPostPutPatchRequestTest extends SpartanTestBase {

    @Test
    public void testAdd1DataStringBody() {
        //POST /spartans
        //content-type is json
        //body is
        /**
         * {
         *   "name": "API POST",
         *   "gender": "Male",
         *   "phone": 1231231231
         *
         * }
         *
         * expect status code is 201
         * body json format
         *         response body
         *         {
         *               "success": "A Spartan is Born!",
         *               "data": {
         *                 "id": 544,
         *                 "name": "API POST",
         *                 "gender": "Male",
         *                 "phone": 1231231231
         *                }
         *          }
         *
         */


        String strBody = "{\n" +
                "                    \"name\":\"API POST\",\n" +
                "                    \"gender\":\"Male\",\n" +
                "                    \"phone\":1231231231\n" +
                "          }";


        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(strBody)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .body("success",is("A Spartan is Born!"))
                .body("data.name",is(containsStringIgnoringCase("api post") ) )

                //you might get error comparing int to long
                //.body("data.phone",is(1231231231))
               //permanent solution for converting the phone number we got to long
               //is using groovy method to convert int to long using data.phone.toLong()
                .body("data.phone.toLong()",is(1231231231L));


               // .contentType(ContentType.JSON);


    }

    @Test
    public void testPutOneDataSpartan(){

        //we will send PUT request body (update the existing data), verify it
        //first way -string
        //-Using collection(Map)
        //-POJO object help
        String jsonBody="{\n" +
                "    \"name\":\"Kuku\",\n" +
                "    \"gender\":\"Female\",\n" +
                "    \"phone\":2930487290\n" +
                "}";
        String statusMsg="A Spartan is Born!";
        given()
                .contentType(ContentType.JSON)
                .pathParam("id",126)
                .log().all()
                .and().body(jsonBody)
                .when().put("/spartans/{id}")
                .then().statusCode(204);

    }


    @Test
    public void testPatchOneDataSpartan(){

        //id 127
        Map<String ,Object> patchMap= new LinkedHashMap<>();
        patchMap.put("name","Boo-Boo");
        String statusMsg="A Spartan is Born!";
        given()
                .contentType(ContentType.JSON)
                .pathParam("id",126)
                .log().all()
                .and().body(patchMap)
                .when().patch("/spartans/{id}")
                .then()
                .log().all().statusCode(204);

    }

    @Test
    public void testDeleteOneDataSpartan(){

        //delete existing data which id is 127 from Spartans and verify it's not existed
        //Keep in mind that , once you delete the data , your test will fail
        //In order to avoid that
        //We can send request to GET /spartans endpoint
        //and get the existing id in the system

        // Response response=get("/spartans");
        int lastId=get("/spartans").path("id[-1]");

        given()
                .pathParam("id",lastId)
                .log().all()
                .when()
                .delete("/spartans/{id}")
                .then()
                .log().all().statusCode(204);
       /*
        given()
                .pathParam("id",127)
                .when().delete("/spartans/{id}")
                .then()
                .log().all().statusCode(404);

        */
    }




}
