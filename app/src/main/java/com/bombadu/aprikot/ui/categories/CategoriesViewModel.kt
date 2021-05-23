package com.bombadu.aprikot.ui.categories

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.bombadu.aprikot.local.getDatabase
import com.bombadu.aprikot.network.CategoryData
import com.bombadu.aprikot.repository.MainRepository
import kotlinx.coroutines.launch


class CategoriesViewModel(application: Application): AndroidViewModel(application) {

    private val repository = MainRepository(getDatabase(application))

    private val _categoryData = MutableLiveData<List<CategoryData>>()

    val categoryData:  LiveData<List<CategoryData>>
    get() = _categoryData


    init {
        viewModelScope.launch {
            repository.refreshCategoryData()
        }
    }

}