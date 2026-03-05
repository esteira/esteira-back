package com.example.demo.domain.entity;

import com.example.demo.Model.ExternalUrls;
import com.example.demo.Model.Followers;
import com.example.demo.Model.Owner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Playlist")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long playlistId;

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

    @Embedded
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @Embedded
    @JsonProperty("followers")
    private Followers followers;

    @Transient
    @JsonProperty("href")
    private String href;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "playlist_id")
    @JsonProperty("images")
    private List<Image> images;

    @Embedded
    @JsonProperty("owner")
    private Owner owner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tracks_id", referencedColumnName = "id")  // Make sure this matches the PK of Tracks
    @JsonProperty("tracks")
    private Tracks tracks;

    @JsonProperty("snapshot_id")
    private String snapshotId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;
}
