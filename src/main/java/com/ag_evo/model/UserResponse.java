package com.ag_evo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    @JsonProperty("name")
    private String name;

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

    @JsonProperty("registeredOn")
    private LocalDate registeredOn;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("policy")
    private List<String> policies;
}
