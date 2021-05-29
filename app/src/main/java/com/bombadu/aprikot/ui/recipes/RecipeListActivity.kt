package com.bombadu.aprikot.ui.recipes

import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bombadu.aprikot.R
import com.bombadu.aprikot.databinding.ActivityRecipeListBinding
import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.ui.categories.CategoriesFragment

class RecipeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeListBinding

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

        recipeListViewModel.getRecipeDataByCategory(categoryItem)













        //val viewModelFactory = RecipeListViewModel(application, )

       /* if (category != null) {
            recipeListViewModel.getRecipeDataByCategory(category)
        }*/

        /*if (category != null) {
            recipeListViewModel.refreshRecipelistData(category)
        }*/







        binding.recipeListRecyclerView.adapter = RecipeListAdapter(RecipeListAdapter.OnClickListener {
            /*val category = it.categoryName
            val intent = Intent(context, RecipeListActivity::class.java)
            intent.putExtra("category_key", category)
            startActivity(intent)*/
        })


    }
}