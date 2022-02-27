package com.anna.mycookbook.database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.anna.mycookbook.database.dao.CategoryDao
import com.anna.mycookbook.database.dao.IngredientDao
import com.anna.mycookbook.database.dao.RecipeDao
import com.anna.mycookbook.database.dao.StepDao
import com.anna.mycookbook.database.entity.Category
import com.anna.mycookbook.database.entity.Recipe
import com.anna.mycookbook.database.relationships.CategoryWithRecipes
import com.anna.mycookbook.database.relationships.CompleteRecipes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CookBookRepository(application: Application,
                         private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    private val categoryDao: CategoryDao
    private val recipeDao: RecipeDao
    private val ingredientDao: IngredientDao
    private val stepDao: StepDao
    val categories: LiveData<PagingData<Category>>
    val starredRecipes: LiveData<PagingData<CompleteRecipes>>

    init {
        val cookBookDb = CookBookDatabase.getDatabase(application)
        categoryDao = cookBookDb.categoryDao()
        recipeDao = cookBookDb.recipeDao()
        ingredientDao = cookBookDb.ingredientDao()
        stepDao = cookBookDb.stepDao()

        categories = Pager(
            PagingConfig(
                pageSize = 8,
                enablePlaceholders = false,
                initialLoadSize = 8,
                maxSize = 46)
        ){ categoryDao.getCategories() }.liveData

        starredRecipes = Pager(
            PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10,
                maxSize = 50
            )
        ){ recipeDao.getStarredRecipes() }.liveData

    }

    fun getRecipes(categoryId: Int): LiveData<PagingData<CompleteRecipes>>{
        return Pager(
            PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10,
                maxSize = 50
            )
        ){ recipeDao.getRecipes(categoryId)}.liveData
    }

    fun getRecipe(recipeId: Int): LiveData<CompleteRecipes>{
        return recipeDao.getRecipe(recipeId)
    }

    suspend fun insertCategory(category: Category){
        withContext(ioDispatcher){
            categoryDao.insert(category)
        }
    }

    suspend fun insertRecipe(recipe: CompleteRecipes){
        withContext(ioDispatcher) {
            recipeDao.insert(recipe.recipe)
            ingredientDao.insertAll(*recipe.ingredients.toTypedArray())
            stepDao.insertAll(*recipe.steps.toTypedArray())
        }
    }

    suspend fun starrRecipe(recipeId: Int, isStarred: Boolean){
        withContext(ioDispatcher){
            recipeDao.starrRecipe(recipeId, isStarred)
        }
    }

}