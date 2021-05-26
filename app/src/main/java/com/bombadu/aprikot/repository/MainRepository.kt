package com.bombadu.aprikot.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.local.LocalDatabase
import com.bombadu.aprikot.local.RecipeEntity
import com.bombadu.aprikot.network.Network
import com.bombadu.aprikot.network.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val database: LocalDatabase) {



    val categoryData: LiveData<List<CategoryEntity>> = database.categoryDao.getAllCategories()
    lateinit var recipeData: LiveData<List<RecipeEntity>>





    fun getRecipesByCategory(category: String) {
        recipeData = database.recipeDao.getRecipesByCategory(category)
    }




    suspend fun refreshRecipesData(category: String) {
        try {
            val networkData = Network.api.getRecipesByCategory(category)
            val recipeData  = NetworkUtil.convertRecipeData(networkData, category, false)

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



    suspend fun refreshCategoryData() {
        try {
            val networkData = Network.api.getCategories()
            val catData = NetworkUtil.convertCategoryData(networkData)

            for (i in catData.indices) {
                withContext(Dispatchers.IO) {
                    database.categoryDao.insertCategories(catData[i])
                }
            }

        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Log.e(TAG, "Data Request failed")
            }
        }
    }

    companion object {
        private val TAG = MainRepository::class.java.simpleName
    }

}