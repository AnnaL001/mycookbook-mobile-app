package com.anna.mycookbook.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps")
data class Step(
    @ColumnInfo(name = "step_desc") val stepDesc: String,
    @ColumnInfo(name = "recipe_id") val recipeId: Int
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}