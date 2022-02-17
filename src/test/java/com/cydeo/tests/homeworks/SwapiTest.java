package com.cydeo.tests.homeworks;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class SwapiTest {

    @BeforeAll
    public static void setUp(){
        baseURI="https://swapi.dev/api";

    }

    @AfterAll
    public static void tearDown(){
       reset();
    }

    @Test
    public void test1(){
        /**
         * 1. Send request to GET https://swapi.dev/api/people
         *     - Verify status code is 200
         *     - header Content-Type is application/json
         *     - in json body ,
         *       - count field value is 82
         *       - name of first person in the result is Luke Skywalker
         *       - hair_color of fourth person in the result is none
         *       - birth_year of last person is 57BBY
         *       - save the name of all characters in the result into List
         *       and verify the count is 10 (10 per page)
         *       - Optional Java Practice : Save the height of all people from the result
         *       and find out the max height.
         */

        Response response =given().
                          accept(ContentType.JSON)
                          .when().log().all()
                .get(baseURI+"/people");

        response.prettyPrint();

       //converting to JsonPath to read easily later on
        JsonPath jsonPath=response.jsonPath();

        //getting actual count from response payload
        int actualCount=jsonPath.getInt("count");

        //getting actualName from json payload(body)
        String actualName=jsonPath.getString("results.name[0]");

        //getting actual hair_color of 4th person from response body
        String hairColorOfFourthPerson=jsonPath.getString("results[3].hair_color");

       //getting actual birth year of the last person from response payload
        String lastPersonBirthYear=jsonPath.getString("results[-1].birth_year");

        //save the name of all characters in the result into List
        List<String> allNames= jsonPath.getList("results.name");
        System.out.println("allNames = " + allNames);

        // Optional Java Practice : Save the height of all people from the result
        //and find out the max height.
        List<String> allHeights=jsonPath.getList("results.height");
        System.out.println("allHeights = " + allHeights);
        int maxHeight=0;

        for (String eachHeight : allHeights) {

            if(Integer.parseInt(eachHeight)>maxHeight){
                maxHeight=Integer.parseInt(eachHeight);
            }
        }
        //print the max height
        System.out.println("maxHeight = " + maxHeight);

        //- Verify status code is 200
        assertEquals(ContentType.JSON.toString(),response.contentType());

        //- header Content-Type is application/json
        assertEquals(ContentType.JSON.toString(),response.contentType());

        //in json body , - count field value is 82
        assertEquals(82,actualCount);

        //- name of first person in the result is Luke Skywalker
        assertEquals("Luke Skywalker",actualName);

        //- hair_color of fourth person in the result is none
        assertEquals("none",hairColorOfFourthPerson);

        //- birth_year of last person is 57BBY
        assertEquals("57BBY",lastPersonBirthYear);

        //- save the name of all characters in the result into List
        // and verify the count is 10 (10 per page)
        assertEquals(10,allNames.size());


    }

    @Test
    public void test2(){
        /**
         * 2.  in Swapi api , it return 10 result per page
         * and you can use page query param to define which page you want to go
         * for example , second page will be GET https://swapi.dev/api/people?page=2 .
         * - in separate test , use given() section to provide query param page value 2
         * -  print the name of 4th person.
         * -  verify the 9th person name is Yoda
         */

        Response response =given()
                .accept(ContentType.JSON)
                .queryParam("page",2)
                .when().log().all()
                .get("/people/");

        //response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();

        //-  print the name of 4th person.
        String nameOfFourthPerson=jsonPath.getString("results.name[3]");
        System.out.println("nameOfFourthPerson = " + nameOfFourthPerson);

        String nameOfNineThPerson=jsonPath.getString("results.name[8]");
        System.out.println("nameOfNineThPerson = " + nameOfNineThPerson);

        //-  print the name of 4th person.
        assertEquals("Han Solo",nameOfFourthPerson);

        //-  verify the 9th person name is Yoda
        assertEquals("Yoda",nameOfNineThPerson);


    }


    @Test
    public void test3(){
        /**
         *
         3. Send request to GET https://swapi.dev/api/films/3
         (3 in here is path parameter to get single film)
         - use path param in given section for film id
         - verify you get 200
         - verify response is json format
         - verify the name of film is "Return of the Jedi"
         - save characters field value into List<String> and verify count is 20
         */

        Response response=given()
                .accept(ContentType.JSON)
                .and().pathParam("id",3)
                .log().all()
                .when()
                .get("/films/{id}");

       // response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();

        String nameOfTheMovie=jsonPath.getString("title");
        System.out.println("nameOfTheMovie = " + nameOfTheMovie);

        List<String> allCharacters=jsonPath.getList("characters");
        System.out.println("allCharacters = " + allCharacters);


        // - verify you get 200
        assertEquals(200,response.statusCode());

        //- verify response is json format
        assertEquals(ContentType.JSON.toString(),response.contentType());

        //- verify the name of film is "Return of the Jedi"
        assertEquals("Return of the Jedi",nameOfTheMovie);

        //- save characters field value into List<String> and verify count is 20
        assertEquals(20,allCharacters.size());
    }





}
