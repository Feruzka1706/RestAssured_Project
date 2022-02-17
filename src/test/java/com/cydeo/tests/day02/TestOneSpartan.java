package com.cydeo.tests.day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;

public class TestOneSpartan {

    // GET http://52.87.222.1:8000/api/spartans/44

   /* @BeforeEach
    public static void sendingRequest(){
        Response response = get("http://52.87.222.1:8000/api/spartans/1");
    }

    */

    @Test
    public void  testOneSpartan(){
       //sending a get request to this url and saving the response into Response Object
        Response response = get("http://52.87.222.1:8000/api/spartans/1");
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
        Response response = get("http://52.87.222.1:8000/api/spartans/1");

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
        Response response = get("http://52.87.222.1:8000/api/spartans/1");
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



}
