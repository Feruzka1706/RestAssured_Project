package com.cydeo.tests.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class HR_ORDS_API_Test {


    @BeforeAll
    public static void setUp(){
        baseURI="http://52.87.222.1:1000";
        basePath="/ords/hr";
    }

    @AfterAll
    public static void teardown(){
        reset();
    }


    @Test
    public void testGetALlJobs(){

        /**
         * 1. Open a new TestClass called HR_ORDS_API_Test
         * 2. Create @BeforeAll @AfterAll methods for setting up and tearing down
         *    1. baseURI  = http://54.236.150.168:1000
         *    2. basePath = /ords/hr/
         * 3. Create a test called testGetAllJobs
         *    1. Send request to GET /jobs
         *    2. Save the response
         *    3. Check status code is 200
         *    4. Content type is json
         *    5. count value is 19
         *    6. save second job_id into String
         *    7. print 4th mix_salary and
         *    8. save all of the job_title into List<String>
         */

        Response response= given()
                          .accept(ContentType.JSON)
                          .log().all()
                          .when()
                          .get("/jobs");


               // response.prettyPrint();

        int countJobs=response.body().path("count");
        String secondJob_id=response.body().path("items.job_id[1]");
        System.out.println("secondJob_id = " + secondJob_id);

        int fourthMaxSalary=response.body().path("items.max_salary[3]");
        System.out.println("fourthMaxSalary = " + fourthMaxSalary);

        List<String> allJobs=response.body().path("items.job_title");
        System.out.println("allJobs = " + allJobs);


        assertEquals(200,response.statusCode());
        assertEquals(ContentType.JSON.toString(),response.contentType());
        assertEquals(19,countJobs);




    }

    @Test
    public void testJobsWithQueryParam(){
        /**
         * 4. Create a test called testJobsWithQueryParam
         *    1. Send request to GET /jobs?limit=5
         *    2. log everything about the request
         *    3. verify count value is 5
         *    4. verify the value of last job_title is AD_VP
         */
        Response response= given()
                .accept(ContentType.JSON)
                .queryParam("limit",5)
                .log().all()
                .when()
                .get("/jobs");

        response.prettyPrint();

       int count= response.body().path("count");
       assertEquals(5,count);

       String lastJobTitle=response.body().path("items.job_id["+--count+"]");
       assertEquals("AD_VP",lastJobTitle);


    }


    @Test
    public void testSingleJobWithPathParam(){
        /**
         * 5. create a test called `testSingleJobWithPathParam`
         *    1. Send request to `GET /jobs/AD_VP`
         *    2. log everything about the request
         *    3. verify response is json and `job_title` is `Administration Vice President`
         */

        Response response= given()
                .accept(ContentType.JSON)
                .pathParam("job_id","AD_VP")
                .log().all()
                .when()
                .get("/jobs/{job_id}")
                .prettyPeek();

       // response.prettyPrint();
        //response.prettyPeek();
        /**
         * There is one more way to print except prettyPrint()
         * -- prettyPeek() <<< it's same like prettyPrint() just will print the result from response payload
         * ONLY << it will return same Response object, so u can keep chaining the methods
         * It will also include status code and headers and additional information about response result
         */

        String jobTitle=response.body().path("job_title");
        assertEquals("Administration Vice President",jobTitle);
    }



}
