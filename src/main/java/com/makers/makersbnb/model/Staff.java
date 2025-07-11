package com.makers.makersbnb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor

@Table(name = "STAFF")
public class Staff {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String role;

    public Staff(String name, String role) {
        this.name = name;
        this.role = role;
    }
}
