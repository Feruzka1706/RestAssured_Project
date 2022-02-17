package com.cydeo.tests.day05;

import com.cydeo.pojo.SpartanWithID;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class ResAssuredJsonPathTest extends SpartanTestBase {

    /**
     * There are two ways to get the response and extract json data
     *
     * 1>> path("your jsonPath goes here") return type is T(generic)
     * and decided by your variable data type you store
     * int myId=response.path("id")
     *
     * 2>> There is a type(class) in  RestAssured: JsonPath
     * that have lots of methods to extract json body from the response
     * like getInt() , getString(), getDouble(), getObject() and so on....
     *
     * In order to get JosnPath object out of response,
     * we call a method called jsonPath() from the response
     *
     * For example:
     *  JsonPath jp= response.jsonPath("your actual path goes here")
     *
     *  jp.getInt() , jp.getString() and so on
     *
     *  The meaning of the word json path when we use in different places
     *  json path:  -->> when inside " " means the actual path to get the value (like path)
     *
     *  JsonPath : -->> the RestAssured class that have lots of methods
     *  then, jsonPath() : -->> the method of Response object to obtain JsonPath object out of response
     */

    //send Request to GET /spartans/{id}
    //and extract the data  id, name, phone
    @Test
    public void testOneSpartan(){
        //let's get first "id"  exist in our system, so we do not have to deal with data issue
        //send request to get all data and grab the first id
        int firstId=get("/spartans").path("id[0]");
       //System.out.println("firstId = " + firstId);


        //send Request to GET /spartans/{id}
      Response response= given()
                .log().uri() //only log the request url by choice
                .pathParam("id",firstId)
                .when()
                .get("/spartans/{id}")
              .prettyPeek();


//      save the id from the response
//       int myId=response.path("id");

       //Get JsonPath object out of response object
        JsonPath jsonPath=response.jsonPath();
        int myId = jsonPath.getInt("id");
        System.out.println("myId = " + myId);

        String myName= jsonPath.getString("name");
        System.out.println("myName = " + myName);

        long myPhone=jsonPath.getLong("phone");
        System.out.println("myPhone = " + myPhone);

        /*
        {
          "id": 1,
          "name": "Meade",
          "gender": "Male",
          "phone": 3584128232
         }

         */

        // store this json result into a Map object
        // the path to get the entire body is empty string because
        // the entire response is json object already!
        // no need for a path to navigate to this json
        // so this method will create a map object
        // and add all the key of json as key and all value of json as value
        // the return that map object
      Map<String,Object> responseBodyMap= jsonPath.getMap("");
        System.out.println("responseBodyMap = " + responseBodyMap);

        //how to access phone number field out of this Map
        System.out.println("responseBodyMap.get(\"phone\") = " + responseBodyMap.get("phone"));


    }

    @Test
    public void testPracticeWithJsonPath(){

        // Send Request GET /spartans/search?nameContains=Ea&gender=Male
        // get JsonPath object out of response so you can use specialized methods
        // get totalElement field value using getX method
        // get 3rd element phone using getX method
        // get last element name using getX method
        // save first json in json array into Map using getX method
        // remember getX("your path to the element goes here just like xpath")

        Response response =given()
                .log().uri()
                .queryParam("nameContains","Ea")
                .queryParam("gender","Male")
                .contentType(ContentType.JSON)
                .when()
                .get("/spartans/search").prettyPeek();

        JsonPath jsonPath=response.jsonPath();

        int total=jsonPath.getInt("totalElement");
        System.out.println("total = " + total);

        long thirdPhone=jsonPath.getLong("content.phone[1]");
        System.out.println("thirdPhone = " + thirdPhone);

        String lastName=jsonPath.getString("content.name[-1]");
        System.out.println("lastName = " + lastName);

         Map<String,Object> lastObject=jsonPath.getMap("content[0]");
        System.out.println("lastObject = " + lastObject);

       // List<String> allNames= jsonPath.getList("content.name");
        List<String> allNames=jsonPath.getList("content.name",String.class);
                System.out.println("allNames = " + allNames);

        //save all phone numbers into the list and make it obvious what kind of list you want to get
        List<Long> allPhones =jsonPath.getList("content.phone",Long.class);

        //continue from this task: now try to match this json Object into POJO
        //in order to match  json result with 4 fields , you need to have POJO class with 4 matching fields
        //create a class called SpartanWithID
        //add no arg constructor(no need for 4 args constructor because we don't create object ourselves
        //optionally add to String() method, so we can print it worked
        //now we can use ijp.getObject("the path here",SpartanWithID.class)
        //to save it into  SpartanWithID object  --> another de-serialization!

        //store first json in the result as SpartanWithID POJO : content[0]
        //getobject method accept jsonPath to the jsonObject to be converted
        //and the class type you want to convert to and return object of that type
        //with all the field value already filled up by matching the key value

        SpartanWithID spartanObjectId=jsonPath.getObject("content[0]",SpartanWithID.class);
        System.out.println("spartanObjectId = " + spartanObjectId);

        SpartanWithID spartanWithID1 =new SpartanWithID();
        spartanWithID1.setName(jsonPath.getString("content.name[0]"));
       // System.out.println("spartanWithID1.getName() = " + spartanWithID1.getName());

        //store the entire json array, into List<SpartanWithId>
        List<SpartanWithID> resultList= jsonPath.getList("content",SpartanWithID.class);
       // System.out.println("resultList = " + resultList);


    }


}
