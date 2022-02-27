package com.anna.mycookbook.ui.recipe

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.anna.mycookbook.database.relationships.CompleteRecipes

class RecipeListDataAdapter: PagingDataAdapter<CompleteRecipes, RecipeListViewHolder>(
    RECIPE_DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.apply {
            bind(recipe)
            recipeId = recipe!!.recipe.id
            recipeStarred = recipe.recipe.isStarred
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        return RecipeListViewHolder.create(parent)
    }

    companion object{
        private val RECIPE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<CompleteRecipes>(){
            override fun areItemsTheSame(oldItem: CompleteRecipes, newItem: CompleteRecipes
            ): Boolean {
                return oldItem.recipe.id == newItem.recipe.id
            }

            override fun areContentsTheSame(oldItem: CompleteRecipes, newItem: CompleteRecipes
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}