package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LinkPojo {


    @JsonProperty("rel")
    private String rel;

    @JsonProperty("href")
    private String href;
}
