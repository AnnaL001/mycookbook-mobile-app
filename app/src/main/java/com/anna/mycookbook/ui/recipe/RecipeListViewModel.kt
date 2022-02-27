package com.anna.mycookbook.ui.recipe

import android.app.Application
import android.database.sqlite.SQLiteBlobTooBigException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.anna.mycookbook.database.CookBookRepository
import com.anna.mycookbook.database.entity.Recipe
import com.anna.mycookbook.database.relationships.CategoryWithRecipes
import com.anna.mycookbook.database.relationships.CompleteRecipes
import kotlinx.coroutines.launch

class RecipeListViewModel(application: Application): AndroidViewModel(application) {
    private val cookBookRepository = CookBookRepository(application)

    fun insertRecipe(recipe: CompleteRecipes){
        viewModelScope.launch {
            cookBookRepository.insertRecipe(recipe)
        }
    }

    fun getRecipes(categoryId: Int): LiveData<PagingData<CompleteRecipes>>{
        return cookBookRepository.getRecipes(categoryId)
    }
}