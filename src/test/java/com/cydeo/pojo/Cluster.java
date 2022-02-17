package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cluster {

    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("rooms")
    private List<Rooms> rooms;

}
