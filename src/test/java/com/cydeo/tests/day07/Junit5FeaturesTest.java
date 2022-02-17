package com.cydeo.tests.day07;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to explore more Junit5 features
 * Office doc : https://junit.org/junit5/docs/current/user-guide/#writing-tests
 * jUnit 5 have many annotations to either help to make test result simpler
 * or to defect the way that test run
 *
 * @DisplayName : Declares a custom display name for the test class or test method
 * @Disabled : to ignore running certain tests for any reason you might have
 *  it can be at class level or method level
 *  We can optionally add reason inside ("this is optional reason text")
 *  In order to see result (affect) run at class level
 *
 *  Soft Assertion in JUnit 5 using assertAll Method and
 *  provide multiple assertion in () -> assertEquals(. .)
 *
 *  all assertion methods has overloaded version that accept String message
 *  to provide addition information in the console when something fail.
 *
 * @ParametrizedTest Denotes that a method is a parametrized test
 * a test that annotated with @ParametrizedTest instead of @Test
 * will need to provide where to provide the data known as source
 * there are 4 type of sources supported by JUnit 5
 *
 *   - @valueSource
 *   - @CsvSource
 *   - @CsvFileSource
 *   - @MethodSource
 *
 */

@DisplayName("Explore JUnit5 features")
public class Junit5FeaturesTest {


    @ParameterizedTest
    @ValueSource(ints = {11,44,33,15,16,88} )
    public void testNumberMoreThan10(int num){

        // Assuming we got these numbers from
        // some complicated previous code here: 11,44,33,5,6,88
        //assert all these numbers are more than 10

        //int num = 11;
        System.out.println("Number in this iteration: "+num);
        assertTrue(num>10,"Comparing number: "+num+ " is not bigger than 10");

    }


    @DisplayName("Testing addition here")
    @Test
    public void testAdd(){

     assertEquals(10,5+5);

     //Providing additional error message when assertion fail
        assertEquals(10,5+5," Hey, It was not 10!!!");

        assertTrue(10>11," Salary was not greater than 130K" );

    }


    @Disabled("Ignored until defect 101 fixed")//we can optionally add reason message
    @DisplayName("Testing subtraction functionality")
    @Test
    public void testSubtract(){
        assertEquals(9,10-1);
    }


    @DisplayName("Assert All together then one result")
    @Test
    public void multipleAssertions(){

        //in this way, if second assertion fail, it will just stop after failing line assertion
        //and we cannot know the result of last assertions
        //and that how the code supposed to work by design
//        assertEquals(10,5+5);
//        assertEquals(111,5+6);
//        assertEquals(20,5+15);

        //but sometimes we want to assert all of them and see a final result
        //assertAll() method can perform it
       //it accepts one or more Executable
        //Executable is functional interface
        //and it can be easily created  using lambda expression
        //it has single method that accept no parameter and return nothing
        // so it can be in this way () -> the assertion you want to return
        assertAll(  ()->  assertEquals(11,5+5),
                    ()->   assertEquals(11,5+6),
                    ()->   assertEquals(20,5+15)
                );
        //this will enable us to continue to run the test of the assertion
        //in case previous assertion has failed
        //it's also known as SOFT ASSERTION in other term

    }





}
