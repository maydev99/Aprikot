package com.bombadu.aprikot.ui.preparation

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bombadu.aprikot.AprikotApplication
import com.bombadu.aprikot.local.PreparationEntity
import com.bombadu.aprikot.local.RecipeEntity
import com.bombadu.aprikot.local.getDatabase
import com.bombadu.aprikot.repository.MainRepository
import kotlinx.coroutines.launch

class PreparationViewModel(application: Application, recipeEntity: RecipeEntity) : AndroidViewModel(application) {

   private val repository = MainRepository(application as AprikotApplication, getDatabase(application))

    val preparations = repository.getPreparationData(recipeEntity.recipeId)

    var progress = MutableLiveData<Int>()

    fun insertUpdate(preparationEntity: PreparationEntity) {
        viewModelScope.launch {

            repository.insertUpdatedData(preparationEntity)
        }

    }

}