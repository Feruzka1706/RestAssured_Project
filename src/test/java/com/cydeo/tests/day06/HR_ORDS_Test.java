package com.cydeo.tests.day06;

import com.cydeo.pojo.CarPOJO;
import com.cydeo.pojo.Job2_Pojo;
import com.cydeo.pojo.Job_POJO;
import com.cydeo.utility.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class HR_ORDS_Test extends HRTestBase {

    //GET /jobs
    @Test
    public void testJobs(){

        JsonPath jsonPath= given()
                .log().uri()
                .when()
                .get("/jobs")
                .jsonPath();


        //we want to de-serialize first json object from json array
        //we want to be able to follow Java naming convention for POJO fields
        //we want to ignore the json field we do not care about: LINK fields

        Job_POJO job_pojo=jsonPath.getObject("items[0]",Job_POJO.class);
        System.out.println("job_pojo = " + job_pojo);

        //save all result into List<Job_POJO>
        List<Job_POJO> allJobs=jsonPath.getList("items",Job_POJO.class);
        System.out.println("allJobs = " + allJobs);

        Job2_Pojo job2_pojo= jsonPath.getObject("items[0]",Job2_Pojo.class);
        System.out.println("job2_pojo = " + job2_pojo);


        //AS A HOMEWORK:
        //find out all Jobs name with min_salary more than 5000
        for (Job_POJO eachJob : allJobs) {
            if(eachJob.getMinSalary()>=5000){
                System.out.println(eachJob.getJobTitle()+" = $"+eachJob.getMinSalary());
            }
        }

    }


    @Test
    public void testCarPOJO_Class(){

        CarPOJO car1=new CarPOJO("Model3","Tesla",2020,true);

        System.out.println("car1 = " + car1);

        car1.setModel("CyberTruck");
        System.out.println("car1.getModel() = " + car1.getModel());


    }


}
