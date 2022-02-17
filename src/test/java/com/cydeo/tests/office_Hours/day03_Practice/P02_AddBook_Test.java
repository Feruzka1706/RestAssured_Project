package com.cydeo.tests.office_Hours.day03_Practice;

import com.cydeo.utility.DB_Util;
import com.cydeo.utility.LibraryAPI_BaseTest;
import com.cydeo.utility.LibraryAPI_Util;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class P02_AddBook_Test extends LibraryAPI_BaseTest {

     /*

        Task 1 :  student can't add books.
                send a request as a student to create a new book.
                use the LibraryUtil( already exist in Utility ) to create the book
                verify content type json
                verify status code 403
                verify response body:
                {
                    "error": "Unauthorized Access",
                   "details": "/add_book"
               }

        Task 2 : librarian add books
              send a request as a librarian to create a new book
              verify status code 200
              verify content type json
              verify response contain "message": "The book has been created."

        Task 3 : librarian add books
              send a request as a librarian to create a new book
              verify status code 200
              extract the book_id
              get the information using the get_book_by_id endpoint.
              verify that book information is correct in the response payload
              verify that book information is correct in database
     */

    @Test
    public void task1() {

        given()
                .log().uri()
                .header("X-LIBRARY-TOKEN", LibraryAPI_Util.getToken("student"))
                .formParams(LibraryAPI_Util.getRandomBookMap())
                .when()
                .post("/add_book").prettyPeek()
                .then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("error", equalTo("Unauthorized Access"));

    }

    @Test
    public void task2(){

        given()
                .log().uri()
                .header("X-LIBRARY-TOKEN", LibraryAPI_Util.getToken("librarian"))
                .formParams(LibraryAPI_Util.getRandomBookMap())
                .when()
                .post("/add_book").prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("message", equalTo("The book has been created."));

    }



    @Test
    public void task3(){

        String book_id=given()
                .log().uri()
                .header("X-LIBRARY-TOKEN", LibraryAPI_Util.getToken("librarian"))
                .formParams(LibraryAPI_Util.getRandomBookMap())
                .when()
                .post("/add_book").prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("message", equalTo("The book has been created."))
                .extract().jsonPath().getString("book_id");

        System.out.println("book_id = " + book_id);


        //Getting data from API for same book

       Map<String,String> actualBookInfoMap=given()
                .log().uri()
                .header("X-LIBRARY-TOKEN", LibraryAPI_Util.getToken("librarian"))
                .pathParam("id",book_id)
                .when()
                .get("/get_book_by_id/{id}").prettyPeek().jsonPath().get("");

        System.out.println("actualBookInfoMap = " + actualBookInfoMap);

        //Getting data from DB for same book

        String query="select * from books where id="+book_id;
        DB_Util.runQuery(query);
        Map<String,String> expectedBookInfoMap=DB_Util.getRowMap(1);
        System.out.println("expectedBookInfoMap = " + expectedBookInfoMap);

        assertEquals(expectedBookInfoMap,actualBookInfoMap);


    }

}
