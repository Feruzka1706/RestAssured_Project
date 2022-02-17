package com.cydeo.tests.day06;

import com.cydeo.pojo.Character_POJO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharactersAPI_Test {
    @BeforeAll
    public static void setUp(){
        baseURI="https://breakingbadapi.com";
        basePath="/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }


    @Test
    public void testCharacters(){

        JsonPath jp=get("/characters").jsonPath();

        //get first character and de-serialize it into Character POJO
        Character_POJO character1=jp.getObject("[0]", Character_POJO.class);

        //System.out.println("character1 = " + character1);

        List<Character_POJO> allCharacters=jp.getList("",Character_POJO.class);
       // System.out.println("allCharacters = " + allCharacters);


        for (Character_POJO eachCharacter : allCharacters) {

           // System.out.println("eachCharacter.getName() = " + eachCharacter.getName());
        }

        System.out.println("==========================================================");

        // Java Practice HOMEWORK :
        // find out the character names appearance count is exactly 1
        List<Integer> numbers=new ArrayList<>();


        for (Character_POJO eachCharacter : allCharacters) {

            if(eachCharacter.getAppearance().length==1 && eachCharacter.getAppearance()[0]==1){
                System.out.println("Name of the character: "+eachCharacter.getName()
                        +", and appearance is: "+ Arrays.toString(eachCharacter.getAppearance()));
            }
        }

        System.out.println("==========================================================");

        // find out the name of DEA Agent
        for (Character_POJO eachCharacter : allCharacters) {

            if(eachCharacter.getOccupation().contains("DEA Agent")){
                System.out.println("Name of the character: " + eachCharacter.getName()
                        +" "+eachCharacter.getOccupation());
            }

        }



    }


}
