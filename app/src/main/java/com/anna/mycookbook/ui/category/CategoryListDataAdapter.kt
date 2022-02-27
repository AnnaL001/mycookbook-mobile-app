package com.anna.mycookbook.ui.category

import android.content.Context
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.anna.mycookbook.database.entity.Category

class CategoryListDataAdapter(): PagingDataAdapter<Category, CategoryListViewHolder>(
    CATEGORY_DIFFUTIL_CALLBACK) {
    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        val category = getItem(position)

        holder.apply {
            bind(category)
            categoryId = position + 1
            title = category!!.categoryTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        return CategoryListViewHolder.create(parent)
    }

    companion object{
        private val CATEGORY_DIFFUTIL_CALLBACK = object : DiffUtil.ItemCallback<Category>(){
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem == newItem
            }

        }
    }
}