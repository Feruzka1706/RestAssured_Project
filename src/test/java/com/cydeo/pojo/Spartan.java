package com.cydeo.pojo;

/**

 POST /spartans
 content type is json
 body is
 {
 "name":"API POST",
 "gender":"Male",
 "phone":1231231231
 }

 We learned how to represent json body using Map
 and let Jackson?
 Gson data-bind library to serialize it into Json when sending to the server
 Another common way of representing Json data is using special purpose Java Object created from A class
 That have fields matched to Json keys and have getters and setters
 This type of Object, sole purpose is to represent data, known as POJO >>Plain Old Java Object
 The class to create POJO known as POJO class,
 it's used for creating POJO< just like you create any other object

 For example: in order to represent a Json data with 3 fields , name, gender, phone
 we can create a java class with 3 instance fields with same name

 The only required part of POJO class is
 encapsulated fields: private instance fields public getters() and setter()
 no arg constructors

 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * SERIALIZATION:
 *     Java Object >>> Json format
 *
 * DE-SERIALIZATION:
 *     Json format >>> Java object
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class Spartan {

    private String name;
    private String gender;
    private long phone;

    public Spartan() {
     //just have it here, no body required
    }

    public Spartan(String name, String gender, long phone) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }




}
