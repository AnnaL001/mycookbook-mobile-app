package com.anna.mycookbook.ui.recipeitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anna.mycookbook.R
import com.anna.mycookbook.database.entity.Step
import com.anna.mycookbook.databinding.StepListItemBinding

class StepListViewHolder(private val binding: StepListItemBinding): RecyclerView.ViewHolder(binding.root){
    private val stepDesc by lazy {
        binding.stepDesc
    }

    private val context by lazy {
        binding.root.context
    }

    fun bind(step: Step){
        val itemPosition = bindingAdapterPosition + 1
        stepDesc.text = context.getString(R.string.step_text, itemPosition, step.stepDesc)
    }

    companion object{
        fun create(parent: ViewGroup): StepListViewHolder{
            val binding = StepListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return StepListViewHolder(binding)
        }
    }
}