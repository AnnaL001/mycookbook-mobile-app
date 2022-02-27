package com.anna.mycookbook.ui.recipe

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.anna.mycookbook.R
import com.anna.mycookbook.database.relationships.CompleteRecipes
import com.anna.mycookbook.databinding.RecipeListItemBinding
import com.bumptech.glide.Glide

class RecipeListViewHolder(private val binding: RecipeListItemBinding):
    RecyclerView.ViewHolder(binding.root)  {
        var recipeId = 0
        var recipeStarred = false

        private val TAG = this::class.simpleName

        init {
            binding.root.setOnClickListener { view ->
                val bundle = bundleOf(
                    "recipeId" to recipeId,
                    "recipeStarred" to recipeStarred,
                    "adapterPos" to bindingAdapterPosition)
                Log.d(TAG, "$bundle")
                view.findNavController().navigate(R.id.fragment_recipe_to_fragment_recipe_item, bundle)
            }
        }

        private val recipeImage by lazy {
            binding.recipeImage
        }

        private val recipeTitle by lazy {
            binding.recipeTitle
        }

        private val recipeDesc by lazy {
            binding.recipeDesc
        }

        fun bind(recipe: CompleteRecipes?){
            val image = recipe?.recipe?.recipeImage

            recipeTitle.text = recipe?.recipe?.recipeTitle
            recipeDesc.text = recipe?.recipe?.recipeDesc
            Glide.with(binding.root.context)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_fastfood_24)
                .into(recipeImage)
        }

        companion object{
            fun create(parent: ViewGroup): RecipeListViewHolder{
                val binding = RecipeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return RecipeListViewHolder(binding)
            }
        }
}