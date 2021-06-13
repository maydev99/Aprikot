package com.bombadu.aprikot.repository

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.bombadu.aprikot.AprikotApplication
import com.bombadu.aprikot.Recipes
import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.local.LocalDatabase
import com.bombadu.aprikot.local.PreparationEntity
import com.bombadu.aprikot.network.Network
import com.bombadu.aprikot.network.NetworkUtil
import com.bombadu.aprikot.ui.categories.CategoriesViewModel
import com.bombadu.aprikot.util.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository (aprikotApplication: AprikotApplication, private val database: LocalDatabase)  {

    val categoryData: LiveData<List<CategoryEntity>> = database.categoryDao.getAllCategories()
    val favoriteData: LiveData<List<PreparationEntity>> = database.preparationDao.getFavorites()
    val application = aprikotApplication



    //Category Screen
    suspend fun checkData() {
        withContext(Dispatchers.IO) {
            val exists = database.categoryDao.isExists()
            if (!exists) {
                refreshCategoryData()
            }
        }

    }


    private suspend fun refreshCategoryData() {
        try {
           // val viewModel = CategoriesViewModel(application)
           // viewModel.progress.value = View.VISIBLE

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


    //Favorite Screen
    fun getFavorites() : LiveData<List<PreparationEntity>> {
        return database.preparationDao.getFavorites()
    }


    //Preparation Screen
    fun getPreparationData(recipeId: String): LiveData<PreparationEntity> {
        return database.preparationDao.getPreparationById(recipeId)
    }


    suspend fun insertUpdatedData(preparationEntity: PreparationEntity) {
        withContext(Dispatchers.IO) {
            database.preparationDao.insertPreparation(preparationEntity)
        }
    }

    //Recipes List Screen Section
    suspend fun checkData(category: String) {
        withContext(Dispatchers.IO) {
            val exists = database.recipeDao.isRowIsExist(category)
            if (!exists) {
                refreshRecipesData(category)
            }
        }

    }

    fun getRecipeData(category: String): MutableLiveData<List<Recipes>> {
        return Transformations.map(database.recipeDao.getRecipesByCategory(category))
        { it.toDomainModel() } as MutableLiveData<List<Recipes>>

    }


    suspend fun refreshIndividualRecipe(recipeId: String, category: String) {
        withContext(Dispatchers.IO) {

            try {
                val exists = database.preparationDao.isRowIsExist(recipeId)
                if (!exists) {
                    val netData = Network.api.getPreparationData(recipeId)
                    val prepData = NetworkUtil.convertPreparationData(netData, category)
                    database.preparationDao.insertPreparation(prepData)
                } else {
                    //Do Nothing
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e(TAG, "Data Request Failed")

                }
            }

        }

    }

    private suspend fun refreshRecipesData(category: String) {
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
        private val TAG = MainRepository::class.java.simpleName
    }
}