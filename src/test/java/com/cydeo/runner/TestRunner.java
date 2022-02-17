package com.cydeo.runner;

import org.junit.platform.suite.api.*;

@Suite
@SuiteDisplayName("Smoke Test")
//@SelectPackages("com.cydeo.tests.day09")//just to run day08 package
//@SelectPackages({"com.cydeo.tests.day08","com.cydeo.tests.day01"} )// day01 and day08 we want to run
//@SelectClasses(BaseAuthTest.class) //I just want to run BaseAuthTest

@SelectPackages("com.cydeo.tests") //start looking from root location,
//then it will look anything with tag "smoke1"
@IncludeTags({"smoke1","tc1"}) //we can provide one tag or more tags here

//@IncludeTags("MethodSource")//this is class level tag
//@ExcludeTags("smoke2") //it will exclude this tag

/**
 * Make sure the TEST CLASSES you select have followed naming convention
 * ClassName should be somethingTEST
 * methodName should be testSomething
 */
public class TestRunner {

}
