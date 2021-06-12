package com.bombadu.aprikot.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bombadu.aprikot.AprikotApplication
import com.bombadu.aprikot.local.getDatabase
import com.bombadu.aprikot.repository.MainRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepository(application as AprikotApplication, getDatabase(application))
    val favorite = repository.favoriteData


    init {
        viewModelScope.launch {
            getFavorites()
        }

    }

    private fun getFavorites() {
        repository.getFavorites()
    }


}