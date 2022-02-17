package com.cydeo.tests.day09;

import com.cydeo.utility.DB_Util;
import com.cydeo.utility.LibraryAPI_BaseTest;
import com.cydeo.utility.LibraryAPI_Util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class Library_API_DB_Test extends LibraryAPI_BaseTest {


    @Test
    public void testDB(){

        DB_Util.runQuery("Select count(*) from books");
        //DB_Util.displayAllData();


    }

    @DisplayName("Test GET/Dashboard response match database")
    @Test
    public void testDashboardData(){

        DB_Util.runQuery("Select count(*) from books");
       // DB_Util.displayAllData();
        String expectedBookCount=DB_Util.getCellValue(1,1);

        DB_Util.runQuery("select count(*) from users");
        DB_Util.displayAllData();
        String expectedUsersCount=DB_Util.getCellValue(1,1);


        DB_Util.runQuery("select count(*) from book_borrow where is_returned=0");
        DB_Util.displayAllData();
        String expectedBorrowedBookCount=DB_Util.getCellValue(1,1);


        //GET /dashboard_stats
        given()
                .log().all()
                .header("x-library-token", LibraryAPI_Util.getToken() )
                .when()
                .get("/dashboard_stats")
                .then()
                .statusCode(200)
                .log().all()
                .body("book_count",is(expectedBookCount))
                .body("borrowed_books",is(expectedBorrowedBookCount))
                .body("users",is(expectedUsersCount));



    }

    // create a method to return Map<String,String> to return above data


}
