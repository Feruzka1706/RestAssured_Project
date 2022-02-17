package com.cydeo.tests.homeworks;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoviePOJO {

    //first this is not a common java naming convention, must starts with lower case
    //we still want to instruct Jackson data-bind to match the fields we want
    //in order to instruct jackson library what json field to match what java field
    //you can use the annotation coming from Jackson library @JsonProperty
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("imdbID")
    private String imdbID;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Poster")
    private String poster;

    //generating no args Constructor, so later on to create object from this class (initialize the fields)
    public MoviePOJO() {
    }


    //generating toString() method to get string print as format whenever when we print Object
    @Override
    public String toString() {
        return "MoviePOJO{" +
                "Title='" + title + '\'' +
                ", Year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", Type='" + type + '\'' +
                ", Poster='" + poster + '\'' +
                '}';
    }

    //Generating getter and setter methods
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


}
