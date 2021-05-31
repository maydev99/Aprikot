package com.bombadu.aprikot.ui.preparation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.bombadu.aprikot.Preparation
import com.bombadu.aprikot.local.LocalDatabase
import com.bombadu.aprikot.util.toDomainModel

class PreparationRepository(private val database: LocalDatabase) {


    fun getPreparationData(recipeId: String): MutableLiveData<Preparation> {
        return Transformations.map(database.preparationDao.getPreparationById(recipeId))
        { it.toDomainModel() } as MutableLiveData<Preparation>
    }


}