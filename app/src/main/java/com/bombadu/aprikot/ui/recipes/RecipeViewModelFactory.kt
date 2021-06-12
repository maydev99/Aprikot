package com.bombadu.aprikot.ui.recipes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bombadu.aprikot.local.CategoryEntity

class RecipeViewModelFactory(
    private val categoryEntity: CategoryEntity,
    private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeListViewModel::class.java)) {
            return RecipeListViewModel(application, categoryEntity) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}