package com.bombadu.aprikot.ui.recipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bombadu.aprikot.R
import com.bombadu.aprikot.databinding.ActivityRecipeListBinding
import com.bombadu.aprikot.ui.categories.CategoryAdapter

class RecipeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeListBinding
    private lateinit var recipeListViewModel: RecipeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_list)

        binding.lifecycleOwner = this

        recipeListViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)
        binding.viewModel = recipeListViewModel

        val category = intent.getStringExtra("category_key")
        Toast.makeText(this, category, Toast.LENGTH_SHORT).show()

        if (category != null) {
            recipeListViewModel.getTheRecipeListData(category)
        }

        binding.recipeListRecyclerView.adapter = RecipeListAdapter(RecipeListAdapter.OnClickListener {
            /*val category = it.categoryName
            val intent = Intent(context, RecipeListActivity::class.java)
            intent.putExtra("category_key", category)
            startActivity(intent)*/
        })


    }
}