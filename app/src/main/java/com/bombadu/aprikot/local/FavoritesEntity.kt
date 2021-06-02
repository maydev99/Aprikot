package com.bombadu.aprikot.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize


//Delete********
@Parcelize
@Entity(
    tableName = "favorites_data_table",
    indices = [Index(value = ["recId", "isFavorite"], unique = true)]

)
class FavoritesEntity(
    @ColumnInfo(name = "recId") var recId: String,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}
