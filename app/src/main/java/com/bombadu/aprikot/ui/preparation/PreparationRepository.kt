package com.bombadu.aprikot.ui.preparation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.bombadu.aprikot.Preparation
import com.bombadu.aprikot.local.LocalDatabase
import com.bombadu.aprikot.network.Network
import com.bombadu.aprikot.network.NetworkUtil
import com.bombadu.aprikot.util.toDomainModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PreparationRepository(private val database: LocalDatabase) {



    fun getPreparationData(recipeId: String): LiveData<MutableLiveData<Preparation>> {
        return Transformations.map(database.preparationDao.getPreparationById(recipeId))
        { recipe ->
            recipe.let {
                recipe.toDomainModel() } as MutableLiveData<Preparation>
            }
    }



    suspend fun refreshPreparationData(recipeId : String) {
        try {
            val networkData = Network.api.getPreparationData(recipeId)
            val prepData = NetworkUtil.convertPreparationData(networkData)
            withContext(Dispatchers.IO) {
                database.preparationDao.insertPreparation(prepData)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Data Request failed")
        }
    }

    companion object {
        private val TAG = PreparationRepository::class.java.simpleName
    }

}