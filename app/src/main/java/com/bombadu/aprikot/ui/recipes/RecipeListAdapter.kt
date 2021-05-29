package com.bombadu.aprikot.ui.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.aprikot.R
import com.bombadu.aprikot.Recipes
import com.bombadu.aprikot.databinding.RecipeListItemBinding



class RecipeListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Recipes, RecipeListAdapter.RecipesViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<Recipes>() {
        override fun areItemsTheSame(oldItem: Recipes, newItem: Recipes): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Recipes, newItem: Recipes): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipesViewHolder {
        return RecipesViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipes = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(recipes)
        }
        holder.bind(recipes)
    }


    class RecipesViewHolder(private var binding: RecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup) : RecipesViewHolder {
                val binding: RecipeListItemBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.recipe_list_item,
                    parent,
                    false
                )

                return RecipesViewHolder(binding)
            }
        }
        fun bind(item: Recipes) {
            binding.recipes = item
            binding.executePendingBindings()
        }
    }


    class OnClickListener(val clickListener: (recipes: Recipes) -> Unit) {
        fun onClick(recipes: Recipes) = clickListener(recipes)
    }
}