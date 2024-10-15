package com.example.demo.Model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "Usuario")
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class UserResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioid;

    @Transient
    private TokenResponse token;


    private String country;
    @JsonProperty("display_name")
    private String nome ;

    @JsonProperty("email")
    private String email;

    @JsonProperty("id")
    private String spotifyId;




    public Long getUsuarioid() {
        return usuarioid;
    }

    public String getspotifyId() {
        return spotifyId;
    }

    public String getNome() {
        return nome;
    }

    public String getCountry() {
        return country;
    }

    public void setUsuarioid(Long usuarioid) {
        this.usuarioid = usuarioid;
    }



}
