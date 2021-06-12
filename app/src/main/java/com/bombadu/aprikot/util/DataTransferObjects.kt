package com.bombadu.aprikot.util

import com.bombadu.aprikot.Recipes
import com.bombadu.aprikot.local.RecipeEntity

fun List<RecipeEntity>.toDomainModel(): List<Recipes> {
    return map {
        Recipes(
            recipeId = it.recipeId,
            recipeName = it.recipeName,
            recipeImageUrl = it.recipeImageUrl,
            recipeCategory = it.category,
            recipeFavorite = it.isFavorite
        )
    }
}

