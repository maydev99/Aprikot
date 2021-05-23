package com.bombadu.aprikot.util

import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.network.CategoryData

fun List<CategoryData.Category>.toDatabaseModel(): List<CategoryEntity> {
    return map {
        CategoryEntity(
            categoryId = it.idCategory,
            categoryName = it.strCategory,
            categoryDescription = it.strCategoryDescription,
            categoryImageUrl = it.strCategoryThumb
        )
    }
}