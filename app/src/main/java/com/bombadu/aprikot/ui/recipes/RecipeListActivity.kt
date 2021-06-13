package com.bombadu.aprikot.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bombadu.aprikot.R
import com.bombadu.aprikot.databinding.ActivityRecipeListBinding
import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.local.RecipeEntity
import com.bombadu.aprikot.network.ConnectionType
import com.bombadu.aprikot.network.NetworkMonitorUtil
import com.bombadu.aprikot.ui.MainActivity
import com.bombadu.aprikot.ui.categories.CategoriesFragment
import com.bombadu.aprikot.ui.preparation.PreparationActivity


class RecipeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeListBinding
    private val networkMonitor = NetworkMonitorUtil(this)

    private val recipeListViewModel: RecipeListViewModel by lazy {
        ViewModelProvider(this).get(RecipeListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_list)

        checkNetwork()

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

        recipeListViewModel.getRecipePreparation(it.recipeId, it.recipeCategory)

            val intent = Intent(this, PreparationActivity::class.java)
            val recipeItem = RecipeEntity(it.recipeId, it.recipeName, it.recipeImageUrl,
                it.recipeCategory, it.recipeFavorite)
            intent.putExtra(SELECTED_RECIPE, recipeItem)
            startActivity(intent)

        })

        recipeListViewModel.recipes.observe(this, {

            try {
                if (it[0].recipeImageUrl.isNotEmpty()) {
                    recipeListViewModel.loadingComplete()
                }
            } catch (e: Exception) {
                Log.e(TAG, "No Data")
            }

        })


    }

    private fun checkNetwork() {
        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when(isAvailable) {
                    true -> {
                        when(type) {
                            ConnectionType.Wifi -> {
                                Log.i(MainActivity.TAG, "Wifi Connected")
                            }

                            ConnectionType.Cellular -> {
                                Log.i(MainActivity.TAG, "Cellular Connected")
                            }

                            else -> {}
                        }
                    }
                    false -> {
                        Log.i(MainActivity.TAG, "No Connection")
                        Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }

    companion object {
        const val SELECTED_RECIPE = "selected_recipe"
        val TAG = RecipeListActivity::class.java.simpleName

    }
}