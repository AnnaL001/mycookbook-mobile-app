package com.anna.mycookbook.ui.recipeitem

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anna.mycookbook.database.entity.Ingredient

class IngredientListRecyclerAdapter(): RecyclerView.Adapter<IngredientListViewHolder>() {
    private var ingredientList: List<Ingredient> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientListViewHolder {
        return IngredientListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: IngredientListViewHolder, position: Int) {
        val ingredient = ingredientList[position]
        holder.bind(ingredient)
    }

    fun setIngredients(ingredients: List<Ingredient>){
        ingredientList = ingredients
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = ingredientList.size
}