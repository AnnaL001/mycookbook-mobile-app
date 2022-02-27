package com.anna.mycookbook.database.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.anna.mycookbook.database.entity.Ingredient
import com.anna.mycookbook.database.entity.Recipe
import com.anna.mycookbook.database.entity.Step

data class CompleteRecipes(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipe_id"
    )
    val ingredients: List<Ingredient>,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipe_id"
        )
    val steps: List<Step>
)