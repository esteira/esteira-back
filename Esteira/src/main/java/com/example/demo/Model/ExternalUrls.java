package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Data;

@Embeddable
@Data
public class ExternalUrls {

    @Transient
    @JsonProperty("spotify")
    private String spotify;
    }