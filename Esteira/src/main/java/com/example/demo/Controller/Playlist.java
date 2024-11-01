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
    public Playlist(String spotifyId, String nome, boolean publica, boolean collaborative) {
        this.spotifyId = spotifyId;
        this.nome = nome;
        this.publica = publica;
        this.collaborative = collaborative;
    }

    public Playlist() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long playlistId;

    public long getPlaylistId() {
        return playlistId;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public boolean isCollaborative() {
        return collaborative;
    }

    public String getDescription() {
        return description;
    }

    public String getNome() {
        return nome;
    }

    public boolean isPublica() {
        return publica;
    }

    @JsonProperty("id")
    private String spotifyId;

    @JsonProperty("collaborative")
    private boolean collaborative;
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String nome;

    @JsonProperty("public")
    private boolean publica;

}











