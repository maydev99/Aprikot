package com.bombadu.aprikot.ui.preparation

import androidx.lifecycle.LiveData
import com.bombadu.aprikot.local.LocalDatabase
import com.bombadu.aprikot.local.PreparationEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PreparationRepository(private val database: LocalDatabase) {


    fun getPreparationData(recipeId: String): LiveData<PreparationEntity> {
        return database.preparationDao.getPreparationById(recipeId)
    }


    suspend fun insertUpdatedData(preparationEntity: PreparationEntity) {
        withContext(Dispatchers.IO) {
            database.preparationDao.insertPreparation(preparationEntity)
        }
    }


}


