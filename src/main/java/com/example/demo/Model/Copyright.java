package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Copyright")
public class Copyright {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("text")
    private String text;

    @Transient
    @JsonProperty("type")
    private String type;

    // Getters e Setters
    // ...
}
