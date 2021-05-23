package com.bombadu.aprikot.network


data class CategoryData(
    var categories: List<Category>
) {
    data class Category(
        var idCategory: String,
        var strCategory: String,
        var strCategoryDescription: String,
        var strCategoryThumb: String
    )
}