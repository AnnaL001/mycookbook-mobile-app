package com.anna.mycookbook.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.anna.mycookbook.database.entity.Recipe
import com.anna.mycookbook.database.relationships.CategoryWithRecipes
import com.anna.mycookbook.database.relationships.CompleteRecipes

@Dao
interface RecipeDao {
    @Insert
    suspend fun insert(recipe: Recipe)

    @Transaction
    @Query("SELECT * FROM recipes WHERE category_id = :categoryId")
    fun getRecipes(categoryId: Int): PagingSource<Int, CompleteRecipes>

    @Transaction
    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    fun getRecipe(recipeId: Int): LiveData<CompleteRecipes>

    @Transaction
    @Query("SELECT * FROM recipes WHERE is_starred = 1")
    fun getStarredRecipes(): PagingSource<Int, CompleteRecipes>

    @Query("UPDATE recipes SET is_starred = :isStarred WHERE id = :recipeId")
    suspend fun starrRecipe(recipeId: Int, isStarred: Boolean)
}