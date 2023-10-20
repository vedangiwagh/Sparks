package com.planning.mealsandrecipes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "ingredient")
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "This class represents ingredients used.")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the ingredient
    private String name; // Name of the ingredient
    private Double calories; // Caloric content
    private Double fat; // Fat content
    private Double carbohydrates; // Carbohydrate content
    private Double fiber; // Dietary fiber content
    private Double sugar; // Sugar content
    private Double protein; // Protein content
    private Double sodium; // Sodium content

    // Constructors, getters, and setters

    // Override the equals method to compare ingredients by their ID.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id.equals(that.id);
    }

    // Override the hashCode method to compute the hash code based on the ingredient's ID.
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
