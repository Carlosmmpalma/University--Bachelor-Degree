package com.psideris.moviesapi.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Director(@JsonProperty("name")String name, @JsonProperty("surname")String surname) {

    public Director {
        if (surname == null)
            throw new IllegalArgumentException("Surname required");
    }
}
