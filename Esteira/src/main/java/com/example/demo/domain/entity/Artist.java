package com.example.demo.domain.entity;


import com.example.demo.Model.ExternalUrls;
import com.example.demo.Model.Followers;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("id")
    private String artistId;

    @JsonProperty("name")
    private String name;

    @Embedded
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @Embedded
    @JsonProperty("followers")
    private Followers followers;

    @ElementCollection
    @JsonProperty("genres")
    private List<String> genres;

    @JsonProperty("href")
    @Transient
    private String href;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "artist_id")
    @JsonProperty("images")
    private List<Image> images;

    @JsonProperty("popularity")
    private Integer popularity;

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;


}
