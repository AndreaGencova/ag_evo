package com.ag_evo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Policy {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
}
