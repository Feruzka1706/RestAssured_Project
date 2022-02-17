package com.cydeo.tests.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class TestOneSpartan2 {

    // GET http://52.87.222.1:8000/api/spartans/44
    // We can break down above url to 3 part to tell RestAssured to append at the end of our endpoints

    /**
     * BaseURI : http://52.87.222.1:8000
     * BasePath :/api
     * Anything comes after actual resources
     *
     * For RestAssured The whole URL will be BaseURL + BasePath + whatever u put in get("here")
     */
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI ="http://52.87.222.1:8000";
        RestAssured.basePath="/api";

    }

    @AfterAll
    public static void cleanUp(){
       //in order to avoid the static value accidentally carried over
       //to different class when we practice different api,
        //it's better if we set baseURI  basepath  back to it's original value using reset method
        reset();
    }

    @Test
    public void  testOneSpartan(){
       //sending a get request to this url and saving the response into Response Object
        Response response = get("/spartans/1");
        //print out the status code
       int statusCode= response.getStatusCode();
        System.out.println("statusCode = " + statusCode);
        response.prettyPrint();
       //String tableBody= response.prettyPrint();
        //System.out.println("tableBody = " + tableBody);

        //System.out.println("response.asString() = " + response.asString());
        System.out.println("response.header(\"Content-Type\") = "
                + response.header("Content-Type"));
        System.out.println("response.getHeader(\"Content-Type\") = "
                + response.getHeader("Content-Type"));

        System.out.println(response.getHeaders());

        System.out.println("response.getHeader(\"Date\") = " + response.getHeader("Date"));

        System.out.println("response.getHeader(\"Keep-Alive\") = " + response.getHeader("Keep-Alive"));

        System.out.println("response.getHeader(\"Connection\") = " + response.getHeader("Connection"));


    }


    @Test
    public void testContentTypeHeader(){

        //sending a get request to this url and saving the response into Response Object
        Response response = get("/spartans/1");

        //RestAssured has special support for common header like Content Type with method
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.getContentType() = " + response.getContentType());

        //write an assertion to verify contentType() = application/json
        Assertions.assertEquals("application/json",response.contentType());

        //Different type of content type is represented in ENUM coming from
        //import io.restassured.http.ContentType
        System.out.println("ContentType.JSON = " + ContentType.JSON);
        System.out.println("ContentType.XML = " + ContentType.XML);
        System.out.println("ContentType.HTML = " + ContentType.HTML);
        System.out.println("ContentType.TEXT = " + ContentType.TEXT);
        System.out.println("ContentType.URLENC = " + ContentType.URLENC);

        //now we can simply replace the string with Enum to avoid any types
        //ContentType.JSON return Enum, so we need to convert toString() before comparing the results
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());


    }

    @Test
    public void testJSONBody(){
        /**
         * {
         *     "id": 1,
         *     "name": "Meade",
         *     "gender": "Male",
         *     "phone": 3584128232
         * }
         */
        // sending a get request to this url and saving the response into Response object
        Response response = get("/spartans/1");
       //print out the body
        //response.prettyPrint();
        //just like navigating through html using xpath to get to certain element
        //you can navigate through json to get the value of certain key using jsonpath
        //the easiest way to get the value using jsonpath is using path() method from response object
        //System.out.println("response.getBody() = " + response.getBody());
        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println(response.path("id").toString());
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"name\") = " + response.path("gender"));
        System.out.println("response.path(\"name\") = " + response.path("phone"));

        //save json value u got from key into variables
        int idNum=response.path("id");
        String myNam=response.path("name");
        String myGender=response.path("gender");
        long phoneNum=response.path("phone");
        System.out.println("idNum = " + idNum);
        System.out.println("myNam = " + myNam);
        System.out.println("myGender = " + myGender);
        System.out.println("phoneNum = " + phoneNum);

        //write assertions to verify the json body
        Assertions.assertEquals(1,idNum);
        Assertions.assertEquals("Meade",myNam);
        Assertions.assertEquals("Male",myGender);
        Assertions.assertEquals(3584128232L,phoneNum);


    }


    //add a new test to check GET api/hello endpoint
    //verify status code is 200, content type is text/plain, body is Hello from Sparta (this is string not json)
    @Test
    public void testHelloAgain(){
        //the actual url being used BaseURI + basePath +/hello
        Response response=get("/hello");

        Assertions.assertEquals(200,response.getStatusCode());
        //below the assertions will not work because actual result has extra characters
       // Assertions.assertEquals(ContentType.TEXT.toString(),response.contentType() );
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType() );

        Assertions.assertEquals("Hello from Sparta", response.asString());
        //response.prettyPrint() << works same
    }



}
