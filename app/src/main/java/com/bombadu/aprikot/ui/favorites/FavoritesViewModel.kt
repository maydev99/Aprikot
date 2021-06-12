package com.bombadu.aprikot.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bombadu.aprikot.local.getDatabase
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FavoriteRepository(getDatabase(application))
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