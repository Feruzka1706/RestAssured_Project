package com.cydeo.tests.day07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JUnit5DataDrivenTest {


    // Given these String data:
    // "Furkan", "Abbos", "Yevheniia", "Shazia", "Tugba", "Mohamed", "Kimberley"
    //write a data driven test
    //to check the length of these names all more than 5 characters

    @ParameterizedTest
    @ValueSource(strings = {"Furkan", "Abbos S", "Yevheniia", "Shazia", "Tugba A", "Mohamed", "Kimberley"})
    public void testLengthOfNames(String name){

        assertTrue(name.length()>5,"The name wasn't longer than 5 characters, name: "+name);

    }




    /**
     * Junit5 have one data source known as @CsvFileSource where you can provide the path of external csv file that exists in src/test/resources folder and get the data from there.
     *
     * CSV File is a file that contains comma separated value
     *
     * It can be opened by Excel reading software, however it's not excel file and it's much ligher because it's plain text.
     * It can be easily consumed by many library.
     * In Junit 5, all we have to do to data drive the test according to the rows , we can point the
     * @CSVFileSource to the file under src/test/resources folder for easy recogniztion.
     *
     * It looks as below.
     * Optionally First row can be seen as column name , the rest of the row can be seen as actual data
     *
     * name, gender , phone
     * Jon , Male , 123123123
     * Mary, Female , 123123333
     * Bla , Male , 735273545
     *
     *
     * Create a resources folder under src/test package if you do not already have it.
     *
     * Right click and select
     * new -> file - > give a name  people.csv
     * and add above content and save
     *
     */

    @ParameterizedTest
    @CsvFileSource(resources= "/people.csv" , numLinesToSkip =1)//our first line is header and we are skipping header here
    public void testPerson(String name, String gender, long phone){
        // each iteration we have access to 3 data that exist in per row in csv file
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);


    }

    /**
     * Another way of providing source for @ParametrizedTest is @MethodSource
     * It will allow you to provide the returned value from the static method with no param
     * as the source for your parametrized tes
     */

    @DisplayName("Method Source Testing")
    @ParameterizedTest(name = "Bla Bla")
    @MethodSource("get10NumbersList")
    public void testWithMethodSource(int num){
        System.out.println("num = " + num);

    }

    //write a static method that return List<Integer> that contains 10 numbers
    public static List<Integer> get10NumbersList(){

        //This list could have been the result of very long code here
        return new ArrayList<>(Arrays.asList(11,2,34,45,15,6,37,48,59,10));
    }

    //what if the method is not in same class?
    //we provide full.package .path#staticMethodName
    @ParameterizedTest
    @MethodSource("com.cydeo.day08.MethodSourceUtil#getSomeNames")
    public void testNamesWithMethodSourceDDT(String name){

        System.out.println("name = " + name);
    }


}
