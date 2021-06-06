package com.bombadu.aprikot.ui.favorites

import androidx.lifecycle.LiveData
import com.bombadu.aprikot.local.LocalDatabase
import com.bombadu.aprikot.local.PreparationEntity

class FavoriteRepository(private val database: LocalDatabase) {

    val favoriteData: LiveData<List<PreparationEntity>> = database.preparationDao.getFavorites()

    fun getFavorites() : LiveData<List<PreparationEntity>> {
        return database.preparationDao.getFavorites()
    }



}