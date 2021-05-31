package com.bombadu.aprikot.ui.recipes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.bombadu.aprikot.Recipes
import com.bombadu.aprikot.local.LocalDatabase
import com.bombadu.aprikot.network.Network
import com.bombadu.aprikot.network.NetworkUtil
import com.bombadu.aprikot.ui.preparation.PreparationRepository
import com.bombadu.aprikot.util.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipesRepository(private val database: LocalDatabase) {


    fun getRecipeData(category: String): MutableLiveData<List<Recipes>> {
        return Transformations.map(database.recipeDao.getRecipesByCategory(category))
        { it.toDomainModel() } as MutableLiveData<List<Recipes>>

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


    suspend fun refreshRecipesData(category: String) {
        try {
            val networkData = Network.api.getRecipesByCategory(category)
            val recipeData = NetworkUtil.convertRecipeData(networkData, category, false)

            for (i in recipeData.indices) {
                withContext(Dispatchers.IO) {
                    database.recipeDao.insertRecipes(recipeData[i])
                }
            }

        } catch (e: java.lang.Exception) {
            withContext(Dispatchers.IO) {
                Log.e(TAG, "Data request failed")
            }
        }
    }

    companion object {
        private val TAG = RecipesRepository::class.java.simpleName
    }

}