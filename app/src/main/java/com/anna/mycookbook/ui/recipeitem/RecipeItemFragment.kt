package com.anna.mycookbook.ui.recipeitem

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anna.mycookbook.R
import com.anna.mycookbook.database.entity.Recipe
import com.anna.mycookbook.databinding.FragmentRecipeListItemBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import android.view.MenuItem as MenuItem1

class RecipeItemFragment: Fragment() {
   private var _binding: FragmentRecipeListItemBinding ?= null
   private lateinit var recipeItemViewModel: RecipeItemViewModel
   var recipeId: Int = 0
   lateinit var recipe: Recipe

    private val recipeTitle by lazy {
       binding.recipeItemTitle
   }
   private val recipeDesc by lazy {
       binding.recipeItemDesc
   }
   private val recipeImage by lazy {
       binding.recipeItemImage
   }

   private val ingredientRecyclerView by lazy {
       binding.ingredientsRecyclerView
   }

   private val stepRecyclerView by lazy {
       binding.stepsRecyclerView
   }

   private val ingredientListAdapter by lazy {
       IngredientListRecyclerAdapter()
   }

   private val stepListAdapter by lazy {
       StepListRecyclerAdapter()
   }


    // Available between onCreateView and onDestroyView
   private val binding get() = _binding!!

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeListItemBinding.inflate(inflater, container, false)
       setHasOptionsMenu(true)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeItemViewModel = ViewModelProvider(this).get(RecipeItemViewModel::class.java)

        recipeId = requireArguments().getInt("recipeId")
        val adapterPos = requireArguments().getInt("adapterPos")
        Log.d(tag, "ACTIVE RECIPE ID: $recipeId, ADAPTER POS: $adapterPos")


        ingredientRecyclerView.layoutManager = LinearLayoutManager(context)
        ingredientRecyclerView.adapter = ingredientListAdapter

        stepRecyclerView.layoutManager = LinearLayoutManager(context)
        stepRecyclerView.adapter = stepListAdapter



        recipeItemViewModel.getRecipe(recipeId).observe(viewLifecycleOwner, { recipe ->
            recipeTitle.text = recipe.recipe.recipeTitle
            recipeDesc.text = recipe.recipe.recipeDesc
            Glide.with(binding.root.context)
                .load(recipe.recipe.recipeImage)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_fastfood_24)
                .into(recipeImage)
            ingredientListAdapter.setIngredients(recipe.ingredients)
            stepListAdapter.setSteps(recipe.steps)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipe_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem1): Boolean {
        when(item.itemId){
            R.id.action_share -> {
                return true
            }
            R.id.action_starr -> {
                starrRecipe()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun starrRecipe(){
        Log.d(tag, "STARR/ UNSTARR RECIPE ID: $recipeId")
        viewLifecycleOwner.lifecycleScope.launch{
            val isStarred = requireArguments().getBoolean("recipeStarred")
            Log.d(tag, "$isStarred")
            recipeItemViewModel.starrRecipe(recipeId, !isStarred)
        }
        requireActivity().invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val menuItem = menu.findItem(R.id.action_starr)
        recipeItemViewModel.getRecipe(recipeId).observe(viewLifecycleOwner, {
            Log.d(tag, "$it")
            if (it.recipe.isStarred){
                menuItem.icon = getDrawable(requireContext(), R.drawable.ic_baseline_star_rate_24)
                menuItem.icon.setTint(resources.getColor(R.color.white, null))
            } else {
                menuItem.icon = getDrawable(requireContext(), R.drawable.ic_baseline_star_outline_24)
                menuItem.icon.setTint(resources.getColor(R.color.white, null))
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}