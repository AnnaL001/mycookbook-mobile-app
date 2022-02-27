package com.anna.mycookbook.ui.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.anna.mycookbook.R
import com.anna.mycookbook.databinding.FragmentCategoriesListBinding

class CategoryListFragment: Fragment() {
    private lateinit var categoryListViewModel: CategoryListViewModel

    private var _binding: FragmentCategoriesListBinding ?= null
    private val TAG = this::class.simpleName

    private val categoryLayoutManager by lazy {
        GridLayoutManager(context, resources.getInteger(R.integer.category_grid_span))
    }

    private val categoryDataAdapter by lazy {
        CategoryListDataAdapter()
    }

    private val categoryRecyclerView by lazy {
        binding.categoryRecyclerView
    }

    // Available only between onCreateView and onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        Log.d(TAG, "CALLING ONCREATEVIEW")
        _binding = FragmentCategoriesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "CALLING ONVIEWCREATED")
        categoryListViewModel = ViewModelProvider(this).get(CategoryListViewModel::class.java)

        initRecyclerView()
        initDataAdapter()
    }

    private fun initRecyclerView(){
        Log.d(TAG, "INITIALIZING RECYCLERVIEW")
        categoryRecyclerView.apply {
            layoutManager = categoryLayoutManager
            adapter = categoryDataAdapter
        }
    }

    private fun initDataAdapter(){
        categoryListViewModel.categories.observe(viewLifecycleOwner, { categories ->
            Log.d(TAG, "INITIALIZING PAGING DATA ADAPTER")
            categoryDataAdapter.submitData(viewLifecycleOwner.lifecycle, categories)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}