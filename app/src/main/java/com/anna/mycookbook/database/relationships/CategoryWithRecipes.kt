package com.anna.mycookbook.database.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.anna.mycookbook.database.entity.Category
import com.anna.mycookbook.database.entity.Recipe

data class CategoryWithRecipes(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    )
    val recipes: List<Recipe>
)