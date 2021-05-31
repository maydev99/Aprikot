package com.bombadu.aprikot.network

import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.local.PreparationEntity
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





    fun convertPreparationData(preparationData: PreparationData): PreparationEntity {
        val item = preparationData.meals
        val ingredientList = mutableListOf<String>()
        val measurementList = mutableListOf<String>()
        val combinedList = mutableListOf<String>()

        /**
         * Converts all ingredient and measurement data into
         * a single string (recipeIngredients)
         */


        /*
         * Gets Ingredients and Measurements from API
         * adds to ingredients and measurements list
         */

        ingredientList.add(item[0].strIngredient1)
        ingredientList.add(item[0].strIngredient2)
        ingredientList.add(item[0].strIngredient3)
        ingredientList.add(item[0].strIngredient4)
        ingredientList.add(item[0].strIngredient5)
        ingredientList.add(item[0].strIngredient6)
        ingredientList.add(item[0].strIngredient7)
        ingredientList.add(item[0].strIngredient8)
        ingredientList.add(item[0].strIngredient9)
        ingredientList.add(item[0].strIngredient10)
        ingredientList.add(item[0].strIngredient11)
        ingredientList.add(item[0].strIngredient12)
        ingredientList.add(item[0].strIngredient13)
        ingredientList.add(item[0].strIngredient14)
        ingredientList.add(item[0].strIngredient15)
        ingredientList.add(item[0].strIngredient16)
        ingredientList.add(item[0].strIngredient17)
        ingredientList.add(item[0].strIngredient18)
        ingredientList.add(item[0].strIngredient19)
        ingredientList.add(item[0].strIngredient20)

        measurementList.add(item[0].strMeasure1)
        measurementList.add(item[0].strMeasure2)
        measurementList.add(item[0].strMeasure3)
        measurementList.add(item[0].strMeasure4)
        measurementList.add(item[0].strMeasure5)
        measurementList.add(item[0].strMeasure6)
        measurementList.add(item[0].strMeasure7)
        measurementList.add(item[0].strMeasure8)
        measurementList.add(item[0].strMeasure9)
        measurementList.add(item[0].strMeasure10)
        measurementList.add(item[0].strMeasure11)
        measurementList.add(item[0].strMeasure12)
        measurementList.add(item[0].strMeasure13)
        measurementList.add(item[0].strMeasure14)
        measurementList.add(item[0].strMeasure15)
        measurementList.add(item[0].strMeasure16)
        measurementList.add(item[0].strMeasure17)
        measurementList.add(item[0].strMeasure18)
        measurementList.add(item[0].strMeasure19)
        measurementList.add(item[0].strMeasure20)

        //Eliminate Null Values

        ingredientList.removeAll<String?>(listOf(null))
       // measurementList.removeAll(listOf("null"))
        ingredientList.removeAll(listOf(""))
        measurementList.removeAll(listOf(""))



        /*
         * Code Below takes the measurements list and
         * combines with the ingredients list. Even though
         * both lists may be different sizes
         */

        //Get Measurement list size
        val measListSize = measurementList.size - 1

        //Iterates through Ingredient List (same or longer than measurement list)
        for (i in 0 until ingredientList.size) {
            val ingredients = ingredientList[i]

            //Check if measurement exists for the ingredient
            val combined = if (i <= measListSize) {
                val measurements = measurementList[i]
                "$measurements: $ingredients" //concatenates if both exist
            } else {
                ingredients //if no measurement data
            }

            //Adds each ingredient/measurement to one list
            combinedList.add(combined)
        }

        /*
         * Appends each ingredient for a recipe to a single string
         * with line breaks
         */

        val strBuilder = StringBuilder()
        for (i in 0 until combinedList.size) {
            val line = combinedList[i]
            strBuilder.appendLine(line)
        }
        //Put it all in one string
        val recipeIngredients = strBuilder.toString()

        //Add data to local db model

        return PreparationEntity(
            item[0].idMeal,
            item[0].strMeal,
            item[0].strMealThumb,
            false,
            recipeIngredients,
            item[0].strYoutube,
            item[0].strInstructions
        )

    }


}