package com.cydeo.tests.practice.shortcutVideos;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Path_Method extends SpartanTestBase {


    @Test
    public void testPathMethod(){

       Response response= given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id",10)
                .when()
                .get("/spartans/{id}");

        //response.prettyPrint();

       int myId= response.body().path("id");

       String myName =response.body().path("name");

       String gender =response.body().path("gender");

       long phoneNum=response.body().path("phone");

        assertEquals(200,response.statusCode());

        assertEquals(10,myId);

        assertEquals("Wendy",myName);
        assertEquals("Male",gender);
        assertEquals(8341091877L,phoneNum);

    }

    @Test
    public void testPathMethod2(){

       Response response= when().get("/spartans");

       //response.prettyPrint();

       int firstId=response.path("id[0]");

       String firstName=response.path("name[1]");

       long phoneNum=response.path("phone[1]");

       String lastName=response.path("name[-1]");

       assertEquals(1,firstId);
       assertEquals("Kuku Patch",firstName);
       assertEquals(12364758910L,phoneNum);

       assertEquals("Romelia",lastName);

       //extract all firstNames and print them
        List<String> allNames=response.path("name");
        System.out.println("allNames = " + allNames);

        for (String eachName : allNames) {

            if (eachName.contains("Feruza")){
                System.out.println("eachName = " + eachName);
            }
        }

        List<Object> allPhoneNums=response.path("phone");

        for (Object eachPhoneNum : allPhoneNums) {

            System.out.println("eachPhoneNum = " + eachPhoneNum);

        }


    }



}
