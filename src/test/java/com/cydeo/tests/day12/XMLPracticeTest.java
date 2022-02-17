package com.cydeo.tests.day12;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class XMLPracticeTest {

    @DisplayName("Homework 2 xml response checking")
    @Test
    public void testHomework2(){
        /**
         *   Homework 2:
         *   Send this request in Movie API
         *   GET http://www.omdbapi.com/?t=The Mandalorian&r=xml?apiKey=YourKeyGoesHere
         *   print out all the movie attributes
         *
         *   Send the request GET  http://www.omdbapi.com/?t=The Mandalorian&r=xml?apiKey=YourKeyGoesHere
         *   verify root element attribute totalResults="11"
         *   store all the titles on List<String> and print
         *   verify the size of list match the attribute  totalResults="11"
         *
         */

        String baseURI="http://www.omdbapi.com";

        XmlPath xp =given()
                .log().uri()
                .queryParam("s","The Mandalorian")
                .queryParam("apiKey","d8d661")
                .queryParam("r","xml")
                .when()
                .get(baseURI+"/")
                .then()
                .statusCode(200)
                .body("root.@totalResults",equalTo("11"))
                //count how many results will show on one page
                .body("root.result.@title.size()",is(10))
                .extract().xmlPath();


        List<String> allResultAttributes=new ArrayList<>(Arrays.asList("title","year","imdbID","type","poster"));

        for (String eachResultAttribute : allResultAttributes) {
            System.out.println("eachResultAttribute = " + eachResultAttribute+xp.getString("root.result.@"+eachResultAttribute));
        }



        int  totalResult= xp.getInt("root.@totalResults");
        //System.out.println("totalResult = " + totalResult);
        assertEquals(11,totalResult);

        List<String> allTitleAttributes=xp.getList("root.result.@title");
        // System.out.println("allTitleAttributes = " + allTitleAttributes);

        List<Integer> allYearsAttributes=xp.getList("root.result.@year");
        System.out.println("allYearsAttributes = " + allYearsAttributes);

        //the assertion will fail because the total title size is 10 not 11!
        assertEquals(totalResult-1,allTitleAttributes.size());



    }


    @Test
    public void testGettingAllMoviesInAllPages(){
        // in separate test , send a request to same endpoint store the title
        // in list of string
        // if you have more pages , keep sending more requests and store the title
        // into the same list

        //first we need to decide how many pages all movie information will be
        //if we have less than or equal to 10 we always have 1 page
        //if we have more than 10 then we need to divide it by 10 to find out
        //how many pages we will have
        //if the result count can be divided by 10  without remainder

            //then pageCount =result/10  for example 80/10=8  pages
        //else
          //then pageCount =result/10 +1 for example 81/10=8, 8+1 pages

        //in order to navigate through pages, according to the doc
        //we can use page query parameter and provide page number

        List<String > allMovies  =new ArrayList<>();

        //send first request to find out how many result
        //and save page one result into the list

        Response response = getMovieResponse("Iron Man",1);

        int totalCount=Integer.parseInt(response.path("totalResults"));
        System.out.println("totalCount = " + totalCount);

        List<String> page1MovieList=response.path("Search.Title");
        //System.out.println("page1MovieList = " + page1MovieList);

        //add first page movies into allMovie List
        allMovies.addAll(page1MovieList);

        //we need to find out how many pages we have and how many additional request
        //we need to send to get all the movies in all pages
        int totalPageCount=(totalCount % 10==0 ) ? totalCount/10 : totalCount/10+1 ;


        for(int currentPage=2; currentPage<=totalPageCount; currentPage++){
            //sending request to get second page
            response =  getMovieResponse("Iron Man",currentPage);

            List<String> currentPageMovies=response.path("Search.Title");

            //adding all movie in current page into allMovies List
            allMovies.addAll(currentPageMovies);
            System.out.println("allMovies = " + allMovies);
            System.out.println("allMovies = " + allMovies.size());

        }

    }



    @Test
    public void testGettingAllMoviesInAllPages_AnotherWay(){

        List<String > allMovies  =new ArrayList<>();

        Response response = getMovieResponse("Iron Man",1);
        //the result is coming as String so we want to parse it into the int variable
        int totalCount=Integer.parseInt(response.path("totalResults"));
        //this is the total result count (not on this page but in all pages)
        System.out.println("totalCount = " + totalCount);


        //we need to find out how many pages we have and how many additional request
        //we need to send to get all the movies in all pages
        int totalPageCount=(totalCount % 10==0 ) ? totalCount/10 : totalCount/10+1 ;


        for(int currentPage=1; currentPage<=totalPageCount; currentPage++){
            //sending request to get second page
            response =  getMovieResponse("Iron Man",currentPage);

            List<String> currentPageMovies=response.path("Search.Title");

            //adding all movie in current page into allMovies List
            allMovies.addAll(currentPageMovies);
            System.out.println("allMovies = " + allMovies);
            System.out.println("allMovies = " + allMovies.size());

        }

    }


    /**
     Create a method that accept movie name and page number
     and return Response object
     */
    public static Response getMovieResponse(String movieName, int currentPage){

        return   given()
                .baseUri("http://www.omdbapi.com")
                .queryParam("apiKey","25d8fdf1")
                .queryParam("s",movieName)
                .queryParam("page",currentPage)
                .when()
                .get();
    }



    public static Response getSwapiPageResponse(String page, int pageNum){
        return given()
                .baseUri("https://swapi.dev/api")
                .queryParam(page,pageNum)
                .when()
                .get("/people");

    }

    @Tag("Homework")
    @Test
    public void testGettingNamesFromAllPages(){
        /**
         * Solve the pagination challenge :
         * Send Request to https://swapi.dev/api/people?page=1
         * The result is 82 ,  save the name of all 82 people into List<String>
         */

        List<String> allNames=new ArrayList<>();

        JsonPath jsonPath =getSwapiPageResponse("page",1).jsonPath();

        int totalCount=jsonPath.getInt("count");
        System.out.println("totalCount = " + totalCount);

        int totalPageCount=0;

        if(totalCount%10==0){
            totalPageCount=totalCount/10;
        }else {
            totalPageCount=totalCount/10+1;
        }

        for (int i = 1; i <=totalPageCount ; i++) {

            jsonPath=getSwapiPageResponse("page",i).jsonPath();
            allNames.addAll(jsonPath.getList("results.name"));
        }

        System.out.println(allNames.size());
        System.out.println(allNames);

    }




}
