package com.anna.mycookbook.ui.recipeitem

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anna.mycookbook.database.entity.Step

class StepListRecyclerAdapter : RecyclerView.Adapter<StepListViewHolder>() {
    private var stepList: List<Step> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepListViewHolder {
        return StepListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: StepListViewHolder, position: Int) {
        val step = stepList[position]
        holder.bind(step)
    }

    fun setSteps(step: List<Step>){
        stepList = step
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = stepList.size
}