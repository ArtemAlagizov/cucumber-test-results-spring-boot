package com.komkommer.results.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Tag {
    @Setter
    @Getter
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @Setter
    @Getter
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;

    @Setter
    @Getter
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Location location;
}
