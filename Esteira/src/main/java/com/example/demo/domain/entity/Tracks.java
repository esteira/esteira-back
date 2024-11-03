package com.example.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tracks")
public class Tracks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("href")
    private String href;

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("next")
    private String next;

    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("previous")
    private String previous;

    @JsonProperty("total")
    private Integer total;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "track_collection_id")  // Altere aqui para evitar conflitos
    @JsonProperty("items")
    private List<Track> items;

    // Getters e Setters
}
