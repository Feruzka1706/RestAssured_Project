package com.cydeo.tests.homeworks;

import com.cydeo.utility.ConfigReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class WeatherHomeworkTest {

    @BeforeAll
    public static void setup(){
       // baseURI= ConfigReader.read("weather_url");

    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @Test
    public void test1(){
        String myToken="7a29ef404452a306f7d257f4dd9f98f5";

        Response response=given()
                .log().all()
                .queryParam("q","London")
                .queryParam("appid",myToken)
                .contentType(ContentType.JSON)
                .when()
                .get("/weather").prettyPeek();

        JsonPath jsonPath=response.jsonPath();

        double maxTemp=jsonPath.getDouble("main.temp_max");
        System.out.println("maxTemp = " + maxTemp);

        double minTemp=jsonPath.getDouble("main.temp_min");
        System.out.println("minTemp = " + minTemp);


    }
}
