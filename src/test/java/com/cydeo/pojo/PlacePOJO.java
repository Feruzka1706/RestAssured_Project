package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PlacePOJO {

    /**
     * create a pojo called Place to represent place json object
     *               with these specific fields :
     *              - name as String
     *              - postCode as int
     *              - latitude as float
     *             - longitude as float
     *            {
     *                    "place name": "Fairfax",
     *                    "longitude": "-77.3242",
     *                    "post code": "22030",
     *                    "latitude": "38.8458"
     *            }
     */

    @JsonProperty("place name")
    private String placeName;
    private float longitude;
    @JsonProperty("post code")
    private int zipCode;
    private float latitude;
}
