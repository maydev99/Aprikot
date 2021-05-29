package com.bombadu.aprikot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipes(

    val recipeId: String,
    val recipeName: String,
    val recipeImageUrl: String,
    val recipeCategory: String,
    val recipeFavorite: Boolean

) : Parcelable
