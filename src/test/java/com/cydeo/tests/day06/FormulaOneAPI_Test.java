package com.cydeo.tests.day06;

import com.cydeo.pojo.DriverPOJO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;

import java.util.List;

public class FormulaOneAPI_Test {

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
     * Our objective is to practice json path to find the values in json result
     * also practice deserialization by converting single driver json to POJO
     * practice converting driver json array in to List<Driver>
     *
     *
     */

    @BeforeAll
    public static void init(){
        baseURI = "http://ergast.com" ;
       basePath = "/api/f1" ;

    }
    @AfterAll
    public static void cleanup(){
        reset();
    }

    @Test
    public void testDriverDeserialization(){

      JsonPath jsonPath=get("/drivers.json")
              .jsonPath();

      //first driver json path : "MRData.DriverTable.Drivers[0]"
        DriverPOJO firstDriverJsonObject=jsonPath.
                getObject("MRData.DriverTable.Drivers[0]",DriverPOJO.class);

        System.out.println("firstDriverJsonObject = " + firstDriverJsonObject);

        List<DriverPOJO> allDriverJsonObjects=jsonPath.
                getList("MRData.DriverTable.Drivers",DriverPOJO.class);

        // Homework :
        // Find out all driver name if their nationality is Italian from above List
        int amountOfItalians=0;

        for (DriverPOJO eachDriverObject : allDriverJsonObjects) {

            if(eachDriverObject.getNationality().equalsIgnoreCase("Italian")){
                System.out.println("Driver nationality is: " + eachDriverObject.getNationality());
                amountOfItalians++;
            }

        }

        System.out.println("Total amount of Italian Drivers = "+amountOfItalians);
       // System.out.println("allDriverJsonObjects = " + allDriverJsonObjects);


    }
}
