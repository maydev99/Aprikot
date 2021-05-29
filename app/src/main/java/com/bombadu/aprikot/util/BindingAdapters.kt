package com.bombadu.aprikot.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.aprikot.R
import com.bombadu.aprikot.Recipes
import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.local.RecipeEntity
import com.bombadu.aprikot.ui.categories.CategoryAdapter
import com.bombadu.aprikot.ui.recipes.RecipeListAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("categoryImage")
fun bindCategoryImageToItemImageView(imageView: ImageView, url: String) {
    Picasso.get().load(url)
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.placeholder)
        .into(imageView)
}
@BindingAdapter("setCatRecyclerView")
fun bindCatRecyclerView(recyclerView: RecyclerView, categoryEntity: List<CategoryEntity>?) {
    val adapter = recyclerView.adapter as CategoryAdapter
    adapter.submitList(categoryEntity)
}

@BindingAdapter("recipeListImage")
fun bindRecipeImageToItemImageView(imageView: ImageView, url: String) {
    Picasso.get().load(url)
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.placeholder)
        .into(imageView)
}

@BindingAdapter("setRecipeRecyclerView")
fun bindRecipeRecyclerView(recyclerView: RecyclerView, recipes: List<Recipes>?) {
    val adapter = recyclerView.adapter as RecipeListAdapter
    adapter.submitList(recipes)
}