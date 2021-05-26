package com.bombadu.aprikot.ui.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.aprikot.R
import com.bombadu.aprikot.databinding.RecipeListItemBinding
import com.bombadu.aprikot.local.RecipeEntity

class RecipeListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<RecipeEntity, RecipeListAdapter.RecipeEntityViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<RecipeEntity>() {
        override fun areItemsTheSame(oldItem: RecipeEntity, newItem: RecipeEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RecipeEntity, newItem: RecipeEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeEntityViewHolder {
        return RecipeEntityViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: RecipeEntityViewHolder, position: Int) {
        val recipes = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(recipes)
        }
        holder.bind(recipes)
    }


    class RecipeEntityViewHolder(private var binding: RecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup) : RecipeEntityViewHolder {
                val binding: RecipeListItemBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.recipe_list_item,
                    parent,
                    false
                )

                return RecipeEntityViewHolder(binding)
            }
        }
        fun bind(item: RecipeEntity) {
            binding.recipe = item
            binding.executePendingBindings()
        }
    }


    class OnClickListener(val clickListener: (recipes: RecipeEntity) -> Unit) {
        fun onClick(recipes: RecipeEntity) = clickListener(recipes)
    }
}