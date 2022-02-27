package com.anna.mycookbook.ui.recipe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.anna.mycookbook.database.entity.Ingredient
import com.anna.mycookbook.database.entity.Recipe
import com.anna.mycookbook.database.entity.Step
import com.anna.mycookbook.database.relationships.CompleteRecipes
import com.anna.mycookbook.databinding.FragmentRecipesListBinding
import kotlinx.coroutines.launch

class RecipeListFragment: Fragment() {
    private var _binding: FragmentRecipesListBinding ?= null
    private lateinit var recipeListViewModel: RecipeListViewModel
    private val TAG = this::class.simpleName

    private val recyclerView by lazy {
        binding.recipeRecyclerView
    }

    private val recipeLayoutManager by lazy {
        LinearLayoutManager(context)
    }

    private val recipeDataAdapter by lazy {
        RecipeListDataAdapter()
    }

    // Available between onCreateView and onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeListViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

//        insertRecipe()

        val categoryId = requireArguments().getInt("categoryId")
        val categoryTitle = requireArguments().getString("title")
        Log.d(TAG, "ACTIVE CATEGORY ID: $categoryId, TITLE: $categoryTitle")
        binding.categoryTitle.text = categoryTitle

        initRecyclerView()

        recipeListViewModel.getRecipes(categoryId).observe(viewLifecycleOwner, {
            Log.d(tag, "INITIALIZE PAGING DATA ADAPTER")
            recipeDataAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        })

    }

    private fun initRecyclerView(){
        Log.d(tag, "INITIALIZE RECYCLERVIEW")
        recyclerView.apply {
            layoutManager = recipeLayoutManager
            adapter = recipeDataAdapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}