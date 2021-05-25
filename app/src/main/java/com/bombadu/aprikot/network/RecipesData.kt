package com.bombadu.aprikot.network


data class RecipesData(
    var meals: List<Meal>
) {
    data class Meal(
        var idMeal: String,
        var strMeal: String,
        var strMealThumb: String
    )
}