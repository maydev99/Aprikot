package com.bombadu.aprikot.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "preparation_data_table",
    indices = [Index(value = ["recipeId", "recipeName"], unique = true)]
)
data class PreparationEntity(
    @ColumnInfo(name = "recipeId") var recipeId: String,
    @ColumnInfo(name = "recipeName") var recipeName: String,
    @ColumnInfo(name = "recipeImageUrl") var recipeImageUrl: String,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean,
    @ColumnInfo(name = "recipeIngredients") var recipeIngredients: String,
    @ColumnInfo(name = "youtubeUrl") var youtubeUrl: String,
    @ColumnInfo(name = "recipeInstructions") var recipeInstructions: String

) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

