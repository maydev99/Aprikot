package com.bombadu.aprikot.ui.recipes

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bombadu.aprikot.AprikotApplication
import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.local.getDatabase
import com.bombadu.aprikot.repository.MainRepository
import kotlinx.coroutines.launch

class RecipeListViewModel(application: Application, categoryEntity: CategoryEntity) :
    AndroidViewModel(application) {

    private val repository = MainRepository(application as AprikotApplication, getDatabase(application))

    val recipes = repository.getRecipeData(categoryEntity.categoryName)

    val categoryTitle = categoryEntity.categoryName

    var progress = MutableLiveData<Int>()


    init {
        viewModelScope.launch {
            repository.checkData(categoryEntity.categoryName)
            progress.value = View.VISIBLE

        }

    }

    fun loadingComplete() {
        progress.value = View.GONE
    }


    fun getRecipePreparation(recipeId: String, category: String) {
        viewModelScope.launch {
            repository.refreshIndividualRecipe(recipeId, category)
        }

    }

}