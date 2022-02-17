package com.cydeo.tests.day08;

import com.cydeo.utility.SpartanAuthTestBase;
import com.cydeo.utility.SpartanUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;

public class AdminAuthTesting extends SpartanAuthTestBase {


    @DisplayName("All credentials should able to read data testing")
    @ParameterizedTest
    @ValueSource(strings = {"admin","editor","user"})
    public void testReadData(String role){

        /**
         * In this test all credentials should pass as result
         * because all credentials should GET data from table
         */

        given()
                .auth().basic(role,role)
                .pathParam("id", SpartanUtil.getRandomIdAuthorized())
                .when()
                .get("/spartans/{id}")
                .then()
                .log().ifValidationFails()
                .statusCode(200);

    }


    @DisplayName("Only Admin and Editor should able to create a data testing")
    @ParameterizedTest
    @ValueSource(strings = {"admin","editor","user"})
    public void testPostDataWithAdmin(String role){
        /**
         * In this role last test case should fail as USER cannot do any action except
         * reading/GET  data from table
         */
       JsonPath jsonPath= given()
                .auth().basic(role,role)
                .log().uri()
                .contentType(ContentType.JSON)
                .body(SpartanUtil.getRandomSpartanMapBody())
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201).extract().jsonPath();

       int idNum=jsonPath.getInt("data.id");


        /**
         * Now send another GET request and check with that ID number if created that data or not
         */
       given()
                .auth().basic(role,role)
                .contentType(ContentType.JSON)
                .log().uri()
                .pathParam("id",idNum)
                .when()
                .get("/spartans/{id}")
                .then()
                        .log().all()
               .statusCode(200);

    }



    @DisplayName("Only Admin and Editor should able to update a data testing")
    @ParameterizedTest
    @ValueSource(strings = {"admin","editor","user"})
    public void testPutDataWithAdmin(String role){

        JsonPath jsonPath= given()
                .auth().basic(role,role)
                .log().uri()
                .pathParam("id",SpartanUtil.getRandomIdAuthorized())
                .contentType(ContentType.JSON)
                .body(SpartanUtil.getRandomSpartanMapBody())
                .when()
                .put("/spartans/{id}")
                .then()
                .log().all()
                .statusCode(204).extract().jsonPath();

    }


    @DisplayName("Only Admin  able to DELETE a data testing")
    @ParameterizedTest
    @ValueSource(strings = {"admin","editor","user"})
    public void testDeleteDataWithAdmin(String role){

        /**
         * In this test Only Admin should pass and other 2 credentials MUST fail
         * and get 403 forbidden result as Editor and User don't have access to deleting data
         */

        JsonPath jsonPath=  given()
                .auth().basic(role,role)
                .contentType(ContentType.JSON)
                .log().uri()
                .pathParam("id",SpartanUtil.getLastIdAuthorized())
                .when()
                .delete("/spartans/{id}")
                .then()
                .log().all()
                .statusCode(204).extract().jsonPath();


    }





}
