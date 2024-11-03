package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Data;

@Embeddable
@Data
public class Followers {

    @Transient
    @JsonProperty("href")
    private String href;

    @Transient
    @JsonProperty("total")
    private int total;

    // Getters e Setters
    // ...
}
