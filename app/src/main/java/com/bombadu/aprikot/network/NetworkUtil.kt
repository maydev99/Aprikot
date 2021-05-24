package com.bombadu.aprikot.network

import com.bombadu.aprikot.local.CategoryEntity

object NetworkUtil {

    fun convertCategoryData(categoryData: CategoryData) : List<CategoryEntity> {
        val item = categoryData.categories
        val myList = mutableListOf<CategoryEntity>()
        for (i in item.indices) {
            val catId = item[i].idCategory
            val catName = item[i].strCategory
            val catDescription = item[i].strCategoryDescription
            val catImageUrl = item[i].strCategoryThumb

            myList.add(CategoryEntity(catId, catName, catDescription, catImageUrl))
        }

        return myList
    }
}