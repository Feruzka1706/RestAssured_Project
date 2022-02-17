package com.cydeo.tests.day08;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

public class MethodSourcePracticeTest {
    //What if we want to provide more than ohe value
    //for each test in parametrized test
    //List<String> will only store 1 string per element
    //in order to have more than one value
    //we can use <List<Map<String,Object> >

    @ParameterizedTest(name = "Getting one Map {0}")
    @MethodSource("getAllStudentInfo")
    public void testStudentDDT(Map<String,Object> studentInfo){

        System.out.println("studentInfo = " + studentInfo);
    }



    //write a method that return <List<Map<String,Object> >
    // Map object should have key : name , gender, phone number
    public static List<Map<String,Object>> getAllStudentInfo(){

        List<Map<String,Object>> allInfoList=new ArrayList<>();

        //add 3 items to this list
        Map<String,Object> studentMap1=new LinkedHashMap<>();
        studentMap1.put("name","Mousa");
        studentMap1.put("gender","Male");
        studentMap1.put("phone",12839302028l);

        Map<String,Object> studentMap2=new LinkedHashMap<>();
        studentMap2.put("name","Mucahit");
        studentMap2.put("gender","Male");
        studentMap2.put("phone",23738191020L);

        Map<String,Object> studentMap3=new LinkedHashMap<>();
        studentMap3.put("name","Nazli");
        studentMap3.put("gender","Female");
        studentMap3.put("phone",2374922021L);

        Map<String,Object> studentMap4=new LinkedHashMap<>();
        studentMap4.put("name","Sophie");
        studentMap4.put("gender","Female");
        studentMap4.put("phone",27183940202L);

        allInfoList.addAll(Arrays.asList(studentMap1,studentMap2,studentMap3,studentMap4));
        return allInfoList;

    }


}
