package com.bombadu.aprikot.ui.categories

import android.app.Application
import androidx.lifecycle.*
import com.bombadu.aprikot.local.getDatabase
import kotlinx.coroutines.launch


class CategoriesViewModel(application: Application): AndroidViewModel(application) {

    private val repository = CategoriesRepository(getDatabase(application))

    val categories = repository.categoryData



    init {
        viewModelScope.launch {
            repository.checkData()
        }
    }



}