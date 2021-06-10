package com.bombadu.aprikot.ui.recipes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.local.PreparationEntity
import com.bombadu.aprikot.local.getDatabase
import kotlinx.coroutines.launch

class RecipeListViewModel(application: Application, categoryEntity: CategoryEntity) :
    AndroidViewModel(application) {

    private val repository = RecipesRepository(getDatabase(application))


    val recipes = repository.getRecipeData(categoryEntity.categoryName)

    val categoryTitle = categoryEntity.categoryName

/*
    private val _navigateToSelectedRecipe = MutableLiveData<PreparationEntity?>()
    val navigateToSelectedRecipe: MutableLiveData<PreparationEntity?>
        get() = _navigateToSelectedRecipe*/


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


    /*fun preparationDetails(preparationEntity: PreparationEntity) {
        _navigateToSelectedRecipe.value = preparationEntity
    }

    fun preparationDetailComplete() {
        _navigateToSelectedRecipe.value = null
    }
*/

    /*fun getRecipeDataByCategory(categoryEntity: CategoryEntity) {
        val category = categoryEntity.categoryName
        repository.getRecipeData(category)
       // refreshRecipeListData(category)


    }


    private fun refreshRecipeListData(category: String) {
        viewModelScope.launch {
            repository.refreshRecipesData(category)
        }
    }
*/

/*
    companion object {
        private val TAG = RecipeListViewModel::class.java.simpleName
    }*/

}