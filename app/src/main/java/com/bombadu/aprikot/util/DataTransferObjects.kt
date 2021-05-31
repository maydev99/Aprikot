package com.bombadu.aprikot.util

import com.bombadu.aprikot.Preparation
import com.bombadu.aprikot.Recipes
import com.bombadu.aprikot.local.PreparationEntity
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


fun PreparationEntity.toDomainModel(): Preparation {
    return Preparation(
        recipeId = this.recipeId,
        recipeName = this.recipeName,
        recipeImageUrl = this.recipeImageUrl,
        isFavorite = this.isFavorite,
        recipeIngredients = this.recipeIngredients,
        youtubeUrl = this.youtubeUrl,
        recipeInstructions = this.recipeInstructions
    )
}

