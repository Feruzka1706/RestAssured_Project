package com.cydeo.utility;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class LibraryAPI_Util {

    //Create method to get token
    public static String getToken(){

       return given()
                .contentType(ContentType.URLENC)
                .formParam("email", "librarian52@library")
                .formParam("password","Sdet2022*").
                when()
                .post("/login").path("token");



    }


    public static String getToken(String userType){
        String email;

        switch (userType){

            case "student":
                email="student42@library";
               break;
            case "librarian":
                email="librarian47@library";
              break;

            default:
                throw new RuntimeException("Unexpected value "+userType);
        }


        return given()
                .contentType(ContentType.URLENC)
                .formParam("email", email)
                .formParam("password","Sdet2022*").
                when()
                .post("/login").path("token");

    }


    //Create a method to generate
    //random book map for form parameter body
    public static Map<String,Object> getRandomBookMap(){

        Faker faker =new Faker();

        Map<String,Object> bookMap = new LinkedHashMap<>();
        bookMap.put("name",faker.book().title());
        bookMap.put("isbn",faker.code().isbn10());
        bookMap.put("year",faker.number().numberBetween(1000,2021));
        bookMap.put("author",faker.book().author());
        bookMap.put("book_category_id",faker.number().numberBetween(1,20));
        bookMap.put("description",faker.chuckNorris().fact());


        return bookMap;
    }




}
