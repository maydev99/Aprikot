package com.bombadu.aprikot.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.aprikot.R
import com.bombadu.aprikot.databinding.CategoryItemBinding
import com.bombadu.aprikot.local.CategoryEntity

class CategoryAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<CategoryEntity, CategoryAdapter.CategoryEntityViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<CategoryEntity>() {
        override fun areItemsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryEntityViewHolder {

        return CategoryEntityViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: CategoryEntityViewHolder, position: Int) {
        val category = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(category)
        }
        holder.bind(category)
    }

    class CategoryEntityViewHolder(private var binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup) : CategoryEntityViewHolder {
                val binding: CategoryItemBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.category_item,
                    parent,
                    false
                )

                return CategoryEntityViewHolder(binding)
            }
        }
        fun bind(item: CategoryEntity) {
            binding.category = item
            binding.executePendingBindings()
        }
    }


    class OnClickListener(val clickListener: (category: CategoryEntity) -> Unit) {
        fun onClick(category: CategoryEntity) = clickListener(category)
    }
}