package com.planning.mealsandrecipes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "recipe")
@Getter
@Setter
@Schema(description = "This class represents recipe.")
public class Recipe {

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final String required = "Required field";

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Schema(description = "Identifier", example = "2")
    private Long id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Schema(description = "Client Identifier", example = "6")
    private Long client_id;


    @Schema(description = "Name", example = "Butter Chicken")
    private String name;

    @Column(nullable = false)
    @Schema(description = "Description", example = "Cook it asap")
    private String description;

    @Column(nullable = false)
    @Schema(description = "Instructions", example = "Dice tomatoes properly")
    private String instruction;


    @Column(
            name = "prep_time",
            nullable = false
    )
    @Schema(description = "Prep time", example = "23")
    private Integer timeToPrepare;

    @Column(
            name = "cook_time",
            nullable = false
    )

    @Schema(description = "Cook time", example = "29")
    private Integer timeToCook;

    @OneToMany(
            mappedBy = "recipe"
    )
//    @JsonIgnore
//    private Set<RecipeIngredient> ingredientSet = new HashSet<>();

//    @ManyToMany
//    @JoinTable(
//            name = "recipe_categories",
//            joinColumns = @JoinColumn(name = "recipe_id"),
//            inverseJoinColumns = @JoinColumn(name = "client_id")
//    )
////    private Set<Category> categories = new HashSet<>();
//
//    @ManyToMany(
//            mappedBy = "favoriteRecipes"
//    )

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id.equals(recipe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
