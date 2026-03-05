package com.example.demo.domain.entity;

import com.example.demo.Model.ExplicitContent;
import com.example.demo.Model.ExternalUrls;
import com.example.demo.Model.Followers;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Table(name = "Usuario")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioid;

    @JsonProperty("country")
    private String country;

    @JsonProperty("display_name")
    private String nome;

    @JsonProperty("email")
    private String email;

    @JsonProperty("id")
    private String spotifyId;

    @Embedded
    @JsonProperty("explicit_content")
    private ExplicitContent explicitContent;

    @Embedded
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @Embedded
    @JsonProperty("followers")
    private Followers followers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id") // Chave estrangeira na tabela de Imagens para vincular ao usuário
    @JsonProperty("images")
    private List<Image> images;

    @JsonProperty("product")
    private String product;

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;



}
