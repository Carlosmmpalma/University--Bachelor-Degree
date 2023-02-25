package com.psideris.moviesapi.records;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Movie(@JsonProperty("id") String id, @JsonProperty("director") Director director, @JsonProperty("released") Boolean released) {
}
