package com.cydeo.tests.day08;

import com.cydeo.utility.SpartanAuthTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;

public class BaseAuthTest  extends SpartanAuthTestBase {
    //Spartan App that run port 7000 require basic auth to make authorized request
    //3 roles with different authority admin/admin, editor/editor, user/user
    //we learned how to provide api key or token
    //in query param or header according to the doc
    //in order to provide basic auth we use auth().basic("username", "apssword")
    //in given section

    /**
     * Create a test method testPublicUser
     * Send a request GET /spartans without any authentication
     * expect 401
     */

    /**
     * Create a test method testAuthenticatedUser
     * Send a request GET /spartans without any authentication
     * expect 200
     */

    @Test
    public void testPublicUser(){

        given().log().uri()
                .when()
                .get("/spartans")
                .then()
                .log().all()
                .statusCode(401);



    }

    @Test
    public void testAuthenticatedUser(){

        given()
                .log().all()
                .auth().basic("user","user")
                .when()
                .get("/spartans")
                .then().log().ifValidationFails()
                //this will only show response log if something fails
                .statusCode(200);


    }

    /**
     * Role Based Access Control Test, also knows as RBAC
     * is a type of testing to ensure authenticated user can
     * perform everything they have authorized to
     * in the meantime cannot perform anything that they are not authorized to perform
     *
     * admin : create , read, update, delete
     * editor : create, read, update
     * user : read only
     */


    /**
     * Given editor credentials are provided
     * When editor try to delete any data
     * Then the response should be 403 forbidden
     */
    @DisplayName("Editor should not be able to delete data")
    @Test
    public void testWithEditorDeleteData(){

        given()
                .auth().basic("editor","editor")
                .pathParam("id", 123)
                .when()
                .delete("/spartans/{id}")
                .then()
                .log().ifValidationFails()
                .statusCode(403);

    }

    /**
     * Write a Parametrized test for getting all data GET/spartans
     * with different credentials
     * use @ValueSource in this particular example
     * since username and password are the same, we can just provide
     */

    @ParameterizedTest
    @ValueSource(strings = {"admin","editor","user"} )
    public void testAllUserGetAllSpartans(String role){
        //in this particular api, username and password have same value as role name
        //that is why we are just using one parameter

        given()
                .auth().basic(role,role)
                .when()
                .get("/spartans")
                .then()
                .log().ifValidationFails()
                .statusCode(200);
    }


    /**
     * Create 3 separate class AdminAuthTest , EditorAuthTest, UserAuthTest
     * Write separate tests for below requirement to do Role Base access control test
     *
     *       admin : create , read, update, delete
     *       editor : create, read, update
     *       user : read only
     */


}
