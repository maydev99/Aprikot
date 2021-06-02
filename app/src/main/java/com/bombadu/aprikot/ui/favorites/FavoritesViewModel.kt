package com.bombadu.aprikot.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.bombadu.aprikot.local.getDatabase
import com.bombadu.aprikot.ui.preparation.PreparationRepository

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PreparationRepository(getDatabase(application))
    val preparations = repository.getFavorites()


}