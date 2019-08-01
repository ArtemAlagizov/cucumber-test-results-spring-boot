package com.komkommer.results.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class Embedding {
    @Setter
    @Getter
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String data;
}
