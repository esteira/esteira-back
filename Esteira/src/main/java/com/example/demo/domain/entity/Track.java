package com.example.demo.domain.entity;

import com.example.demo.Model.ExternalIds;
import com.example.demo.Model.ExternalUrls;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "track")
@Data
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("id")
    private String trackId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("duration_ms")
    private Integer durationMs;

    @JsonProperty("explicit")
    private boolean explicit;

    @Embedded
    @JsonProperty("external_ids")
    private ExternalIds externalIds;

    @Embedded
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @JsonProperty("href")
    private String href;

    @JsonProperty("is_playable")
    private boolean isPlayable;

    @JsonProperty("popularity")
    private Integer popularity;

    @JsonProperty("preview_url")
    private String previewUrl;

    @JsonProperty("track_number")
    private Integer trackNumber;

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id")
    @JsonProperty("album")
    private Album album;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "track_id") // Chave estrangeira na tabela de Artistas para vincular à faixa
    @JsonProperty("artists")
    private List<Artist> artists;


}
