package com.anna.mycookbook.ui.starred

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anna.mycookbook.R
import com.anna.mycookbook.database.relationships.CompleteRecipes
import com.anna.mycookbook.databinding.RecipeListItemBinding
import com.bumptech.glide.Glide

class StarredListViewHolder(private val binding: RecipeListItemBinding): RecyclerView.ViewHolder(binding.root) {
    private val recipeImage by lazy {
        binding.recipeImage
    }

    private val recipeTitle by lazy {
        binding.recipeTitle
    }

    private val recipeDesc by lazy {
        binding.recipeDesc
    }

    var recipeId = 0

    fun bind(recipe: CompleteRecipes?){
        recipeTitle.text = recipe?.recipe?.recipeTitle
        recipeDesc.text = recipe?.recipe?.recipeDesc
        Glide.with(binding.root.context)
            .load(recipe?.recipe?.recipeImage)
            .placeholder(R.drawable.ic_baseline_fastfood_24)
            .centerCrop()
            .into(recipeImage)
    }

    companion object{
        fun create(parent: ViewGroup): StarredListViewHolder{
            val binding = RecipeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return StarredListViewHolder(binding)
        }
    }
}