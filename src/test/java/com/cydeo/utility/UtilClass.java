package com.cydeo.utility;

import io.restassured.RestAssured;

public class UtilClass {

    /**
     * @return
     * Creating utility method which returns randomly  id from response
    */
    public static int getRandomForDriversId(){

        int idNum=SpartanUtil.getRandomNumber(RestAssured.get("/drivers").path("id[0]"),
                RestAssured.get("/drivers").path("id[-1]"));

        return idNum;
    }


}
