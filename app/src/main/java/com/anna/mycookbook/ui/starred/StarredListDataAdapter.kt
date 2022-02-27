package com.anna.mycookbook.ui.starred

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.anna.mycookbook.database.relationships.CompleteRecipes

class StarredListDataAdapter(): PagingDataAdapter<CompleteRecipes, StarredListViewHolder>(
    STARRED_DIFFUTIl_CALLBACK) {
    override fun onBindViewHolder(holder: StarredListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.recipeId = item!!.recipe.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarredListViewHolder {
        return StarredListViewHolder.create(parent)
    }

    companion object{
        private val STARRED_DIFFUTIl_CALLBACK = object : DiffUtil.ItemCallback<CompleteRecipes>(){
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