package com.cydeo.tests.homeworks;

import com.cydeo.utility.MovieTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
public class HomeworkMovieAPITest extends MovieTestBase {

    @Test
    public void test1(){
        JsonPath jsonPath=given()
                .log().all()
                .contentType(ContentType.JSON)
                .queryParam("apiKey","d8d661")
                .queryParam("s","The Mandalorian")
                .when()
                .get("").prettyPeek().jsonPath();


       //Map<String,String> jsonBody=jsonPath.getJsonObject("");
        //System.out.println("jsonBody = " + jsonBody);


        //match the java filed to json field
        //and save the first one as Movie  POJO
       /* MoviePOJO firstMovieObject=new MoviePOJO();
        firstMovieObject.setTitle(jsonPath.getString("Search[0].Title"));
        firstMovieObject.setYear(jsonPath.getString("Search[0].Year"));
        firstMovieObject.setImdbID(jsonPath.get("Search[0].imdbID"));
        firstMovieObject.setType(jsonPath.getString("Search[0].Type"));
        firstMovieObject.setPoster(jsonPath.getString("Search[0].Poster"));

        */

        MoviePOJO firstMovieObject=jsonPath.getObject("Search[0]",MoviePOJO.class);
        System.out.println("firstMovieObject = " + firstMovieObject);


        System.out.println("=====================================================");

       //save all of them as list<POJO>
        List<MoviePOJO> allMovieObjects= jsonPath.getList("Search");
       System.out.println("allObjects = " + allMovieObjects);


    }

}
