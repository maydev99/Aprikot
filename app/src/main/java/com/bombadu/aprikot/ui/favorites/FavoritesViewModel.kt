package com.bombadu.aprikot.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bombadu.aprikot.local.PreparationEntity
import com.bombadu.aprikot.local.getDatabase
import com.bombadu.aprikot.ui.preparation.PreparationRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FavoriteRepository(getDatabase(application))
    private val prepRepository = PreparationRepository(getDatabase(application))
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