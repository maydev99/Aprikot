package com.bombadu.aprikot.ui.recipes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.local.getDatabase
import kotlinx.coroutines.launch

class RecipeListViewModel(application: Application, categoryEntity: CategoryEntity) :
    AndroidViewModel(application) {

    private val repository = RecipesRepository(getDatabase(application))


    val recipes = repository.getRecipeData(categoryEntity.categoryName)

    val categoryTitle = categoryEntity.categoryName


    init {
        viewModelScope.launch {
            repository.checkData(categoryEntity.categoryName)

        }

    }


    fun getRecipePreparation(recipeId: String, category: String) {
        viewModelScope.launch {
            repository.refreshIndividualRecipe(recipeId, category)
        }

    }

}