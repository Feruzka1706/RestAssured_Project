package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

@JsonIgnoreProperties(ignoreUnknown = true)

public class Character_POJO {

    /**
     * {
     *         "char_id": 1,
     *         "name": "Walter White",
     *         "occupation": [
     *             "High School Chemistry Teacher",
     *             "Meth King Pin"
     *         ],
     *         "status": "Presumed dead",
     *         "nickname": "Heisenberg",
     *         "appearance": [
     *             1,
     *             2,
     *             3,
     *             4,
     *             5
     *         ],
     *      }
     */

    @JsonProperty("char_id")
    private int id;
    private String name;
    private List<String> occupation;
    private String status;
    private String nickName;
    private int[] appearance;

}
