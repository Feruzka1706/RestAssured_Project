package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class LibraryAPI_BaseTest {

    @BeforeAll
    public static void setup(){
        baseURI= "https://library2.cybertekschool.com";
        basePath= "/rest/v1";

        //create the db connection here
        String url      = ConfigReader.read("library2.database.url") ;
        String username = ConfigReader.read("library2.database.username") ;
        String password = ConfigReader.read("library2.database.password") ;

        DB_Util.createConnection(url, username, password);


    }

    /**
     * A handy method to create library db connection by reading properties file
     */
 /*
    public static void createLibraryDBConnection(){

        String url      = ConfigReader.read("library2.database.url") ;
        String username = ConfigReader.read("library2.database.username") ;
        String password = ConfigReader.read("library2.database.password") ;

        DB_Util.createConnection(url, username, password);

    }

  */


    @AfterAll
    public static void tearDown() {

        reset();
        //destroy the db connection here
        DB_Util.destroy();
    }



}
