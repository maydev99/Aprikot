package com.bombadu.aprikot.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "category_data_table", indices = [Index(value = ["categoryId", "categoryName"], unique = true)])
@Parcelize
data class CategoryEntity(
    @ColumnInfo(name = "categoryId") var categoryId: String,
    @ColumnInfo(name = "categoryName") var categoryName: String,
    @ColumnInfo(name = "categoryDescription") var categoryDescription: String,
    @ColumnInfo(name = "categoryImageUrl") var categoryImageUrl: String

) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


