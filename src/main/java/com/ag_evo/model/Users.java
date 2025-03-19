package com.ag_evo.model;

import com.ag_evo.utils.StringToListConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "registeredOn")
    private LocalDate registeredOn;

    @Column(name = "orgUnits")
    @Convert(converter = StringToListConverter.class)
    private List<String> orgUnits;
}
