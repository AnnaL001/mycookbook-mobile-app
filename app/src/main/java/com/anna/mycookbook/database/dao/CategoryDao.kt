package com.anna.mycookbook.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anna.mycookbook.database.entity.Category
import com.anna.mycookbook.database.relationships.CategoryWithRecipes

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getCategories(): PagingSource<Int, Category>

    @Insert
    suspend fun insert(category: Category)
}