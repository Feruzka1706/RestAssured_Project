package com.cydeo.tests.practice;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


public class SpartanPOJODeserizialation {


    @BeforeAll
    public static void setUp(){
        baseURI="http://52.87.222.1:8000";
        basePath="/api";
    }

    @AfterAll
    public static void teardown(){
        reset();
    }

    @Test
    public void test1(){
        Response response=given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/spartans/{id}");

        //response.prettyPrint();

        //how to convert json response to our Spartan POJO class

        SpartanPOJO spartan1 =response.body().as(SpartanPOJO.class);

        System.out.println(spartan1.toString());
        assertThat(spartan1.getName(),is("Meta"));
        Assertions.assertEquals(15,spartan1.getId());
        assertThat(spartan1.getGender(),equalTo("Female"));
        assertThat(spartan1.getPhone(),equalTo(1938695106L));


    }

    @Test
    public void gsonExample(){
        Gson gson =new Gson();

        String myJsonBody = "{\n" +
                "                    \"id\": 15,\n"+
                "                    \"name\":\"Meta\",\n" +
                "                    \"gender\":\"Female\",\n" +
                "                    \"phone\":1231231231\n" +
                "          }";

        SpartanPOJO spartanMeta =gson.fromJson(myJsonBody,SpartanPOJO.class);

        System.out.println(spartanMeta.toString());

        //serialization Java object --> JSON BODY

        SpartanPOJO spartan2=new SpartanPOJO(104,"Mike","Male",2391030282L);

        //converting custom class to json (serialization)
        String jsonBody=gson.toJson(spartan2);
        System.out.println("jsonBody = " + jsonBody);


    }


}
