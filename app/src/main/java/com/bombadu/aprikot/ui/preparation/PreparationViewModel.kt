package com.bombadu.aprikot.ui.preparation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bombadu.aprikot.local.RecipeEntity
import com.bombadu.aprikot.local.getDatabase
import kotlinx.coroutines.launch

class PreparationViewModel(application: Application, recipeEntity: RecipeEntity) : AndroidViewModel(application) {

    private val repository = PreparationRepository(getDatabase(application))
    val preparations = repository.getPreparationData(recipeEntity.recipeId)


    fun getPreparationDataById(recipeEntity: RecipeEntity) {
        viewModelScope.launch {
            val prepId = recipeEntity.recipeId
            repository.getPreparationData(prepId)
            repository.refreshPreparationData(prepId)


        }
    }



    fun refreshPreparationData(recipeId: String) {
        viewModelScope.launch {
            repository.refreshPreparationData(recipeId)
        }

    }

}