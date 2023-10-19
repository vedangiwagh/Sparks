package com.planning.mealsandrecipes.entity.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
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
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Schema(description = "Identifier", example = "9")
    private Long id;

    @Column(
            unique = true,
            nullable = false
    )

    @Schema(description = "Name", example = "Banana")
    private String name;

    @Schema(description = "Protein value of the ingredient.", example = "7.7")
    private BigDecimal protein;

    @Schema(description = "Calories", example = "320")
    private BigDecimal calories;

    @Schema(description = "Carbs.", example = "350")
    private BigDecimal carbs;

    @Schema(description = "Sugar value of the ingredient.", example = "9")
    private BigDecimal sugar;

    @Schema(description = "Fat value", example = "50")
    private BigDecimal fat;

    @Schema(description = "Sodium value of the ingredient.", example = "10")
    private BigDecimal sodium;

    @Schema(description = "Fibre value of the ingredient.", example = "100")
    private BigDecimal fibre;

    @OneToMany(
            mappedBy = "ingredient"
    )
    @JsonIgnore
    private Set<RecipeIngredient> ingredientSet = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}