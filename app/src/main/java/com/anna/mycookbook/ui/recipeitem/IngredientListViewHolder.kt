package com.anna.mycookbook.ui.recipeitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anna.mycookbook.database.entity.Ingredient
import com.anna.mycookbook.databinding.IngredientListItemBinding

class IngredientListViewHolder(private val binding: IngredientListItemBinding):
    RecyclerView.ViewHolder(binding.root) {

    private val ingredientDesc by lazy {
        binding.ingredientDesc
    }

    fun bind(ingredient: Ingredient?){
        ingredientDesc.text = ingredient?.IngredientDesc
    }

    companion object{
        fun create(parent: ViewGroup): IngredientListViewHolder{
            val binding = IngredientListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return IngredientListViewHolder(binding)
        }
    }
}