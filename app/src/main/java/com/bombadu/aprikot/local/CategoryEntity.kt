package com.bombadu.aprikot.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "category_data_table", indices = [Index(value = ["categoryId", "categoryName"], unique = true)])
data class CategoryEntity(
    @ColumnInfo(name = "categoryId") var categoryId: String,
    @ColumnInfo(name = "categoryName") var categoryName: String,
    @ColumnInfo(name = "categoryDescription") var categoryDescription: String,
    @ColumnInfo(name = "categoryImageUrl") var categoryImageUrl: String

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


