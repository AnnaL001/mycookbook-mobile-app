package com.anna.mycookbook.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class Ingredient(
    @ColumnInfo(name = "ingredient_desc") val IngredientDesc: String,
    @ColumnInfo(name = "recipe_id") val recipeId: Int
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}