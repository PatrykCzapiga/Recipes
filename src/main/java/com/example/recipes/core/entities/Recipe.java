package com.example.recipes.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="RECIPE")
public class Recipe {

        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        private String name;

        @Column
        private String category;

        @Column
        private LocalDateTime date;

        @Column
        private String description;

        @Column
        private String ingredients;

        @Column
        private String directions;

        @ManyToOne
        @JoinTable(name="USERRECIPE",
                joinColumns = @JoinColumn(name="id"),
                inverseJoinColumns = @JoinColumn(name="userId")
        )
        private User user;

}
