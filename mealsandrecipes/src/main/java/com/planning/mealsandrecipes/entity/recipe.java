package com.planning.mealsandrecipes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "recipe")
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(nullable = false)
    @NotEmpty(message = required)

    @Schema(description = "Name", example = "Butter Chicken")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = required)
    @Schema(description = "Description", example = "Cook it asap")
    private String description;

    @Column(nullable = false)
    @NotEmpty(message = required)
    @Schema(description = "Instructions", example = "Dice tomatoes properly")
    private String instruction;


    @Column(
            name = "prep_time",
            nullable = false
    )
    @NotNull(message = required)
    @Min(value = 1, message = "Prep time should be greater than a minute")
    @Schema(description = "Prep time", example = "23")
    private Integer timeToPrepare;

    @Column(
            name = "cook_time",
            nullable = false
    )
    @NotNull(message = required)
    @Min(value = 1, message = "Cook time should be greater than a minute.")
    @Schema(description = "Cook time", example = "29")
    private Integer timeToCook;

    @OneToMany(
            mappedBy = "recipe"
    )
    @JsonIgnore
    private Set<RecipeIngredient> ingredientSet = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "recipe_categories",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(
            mappedBy = "favoriteRecipes"
    )

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
