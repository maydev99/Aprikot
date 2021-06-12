package com.bombadu.aprikot.ui.categories

import android.util.Log
import androidx.lifecycle.LiveData
import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.local.LocalDatabase
import com.bombadu.aprikot.network.Network
import com.bombadu.aprikot.network.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriesRepository(private val database: LocalDatabase) {

    val categoryData: LiveData<List<CategoryEntity>> = database.categoryDao.getAllCategories()

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
        private val TAG = CategoriesRepository::class.java.simpleName
    }

}