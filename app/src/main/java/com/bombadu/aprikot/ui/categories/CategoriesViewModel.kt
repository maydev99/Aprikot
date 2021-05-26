package com.bombadu.aprikot.ui.categories

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.local.getDatabase
import com.bombadu.aprikot.network.CategoryData
import com.bombadu.aprikot.repository.MainRepository
import kotlinx.coroutines.launch
import java.util.*


class CategoriesViewModel(application: Application): AndroidViewModel(application) {

    //private val database = getDatabase(application)
    private val repository = MainRepository(getDatabase(application))

    val categories = repository.categoryData

    private val _categoryData = MutableLiveData<List<CategoryData>>()
    val categoryData:  LiveData<List<CategoryData>>
    get() = _categoryData

   /* private val _navigateToRecipeListFragment = MutableLiveData<String?>()
    val navigateToRecipeListFragment: MutableLiveData<String?>
    get() = _navigateToRecipeListFragment


    fun displayRecipesByCategory(category: String) {
        _navigateToRecipeListFragment.value = category
    }

    fun displayNavigationComplete() {
        _navigateToRecipeListFragment.value = null
    }*/




    init {
        viewModelScope.launch {
            repository.refreshCategoryData()
        }
    }



}