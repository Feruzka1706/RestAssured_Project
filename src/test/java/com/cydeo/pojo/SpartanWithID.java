package com.cydeo.pojo;

/**
 Another common way of representing json data is using
 special purpose Java Object created from A class
 that have fields matched to json keys
 and have getters and setters
 This type of Object , sole purpose is to represent data ,
 known as POJO , plain old java object
 The class to create POJO known as POJO class
 It's used for creating POJO , just like you create any other object

 for example : in order to represent a json data with 3 keys , name, gender, phone
 we can create a java class with 3 instance fields with same name

 The only required part of POJO class is
 encapsulated fields :  private instance variables public getters and setters
 no arg constructors
 **/

public class SpartanWithID {
    private int id;
    private String name;
    private String gender;
    private long phone;

    public SpartanWithID() {
    }



    @Override
    public String toString() {
        return "SpartanWithID{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
