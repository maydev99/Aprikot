package com.bombadu.aprikot.ui.recipes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bombadu.aprikot.local.getDatabase
import com.bombadu.aprikot.repository.MainRepository
import kotlinx.coroutines.launch

class RecipeListViewModel(application: Application) : AndroidViewModel(application)  {

    //private val database = getDatabase(application)
    private val repository = MainRepository(getDatabase(application))

    val recipes = repository.recipeData



    fun getTheRecipeListData(category: String) {
        viewModelScope.launch {
            repository.refreshRecipesData(category)
        }
    }

}