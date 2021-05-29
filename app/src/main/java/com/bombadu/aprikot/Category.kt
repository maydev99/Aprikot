package com.bombadu.aprikot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Category(
    val categoryId: String,
    val categoryName: String,
    val categoryDescription: String,
    val categoryImageUrl: String,
) : Parcelable


/*
@ColumnInfo(name = "categoryId") var categoryId: String,
    @ColumnInfo(name = "categoryName") var categoryName: String,
    @ColumnInfo(name = "categoryDescription") var categoryDescription: String,
    @ColumnInfo(name = "categoryImageUrl") var categoryImageUrl: String
 */