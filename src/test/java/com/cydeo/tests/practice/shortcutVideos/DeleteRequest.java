package com.cydeo.tests.practice.shortcutVideos;

import com.cydeo.utility.SpartanTestBase;
import com.cydeo.utility.SpartanUtil;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class DeleteRequest extends SpartanTestBase {


    @Test
    public void testWithDeleteMethod(){

        int idNum= SpartanUtil.getRandomId();

        given()
                .log().all()
                .pathParam("id",idNum)
                .when()
                .delete("spartans/{id}")
                .then().assertThat().statusCode(204);

        //verify part is deleted
        given()
                .log().all()
                .pathParam("id",idNum)
                .when()
                .delete("spartans/{id}")
                .then().assertThat().statusCode(404);

    }


}
