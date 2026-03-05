package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Embeddable
public class Owner {

    @Embedded
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @Embedded
    @JsonProperty("followers")
    private Followers followers;

    @Transient
    @JsonProperty("href")
    private String href;


    @JsonProperty("id")
    private String id;

    @Transient
    @JsonProperty("type")
    private String type;

    @Transient
    @JsonProperty("uri")
    private String uri;

    @JsonProperty("display_name")
    private String displayName;


}