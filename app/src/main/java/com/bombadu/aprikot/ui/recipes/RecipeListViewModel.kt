package com.bombadu.aprikot.ui.recipes

import android.app.Application
import android.text.BoringLayout
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bombadu.aprikot.Recipes
import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.local.RecipeEntity
import com.bombadu.aprikot.local.getDatabase
import kotlinx.coroutines.launch

class RecipeListViewModel(application: Application, categoryEntity: CategoryEntity) : AndroidViewModel(application)  {

    private val repository = RecipesRepository(getDatabase(application))


    val  recipes = repository.getRecipeData(categoryEntity.categoryName)

    val categoryTitle = categoryEntity.categoryName

    init {
        viewModelScope.launch {
            repository.checkData(categoryEntity.categoryName)
        }

    }





    fun getRecipeDataByCategory(categoryEntity: CategoryEntity) {
        val category = categoryEntity.categoryName
        repository.getRecipeData(category)
       // refreshRecipeListData(category)


    }


    private fun refreshRecipeListData(category: String) {
        viewModelScope.launch {
            repository.refreshRecipesData(category)
        }
    }



    companion object {
        private val TAG = RecipeListViewModel::class.java.simpleName
    }

}