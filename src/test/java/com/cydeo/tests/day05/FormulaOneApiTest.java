package com.cydeo.tests.day05;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public class FormulaOneApiTest {

    /**
     * Here is the import link for whole collecgion
     * https://www.getpostman.com/collections/4ea3cf2262b1b19a6d29
     *
     * It's for historical formula one race information
     * In this particular api , it decided to give you ml by default for response type
     * and In this particular api , it decided to give you json if you add .json at the  end of url
     * for example
     * http://ergast.com/api/f1/drivers --->return xml
     * http://ergast.com/api/f1/drivers.json ---> return json
     *
     * Our objective is to practice json path to find the values in json resul
     * also practice deserialization by converting single driver json to POJO
     * practice vonverting driver json arrar in to List</Driver>
     */



    @BeforeAll
    public static void setUp(){
        baseURI="http://ergast.com/api";
        basePath="/api";
    }


    @AfterAll
    public static void tearDown(){
        reset();
    }



}
