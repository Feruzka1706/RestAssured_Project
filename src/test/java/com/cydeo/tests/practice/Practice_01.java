package com.cydeo.tests.practice;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Practice_01 {

   /* @BeforeAll
    public static void setUp(){
        RestAssured.baseURI ="http://52.87.222.1:8000";
        RestAssured.basePath="";
    }

    */
    //declaring the base URL global level so inside each test methods we can access it
    String baseURL="http://52.87.222.1:8000";

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @Test
    public void testOne(){
       /* Response response=get(baseURL+"/api/spartans");
       // System.out.println(response.body().prettyPrint());
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getContentType() = " + response.getContentType());

        */

        Response response= given().
                        accept(ContentType.JSON).
                        when().
                        get(baseURL+"/api/spartans");

        //System.out.println(response.body().prettyPrint());

        assertEquals(200,response.statusCode());
        assertTrue(response.body().asString().contains("Nona")
                ||response.body().toString().contains("Meade"));

       assertEquals(ContentType.JSON.toString(),response.contentType());

    }



    //path Parameter
    @Test
    public void testPathParam(){
        /**
         * Given accept type is JSON
         * And Id parameter value is 500
         * When user sends GET request to /api/spartans/{id}
         * Then response status code should be 404
         * And response content-type: application/json;charset=UTF-8
         * And "Spartan Not Found" message should be in response payload(body)
         */

        Response response=given().
                          accept(ContentType.JSON).
                          and().pathParam("id",500).
                          when().
                          get(baseURL+"/api/spartans/{id}");

        response.body().prettyPrint();

        assertEquals(404,response.statusCode());
        assertTrue(ContentType.JSON.toString().contains(response.contentType()));
        assertTrue(response.body().asString().contains("Not Found"));
    }

    //query Parameter
    @Test
    public void testQueryParam(){
        Response response=given().
                queryParam("gender","Female")
                .queryParam("nameContains","Nona")
                .when()
                .get(baseURL+"/api/spartans/search");

        response.body().prettyPrint();

        assertFalse(response.body().toString().contains("Male"));


    }

    //query Parameter
    @Test
    public void testQueryParam2(){
        //api/spartans/search?nameContains=J&gender=Female
        Response response=given().
                accept(ContentType.JSON).
                queryParam("nameContains","Janette").
                queryParam("gender","Female").
                when().get(baseURL+"/api/spartans/search");

        assertEquals(200,response.statusCode());
        assertEquals(ContentType.JSON.toString(),response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertFalse(response.body().asString().contains("Male"));
        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();
    }

    @Test
    public void queryParamMap(){
        //creating map for query Params
        Map<String,Object> paramMap=new LinkedHashMap<>();
        paramMap.put("gender","Female");
        paramMap.put("nameContains","Janette");

        //send request
       Response response= given().
               accept(ContentType.JSON).
               and().queryParams(paramMap).
               when().get(baseURL+"/api/spartans/search");

       //verify status code is 200
        assertEquals(200,response.statusCode());

        //verify content type in payload (body) should be Json
        assertEquals(ContentType.JSON.toString(),response.contentType());
        //verify body has Female
        assertTrue(response.body().asString().contains("Female"));

        //verify body shouldn't have Male
        assertFalse(response.body().asString().contains("Male"));

        //verify body contains name Janette
        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();

    }


    @Test
    public void testPath(){
        Response response=given().
                accept(ContentType.JSON).
                pathParam("id",10).
                get(baseURL+"/api/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());


        System.out.println("Id :"+response.body().path("id").toString());
        System.out.println("name :"+response.body().path("name").toString());
        System.out.println("gender :"+response.body().path("gender").toString());
        System.out.println("phone :"+response.body().path("phone").toString());

         int idNum=response.body().path("id");
         String name=response.body().path("name");
         String gender =response.body().path("gender");
         long phoneNum=response.body().path("phone");

         assertEquals(10,idNum);
        assertEquals("Lorenza",name);
        assertEquals("Female",gender);
        assertEquals(3312820936L,phoneNum);


    }

    @Test
    public void TestPath2(){
        Response response= get(baseURL+"/api/spartans");

        //get first id from body
        int firstId=response.body().path("id[0]");//it will return first id from index number
        System.out.println("firstId = " + firstId);
        String firstName=response.body().path("name[0]");
        System.out.println("firstName = " + firstName);

        //gert the last first name from body
        String lastFirstName=response.body().path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);

        String firstGender=response.body().path("gender[0]");
        long firstPhone =response.body().path("phone[0]");

        //print all first names from body
       List<String> allNames= response.body().path("name");
        System.out.println("allNames = " + allNames);
        System.out.println("allNames.size() = " + allNames.size());

    }


    @Test
    public void testJSONPath(){
        /**
         * Given accept type is JSON
         * And path parameter spartan id is 11
         * When user sends a get request to /spartans/{id}
         * Then status code is 200
         * And content type is JSON
         * And   "id": 11,
         *        "name": "Nona",
         *        "gender": "Female",
         *        "phone": 7959094216
         */
        Response response=given().
                         accept(ContentType.JSON).
                         pathParam("id",11).
                         get(baseURL+"/api/spartans/{id}");

        assertEquals(200,response.getStatusCode());


        //how to read value with path() method
        int idNum=response.body().path("id");
        System.out.println("idNum = " + idNum);

        //how to read value with Jsonpath ?
        JsonPath jsonData=response.jsonPath();
        int idNum2=jsonData.getInt("id");
        String name=jsonData.getString("name");
        String gender=jsonData.getString("gender");
        long phoneNum=jsonData.getLong("phone");

        System.out.println("idNum2 = " + idNum2);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phoneNum = " + phoneNum);


        assertEquals(11,idNum2);
        assertEquals("Nona",name);
        assertEquals("Female",gender);
        assertEquals(7959094216L,phoneNum);



    }


}
