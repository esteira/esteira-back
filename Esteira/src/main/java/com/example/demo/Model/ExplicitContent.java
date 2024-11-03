package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ExplicitContent {

    @JsonProperty("filter_enabled")
    private boolean filterEnabled;

    @JsonProperty("filter_locked")
    private boolean filterLocked;

}
