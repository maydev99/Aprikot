package com.bombadu.aprikot.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bombadu.aprikot.R
import com.bombadu.aprikot.databinding.ActivityRecipeListBinding
import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.local.RecipeEntity
import com.bombadu.aprikot.ui.categories.CategoriesFragment
import com.bombadu.aprikot.ui.preparation.PreparationActivity

class RecipeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeListBinding
    private var recName: String = ""

    private val recipeListViewModel: RecipeListViewModel by lazy {
        ViewModelProvider(this).get(RecipeListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_list)

        binding.lifecycleOwner = this

        val application = requireNotNull(this).application

        val categoryItem = intent.extras!!.getParcelable<CategoryEntity>(CategoriesFragment.CATEGORY_ITEM)

        val viewModelFactory = RecipeViewModelFactory(categoryItem!!, application)

        binding.viewModel = viewModelFactory.let {
            ViewModelProvider(this,
                it
            ).get(RecipeListViewModel::class.java)
        }


        binding.recipeListRecyclerView.adapter = RecipeListAdapter(RecipeListAdapter.OnClickListener {

            val intent = Intent(this, PreparationActivity::class.java)
            val recipeItem = RecipeEntity(it.recipeId, it.recipeName, it.recipeImageUrl,
                it.recipeCategory, it.recipeFavorite)
            intent.putExtra(SELECTED_RECIPE, recipeItem)
            startActivity(intent)

        })


    }

    companion object {
        private val TAG = RecipeListActivity::class.java.simpleName
        const val SELECTED_RECIPE = "selected_recipe"

    }
}