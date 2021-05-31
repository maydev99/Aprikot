package com.bombadu.aprikot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Preparation(
    val recipeId: String,
    val recipeName: String,
    val recipeImageUrl: String,
    val isFavorite: Boolean,
    val recipeIngredients: String,
    val youtubeUrl: String,
    val recipeInstructions: String

) : Parcelable
