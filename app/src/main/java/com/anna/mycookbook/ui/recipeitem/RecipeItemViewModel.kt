package com.anna.mycookbook.ui.recipeitem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.anna.mycookbook.database.CookBookRepository
import com.anna.mycookbook.database.entity.Recipe
import com.anna.mycookbook.database.relationships.CompleteRecipes
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RecipeItemViewModel(application: Application): AndroidViewModel(application) {
    private val cookBookRepository: CookBookRepository = CookBookRepository(application)

    fun getRecipe(recipeId: Int): LiveData<CompleteRecipes>{
        return cookBookRepository.getRecipe(recipeId)
    }

    fun starrRecipe(recipeId: Int, isStarred: Boolean){
        viewModelScope.launch {
            cookBookRepository.starrRecipe(recipeId, isStarred)
        }
    }
}