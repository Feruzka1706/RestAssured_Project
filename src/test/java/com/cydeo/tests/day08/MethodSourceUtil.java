package com.cydeo.tests.day08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The only purpose of this class is
 * to demonstrate providing the method source
 * for a parametrized test and the method  exist outside of the Test class
 */
public class MethodSourceUtil {

    //create a static ,method that return list of names
    //so we can use the returned value as data source for our parametrized test

    public static List<String> getSomeNames(){

        return new ArrayList<>(Arrays.asList("Furkan", "Abbos", "Yevheniia" , "Shazia" ,"Tugba" , "Mohamed" , "Kimberley"));
    }

}
