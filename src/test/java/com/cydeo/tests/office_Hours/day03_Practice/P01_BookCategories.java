package com.cydeo.tests.office_Hours.day03_Practice;

import com.cydeo.utility.DB_Util;
import com.cydeo.utility.LibraryAPI_BaseTest;
import com.cydeo.utility.LibraryAPI_Util;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class P01_BookCategories extends LibraryAPI_BaseTest {

     /*
    1.  get("/get_book_categories")

        Task 1 : category types (don't use POJO)
             send a request as a librarian
             verify status code 200
             verify content type json
             verify each object in the response array contains id and name
             verify that book categories are same in database

        Task 2: category types
            repeat the same test but do it utilizing category pojo
     */

    @Test
    public void task1() {

         List<String> actualAllCategoriesFromAPI=  given()
                   .log().uri()
                   .header("X-LIBRARY-TOKEN", LibraryAPI_Util.getToken("librarian"))
                   .when()
                   .get("/get_book_categories").prettyPeek()
                   .then()
                   .statusCode(200)
                   .contentType(ContentType.JSON)
                   .body("id",everyItem(notNullValue()))
                   .extract().jsonPath().getList("name");

        System.out.println("allCategoriesFromAPI = " + actualAllCategoriesFromAPI);

        /*
        actual: always Database
        expected: from UI and from API

        api vs db -->
                db -- expected
                api -- actual

         ui vs db -->
                db -- expected
                ui -- actual

         requirement vs db -->
                requirement -- expected
                db -- actual

         */


        //Get book categories from DB

        DB_Util.createConnection();
        String query ="select name from book_categories";
        DB_Util.runQuery(query);

        //I will get one column named with name
        List<String> expectedBookCategories=DB_Util.getColumnDataAsList(1);
        System.out.println(expectedBookCategories);

        assertEquals(expectedBookCategories,actualAllCategoriesFromAPI);


    }

    @Test
    public void test2(){


    }

}
