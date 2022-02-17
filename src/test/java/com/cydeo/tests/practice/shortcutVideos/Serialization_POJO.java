package com.cydeo.tests.practice.shortcutVideos;

import com.cydeo.tests.practice.SpartanPOJO;
import com.cydeo.utility.SpartanTestBase;
import com.cydeo.utility.SpartanUtil;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;


public class Serialization_POJO extends SpartanTestBase {


    @Test
    public void testSerialization_POJO(){

        //Serialization Java Object >>> Json Body

        Gson gson =new Gson();

        SpartanPOJO spartan1=new SpartanPOJO(106,"Feruza","Female",291020373829L);

        String jsonBody= gson.toJson(SpartanUtil.getRandomSpartanMapBody());
        System.out.println("jsonBody = " + jsonBody);


    }
}
