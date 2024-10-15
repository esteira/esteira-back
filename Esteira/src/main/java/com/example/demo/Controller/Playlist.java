package com.example.demo.Controller;

import com.example.demo.Model.Track;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Playlist")
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long playlistId;

    @JsonProperty("collaborative")
    private boolean collaborative;
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String nome;

    @JsonProperty("public")
    private boolean publica;

}











