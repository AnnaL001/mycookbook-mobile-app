package com.anna.mycookbook.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.anna.mycookbook.database.entity.Ingredient

@Dao
interface IngredientDao {
    @Insert
    suspend fun insertAll(vararg ingredients: Ingredient)
}