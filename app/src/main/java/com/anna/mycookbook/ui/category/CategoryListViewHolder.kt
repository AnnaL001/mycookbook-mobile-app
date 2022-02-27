package com.anna.mycookbook.ui.category

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.anna.mycookbook.R
import com.anna.mycookbook.database.entity.Category
import com.anna.mycookbook.databinding.CategoryListItemBinding
import com.bumptech.glide.Glide

class CategoryListViewHolder(private val binding: CategoryListItemBinding):
    RecyclerView.ViewHolder(binding.root) {

        var categoryId = 0
        var title = ""

        init {
            binding.root.setOnClickListener { view ->
                val bundle = bundleOf("categoryId" to categoryId, "title" to title)
                Log.d("CATEGORYVIEWHOLDER", "$bundle")
                view.findNavController().navigate(R.id.fragment_category_to_fragment_recipe, bundle)
            }
        }

        private val categoryTitle by lazy {
            binding.categoryTitle
        }

        private val categoryImage by lazy {
            binding.categoryImage
        }

        fun bind(category: Category?){
            val image = category?.categoryImage

            categoryTitle.text = category?.categoryTitle
            Glide.with(binding.root.context)
                .load(image)
                .placeholder(R.drawable.ic_baseline_fastfood_24)
                .centerCrop()
                .into(categoryImage)
        }

        companion object{
            fun create(parent: ViewGroup): CategoryListViewHolder{
                val binding = CategoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return CategoryListViewHolder(binding)
            }
        }
}