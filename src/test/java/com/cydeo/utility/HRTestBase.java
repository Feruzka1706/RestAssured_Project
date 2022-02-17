package com.cydeo.utility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class HRTestBase {

    /**
     * This class will serve as Base Class to set up BaseURI and BasePath
     * and clean up after all test for any HR ORDS related test classes.
     */

    // set up and teardown
    @BeforeAll
    public static void setup() {
      baseURI = "http://100.26.44.167:1000/" ;
      basePath = "ords/hr";

        DB_Util.createConnection();
    }

    @AfterAll
    public static void tearDown() {
        DB_Util.destroy();
        reset();
    }

}
