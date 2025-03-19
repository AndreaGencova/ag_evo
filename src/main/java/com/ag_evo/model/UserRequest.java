package com.ag_evo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("emailAddress")
    private String email;

    @JsonProperty("organizationUnit")
    private List<String> orgUnits;

    @JsonProperty("birthDate")
    private LocalDate birthDate;
}
