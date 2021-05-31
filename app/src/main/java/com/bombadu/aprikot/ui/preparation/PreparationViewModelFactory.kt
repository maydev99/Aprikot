package com.bombadu.aprikot.ui.preparation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bombadu.aprikot.local.RecipeEntity
import java.lang.IllegalArgumentException

class PreparationViewModelFactory(
    private val recipeEntity: RecipeEntity,
    private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreparationViewModel::class.java)) {
            return PreparationViewModel(application, recipeEntity) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}