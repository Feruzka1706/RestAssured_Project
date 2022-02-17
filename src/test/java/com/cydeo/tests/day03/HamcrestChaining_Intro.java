package com.cydeo.tests.day03;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class HamcrestChaining_Intro {


    @BeforeAll
    public static void setUp(){
        baseURI="http://52.87.222.1:1000";
        basePath="/ords/hr";
    }

    @AfterAll
    public static void teardown(){
        reset();
    }


    /**
     * Hamcrest is framework for writing matcher objects allowing 'match' results
     * to be defined declaratively
     *
     * It is an assertion library that can be used additionally to make assertion
     * readable and it comes with a lot of pre-written matchers to make it easier to write and read
     *
     * The method chain of RestAssured then section use the hamcrest matcher library
     * extensively to assert the response components in one chain
     *  RestAssured dependency already contains Hamcrest Assertion library
     *  so no separate dependency needed
     *   All we need is to add static imports and start using it's matchers
     *   to make the assertions great again
     */

    @Test
    public void testNumbers(){
        //in junit 5 assertions 3+6=9
        //import static org.junit.jupiter.api.Assertions.*;
        assertThat(9,is(3+6));

        //Hamcrest, it can be written in tis way
        //import static org.hamcrest.MatcherAssert.assertThat;
        // import static org.hamcrest.Matchers.*;
        assertThat( 3 + 6 ,  equalTo(9 ) ) ;

        /**
         * equalTo(some value)
         *         is(some value)
         *         is(equalTo(some value) //just for readability
         */

        assertThat(4+6,is(10));

        /**
         * number comparision:
         * greaterThan() lessThan()
         */

        assertThat(3+2,greaterThan(4));

        assertThat(10+10,lessThanOrEqualTo(20));

    }

    @Test
    public void testString(){

        String msg ="B23 is excellent";
        String msg2=" B23 loves API";

        assertThat(msg, is(msg) );

        assertThat(msg,startsWithIgnoringCase("b23"));

        assertThat(msg,containsStringIgnoringCase("is"));
        assertThat(msg, is(  not ("B24 is excellent") ) );

    }

    @Test
    public void testCollections(){

        List<Integer> numberLst = Arrays.asList( 3, 5, 1, 77, 44, 76 ) ;

        // check this list has size of 6
        assertThat(numberLst,hasSize(6));


        // check this list has item 77
        assertThat(numberLst,hasItem(77));

        // check this list has items 5, 44
        assertThat(numberLst,hasItems(5,44));

        // check this list every item is greatThan 0
        assertThat(numberLst,everyItem( greaterThan(0) ) );

        //check this list has items greater than 50
        assertThat(numberLst,hasItem(greaterThan(50) ) );

    }


    @Test
    public void testPractice(){

    /*
        //1st option
        Response response=given()
                .contentType(ContentType.JSON)
                .when().get("/employees/100");

     */



        //2nd option
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .when().get("/employees/100")
                //above request, below response
                .then().statusCode(200)
                .body("first_name", is("Steven"));

        //3rd option JsonPath

        JsonPath jsonPath= given()
                .log().all()
                .contentType(ContentType.JSON)
                .when().get("/employees/100").jsonPath();
                //above request, below response

        String name=jsonPath.getString("first_name");//Steven

        System.out.println("name = " + name);

        //


    }



}
