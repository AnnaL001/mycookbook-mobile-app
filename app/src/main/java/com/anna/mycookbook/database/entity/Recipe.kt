package com.anna.mycookbook.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @ColumnInfo(name = "recipe_title") val recipeTitle: String,
    @ColumnInfo(name = "recipe_image") val recipeImage: String,
    @ColumnInfo(name = "recipe_desc") val recipeDesc: String,
    @ColumnInfo(name = "recipe_serving") val recipeServing: Int,
    @ColumnInfo(name = "category_id") val categoryId: Int,
    @ColumnInfo(name = "is_starred") var isStarred: Boolean,
    @ColumnInfo(name = "has_been_made") var hasBeenMade: Boolean
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}