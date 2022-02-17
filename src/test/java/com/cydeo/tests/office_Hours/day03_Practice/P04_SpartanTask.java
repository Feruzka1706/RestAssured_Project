package com.cydeo.tests.office_Hours.day03_Practice;

import com.cydeo.utility.SpartanTestBase;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class P04_SpartanTask extends SpartanTestBase {


    @Test
    public void task1(){

        /**
         * 4.get("api/spartans")
         *
         *   HomeWork 3 : Send request to  GET /spartans provide accept header to get XML response
         *          // find out second name
         *          // find out last name
         *          // find out all the male spartans name in one shot
         *          // find out phoneNumber who is name Abcd in one shot
         */

       XmlPath xp= given()
                .log().uri()
                .accept(ContentType.XML)
         .when()
                .get("/spartans")
         .then()
                .statusCode(200)
                .contentType(ContentType.XML).extract().xmlPath();

       String secondName=xp.getString("List.item.name[1]");
        System.out.println("secondName = " + secondName);
        String lastName=xp.getString("List.item.name[-1]");
        System.out.println("lastName = " + lastName);

       List<String> allItems=xp.getList("List.item");
        System.out.println("allItems.size() = " + allItems.size());
//       System.out.println("allItems = " + allItems);

        List<String> maleSpartanNames=xp.getList("List.item.findAll {it.gender=='Male'}.name");
        System.out.println("maleSpartanNames.size() = " + maleSpartanNames.size());
        System.out.println("maleSpartanNames = " + maleSpartanNames);

        List<String> allFemaleSpartans=xp.getList("List.item.findAll {it.gender=='Female'}.name");
        System.out.println("allFemaleSpartans.size() = " + allFemaleSpartans.size());
        System.out.println("allFemaleSpartans = " + allFemaleSpartans);

        List<String> allFemaleSpartansPhoneNum=xp.getList("List.item.findAll {it.gender=='Female'}.phone");
        System.out.println("allFemaleSpartansPhoneNum = " + allFemaleSpartansPhoneNum);

        String phoneNum=xp.getString("List.item.findAll {it.name=='Maxwell'}.phone");
        System.out.println("phoneNum = " + phoneNum);


    }

}
