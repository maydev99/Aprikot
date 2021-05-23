package com.bombadu.aprikot.repository

import android.util.Log
import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.local.LocalDatabase
import com.bombadu.aprikot.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val database: LocalDatabase) {

    suspend fun refreshCategoryData() {
        try {
            val networkData = Network.api.getCategories()
            val response = networkData.categories
            var newInsert: CategoryEntity
            //val list = mutableListOf<CategoryEntity>()

            for (i in response.indices) {
                val item = response[i]

                newInsert = CategoryEntity(
                    item.idCategory,
                    item.strCategory,
                    item.strCategoryDescription,
                    item.strCategoryThumb

                )

                Log.i(TAG, "INSERT: $newInsert")
                    // list.add(newInsert)

               //database.categoryDao.insertCategories(newInsert)
            }

            /*for (i in 0 until list.size) {
                database.categoryDao.insertCategories(list[i])
            }*/


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