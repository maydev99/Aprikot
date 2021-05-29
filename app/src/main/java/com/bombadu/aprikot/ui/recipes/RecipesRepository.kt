package com.bombadu.aprikot.ui.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.bombadu.aprikot.Recipes
import com.bombadu.aprikot.local.LocalDatabase
import com.bombadu.aprikot.local.RecipeEntity
import com.bombadu.aprikot.network.Network
import com.bombadu.aprikot.network.NetworkUtil
import com.bombadu.aprikot.util.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipesRepository(private val database: LocalDatabase) {


    fun getRecipeData(category: String): MutableLiveData<List<Recipes>> {
        Log.i("TAG", "XXXX$category")

        val recipeData = Transformations.map(database.recipeDao.getRecipesByCategory(category))
        { it.toDomainModel()} as MutableLiveData<List<Recipes>>
        Log.i("TAG", "XXXX${recipeData.value}")
        return recipeData

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