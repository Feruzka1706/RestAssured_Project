package com.cydeo.tests.homeworks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class MathTask {
    /**
     * ### Task last
     *
     * 1. Create a csv file called `math.csv` under `resource` folder
     * 2. add 3 columns `num1` ,   `num2` , `expectedResult`
     * 3. add valid data for addition num1 + num2 = expectedResult
     * 4. create a `@ParameterizedTest` with above `CsvFileSource` , skip the header
     * 5. assert addition num1 + num2 = expectedResult
     * 6. make sure to change the number and see failed tests.
     */

    @DisplayName("Adding 2 numbers testing")
    @ParameterizedTest
    @CsvFileSource(resources = "/math.csv",numLinesToSkip = 1)
    public void test1(int num1, int num2, int expectedResult){

        //assertEquals(expectedResult,num1+num2,"Num1 and num2 subtraction will be "+num1+num2);
        //just for practice purpose we are using hamcrest matchers assertions
        assertThat(num1+num2, equalTo(expectedResult));


    }





}
