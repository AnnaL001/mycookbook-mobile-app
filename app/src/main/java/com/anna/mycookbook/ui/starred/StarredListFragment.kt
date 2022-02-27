package com.anna.mycookbook.ui.starred

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anna.mycookbook.databinding.FragmentStarredListBinding
import java.util.*

class StarredListFragment: Fragment() {
    private var _binding: FragmentStarredListBinding ?= null

    private val binding get() = _binding!!
    private lateinit var starredListViewModel: StarredListViewModel
    private val starredLayoutManager by lazy {
        LinearLayoutManager(context)
    }
    private val starredListAdapter by lazy {
        StarredListDataAdapter()
    }

    private val recyclerView by lazy {
        binding.starredRecyclerView
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStarredListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        starredListViewModel = ViewModelProvider(this).get(StarredListViewModel::class.java)

        initRecyclerView()
        initAdapter()
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT
    ){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            targetViewHolder: RecyclerView.ViewHolder
        ): Boolean {
            // Called when item is dragged
            val fromPosition = viewHolder.bindingAdapterPosition
            val toPosition = targetViewHolder.bindingAdapterPosition

            recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            (TODO("UNSTARR RECIPE"))
        }
    }
    )

    private fun initRecyclerView(){
        recyclerView.apply {
            layoutManager = starredLayoutManager
            adapter = starredListAdapter
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    private fun initAdapter(){
        starredListViewModel.starredRecipes.observe(viewLifecycleOwner, { starred ->
            starredListAdapter.submitData(viewLifecycleOwner.lifecycle, starred)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}