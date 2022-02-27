package com.anna.mycookbook.ui.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.anna.mycookbook.database.CookBookRepository
import com.anna.mycookbook.database.entity.Category
import com.anna.mycookbook.database.relationships.CategoryWithRecipes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryListViewModel(application: Application): AndroidViewModel(application){
    val categories: LiveData<PagingData<Category>>
    private val cookBookRepository = CookBookRepository(application)

    init {
        categories = cookBookRepository.categories.cachedIn(viewModelScope)
    }

    fun insertCategory(category: Category){
        viewModelScope.launch {
            cookBookRepository.insertCategory(category)
        }
    }

}