package com.anna.mycookbook.ui.starred

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.anna.mycookbook.database.CookBookRepository
import com.anna.mycookbook.database.relationships.CompleteRecipes
import kotlinx.coroutines.launch

class StarredListViewModel(application: Application): AndroidViewModel(application){
    val starredRecipes: LiveData<PagingData<CompleteRecipes>>
    private val cookBookRepository = CookBookRepository(application)

    init {
        starredRecipes = cookBookRepository.starredRecipes
    }

    fun unStarrRecipe(recipeId: Int, isStarred: Boolean){
        viewModelScope.launch {
            cookBookRepository.starrRecipe(recipeId, isStarred)
        }
    }

}