package com.bombadu.aprikot.network

import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.local.RecipeEntity

object NetworkUtil {


    fun convertRecipeData(recipesData: RecipesData, category: String, isFavorite: Boolean) : List<RecipeEntity> {
        val item = recipesData.meals
        val myList = mutableListOf<RecipeEntity>()
        for (i in item.indices) {
            val recipeId = item[i].idMeal
            val recipeName = item[i].strMeal
            val recipeImageUrl = item[i].strMealThumb

            myList.add(RecipeEntity(recipeId, recipeName, recipeImageUrl, category, isFavorite))
        }

        return myList
    }

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