package com.cydeo.tests.day01;


import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Homework_Day01 {

  @Test
  public void practiceTest(){
      String newsAPI="https://newsapi.org/v2/";
      String apiKey="&apiKey=e0188dafa3fb4698accd00fec60b2a95";
      String queryParam="everything?q=bitcoin";
      Response response =get(newsAPI+queryParam+apiKey);
      System.out.println("response.statusCode() = " + response.statusCode());

      //System.out.println(response.prettyPrint());
      System.out.println(response.getBody());
  }


}
