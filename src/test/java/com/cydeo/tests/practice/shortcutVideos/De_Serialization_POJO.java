package com.cydeo.tests.practice.shortcutVideos;

import com.cydeo.pojo.SpartanWithID;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class De_Serialization_POJO extends SpartanTestBase {

    @Test
    public void testDe_Serialization_POJO(){

        JsonPath jsonPath=given()
                .contentType(ContentType.JSON)
                .pathParam("id",1)
                .when().get("/spartans/{id}").jsonPath();
        //jsonPath.prettyPeek();

        SpartanWithID firstSpartan=jsonPath.getObject("",SpartanWithID.class);
        System.out.println("firstSpartan = " + firstSpartan);

        //verify id is equal to 1
        assertEquals(1,firstSpartan.getId());

        //verify name is "Maxwell"
        assertEquals("Maxwell",firstSpartan.getName());

        //verify phone number is 8903568478
        assertEquals(8903568478L,firstSpartan.getPhone());

    }


    @Test
    public void test2(){

        JsonPath jsonPath=given()
                .contentType(ContentType.JSON)
                .when()
                .get("/spartans").jsonPath();


        List<SpartanWithID> allSpartansInfo=jsonPath.getList("",SpartanWithID.class);
        for (SpartanWithID eachSpartan : allSpartansInfo) {

            System.out.println("eachSpartan = " + eachSpartan);
        }




    }






}
