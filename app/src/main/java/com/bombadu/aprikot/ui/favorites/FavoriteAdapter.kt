package com.bombadu.aprikot.ui.favorites

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.aprikot.R
import com.bombadu.aprikot.local.PreparationEntity
import com.bombadu.aprikot.local.RecipeEntity
import com.bombadu.aprikot.ui.preparation.PreparationActivity
import com.squareup.picasso.Picasso


class FavoriteAdapter :
    ListAdapter<PreparationEntity, FavoriteAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_list_item, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView = itemView.findViewById<ImageView>(R.id.favorite_item_image_view)
        private val titleTextView = itemView.findViewById<TextView>(R.id.favorite_item_text_view)

        fun bind(item: PreparationEntity) = with(itemView) {
            Picasso.get().load(item.recipeImageUrl).into(imageView)
            titleTextView.text = item.recipeName

            setOnClickListener {
                val intent = Intent(context, PreparationActivity::class.java)

                val recipeItem = RecipeEntity(
                    item.recipeId, item.recipeName, item.recipeImageUrl,
                    item.recipeCategory, item.isFavorite
                )

                intent.putExtra("selected_recipe", recipeItem)
                context.startActivity(intent)

            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<PreparationEntity>() {

    override fun areItemsTheSame(oldItem: PreparationEntity, newItem: PreparationEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: PreparationEntity,
        newItem: PreparationEntity
    ): Boolean {
        return oldItem == newItem
    }
}