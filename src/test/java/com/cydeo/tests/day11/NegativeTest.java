package com.cydeo.tests.day11;

import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanTestBase;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class NegativeTest extends SpartanTestBase {

    /**
     * Send POST /spartans request json payload have below requirements
     * name : 2-15 chars
     * gender : Male or Female
     * phone : 10-13 digits
     *
     * Knowing that we already tested all positive scenario
     * make sure it generates error with proper json body to reflect error
     * according to the negative test data you provided
     *
     * For example:
     * {
     *     "name":"A",
     *     "gender":"Male",
     *     "phone":1231231231
     * }
     *
     * we should expect 400 response
     {
     *     "name":"A",
     *     "gender":"Male",
     *     "phone":1231231231
     * }
     * we should expect 400 response
     * {
     *     "message": "Invalid Input!",
     *     "errorCount": 1,
     *     "errors": [
     *         {
     *             "errorField": "name",
     *             "rejectedValue": "A",
     *             "reason": "name should be at least 2 character and max 15 character"
     *         }
     *     ]
     * }
     * error count should be 1
     * "message": "Invalid Input!",
     */


    @DisplayName("POST /spartans invalid payload should return error")
    @Test
    public void testAdd1DataNegativeScenario(){

        //prepare test body for invalid Scenario
        Spartan invalidBody=new Spartan("A","Male",12384903238L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(invalidBody)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(400)
                .body("message",is("Invalid Input!"))
                .body("errorCount",equalTo(1) )
                .body("errors[0].errorField",is("name"));
    }


    /**
     * Write a ParametrizedTest for negative scenarios for 'POST /spartans' payload
     * 1.prepare your data in csv file with below columns
     *   1.'name', 'gender', 'phone'
     *   'expectedErrorCount'
     *   2.provide some invalid data by mixing and
     *   matching the errors and provide expected
     *   error count for that set of data in each row
     *    3. in your parameterized test drive
     *    the test using csv file along with your assertions
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/negativePostData.csv",numLinesToSkip = 1)
    public void testNegativeWithParametrized(String nameParam, String genderParam, long phoneParam, int expectedCount){

        //prepare test body for invalid Scenario
        Spartan invalidBody=new Spartan(nameParam,genderParam,phoneParam);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(invalidBody)
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(400)
                .body("message",is("Invalid Input!"))
                .body("errorCount",equalTo(expectedCount));


    }


    @Tag("Defect110")
    //@Disabled("Known issue: Defect110")
    @DisplayName("Test Phone upper boundary")
    @Test
    public void testPutRequestNegativeScenario(){

        /**
         * According to the doc, in PUT /spartans/{id}
         * the valid phone number should be 10-13 digit
         * and anything is not that should return 400
         * a defect was found the upper boundary is not working as expected
         * so write a text to make sure you cover such negative scenario for phone number
         */

        int lastId=get("/spartans").path("id[-1]");
        System.out.println("lastId = " + lastId);

        //prepare body, wrong phone number
        Faker faker =new Faker();
        long phone15Digit = faker.number().randomNumber(15,true);
        System.out.println("phone15Digit = " + phone15Digit);

        Spartan bodyWithInvalidPhone =new Spartan("Alex","Male",phone15Digit);

        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .pathParam("id",lastId)
                .body(bodyWithInvalidPhone)
                .when()
                .put("/spartans/{id}")
                .then()
                .statusCode(400);


    }

}
