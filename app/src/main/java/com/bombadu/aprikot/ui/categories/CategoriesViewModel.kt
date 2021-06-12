package com.bombadu.aprikot.ui.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bombadu.aprikot.AprikotApplication
import com.bombadu.aprikot.local.getDatabase
import com.bombadu.aprikot.repository.MainRepository
import kotlinx.coroutines.launch


class CategoriesViewModel(application: Application): AndroidViewModel(application) {

    private val repository = MainRepository(application as AprikotApplication, getDatabase(application))
    var progress = MutableLiveData<Int>()

    val categories = repository.categoryData


    init {
        viewModelScope.launch {
            progress.value = 0 //Visible
            repository.checkData()
        }
    }

    fun loadingComplete() {
        progress.value = 8 //Invisible
    }



}