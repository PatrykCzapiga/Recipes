package com.example.recipes.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long userId;

        @Column(nullable = false, unique = true)
        @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")
        private String email;
        @NotBlank
        @Size(min = 8)
        private String password;
        private String role;

    }
