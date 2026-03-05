package com.example.demo.domain.entity;


import com.example.demo.Model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Album")
@Data
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("album_type")
    private String albumType;

    @JsonProperty("total_tracks")
    private Integer totalTracks;

    @ElementCollection
    @JsonProperty("available_markets")
    private List<String> availableMarkets;

    @Embedded
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @Transient
    @JsonProperty("href")
    private String href;

    @JsonProperty("id")
    private String albumId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id")
    @JsonProperty("images")
    private List<Image> images;

    @JsonProperty("name")
    private String name;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("release_date_precision")
    private String releaseDatePrecision;

    @Embedded
    @JsonProperty("restrictions")
    private Restrictions restrictions;

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id")
    @JsonProperty("artists")
    private List<Artist> artists;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id")
    @JsonProperty("copyrights")
    private List<Copyright> copyrights;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id")
    @JsonProperty("tracks")
    private List<Track> tracks;

    @Embedded
    @JsonProperty("external_ids")
    private ExternalIds externalIds;

    @ElementCollection
    @JsonProperty("genres")
    private List<String> genres;

    @JsonProperty("label")
    private String label;

    @JsonProperty("popularity")
    private Integer popularity;

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public String getAlbumId() {
        return albumId;
    }
}
