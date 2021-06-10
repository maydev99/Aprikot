package com.bombadu.aprikot.ui.recipes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.bombadu.aprikot.Recipes
import com.bombadu.aprikot.local.LocalDatabase
import com.bombadu.aprikot.network.Network
import com.bombadu.aprikot.network.NetworkUtil
import com.bombadu.aprikot.util.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RecipesRepository(private val database: LocalDatabase) {


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
            val exists = database.preparationDao.isRowIsExist(recipeId)
            if (!exists) {
                val netData = Network.api.getPreparationData(recipeId)
                val prepData = NetworkUtil.convertPreparationData(netData, category)
                database.preparationDao.insertPreparation(prepData)
            }
        }

    }


    suspend fun refreshRecipesData(category: String) {
        try {

            val mealIdList = mutableListOf<String>()
            val networkData = Network.api.getRecipesByCategory(category)
            val recipeData = NetworkUtil.convertRecipeData(networkData, category, false)


            for (i in recipeData.indices) {
                withContext(Dispatchers.IO) {
                    database.recipeDao.insertRecipes(recipeData[i])
                    //Make list of recipe ids
                    //mealIdList.add(recipeData[i].recipeId)
                }
            }

            /*
                  Pre-Caching Preparation Data
                  Using recipe id list, call network and get preparation data
                  for each id, then save to local db.
            */

            /* for (i in 0 until mealIdList.size) {
                 withContext(Dispatchers.IO) {
                     val id = mealIdList[i]
                     val netData = Network.api.getPreparationData(id)
                     val prepData = NetworkUtil.convertPreparationData(netData, category)

                     withContext(Dispatchers.IO) {
                         database.preparationDao.insertPreparation(prepData)

                     }
                 }
             }*/


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