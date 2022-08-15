package com.android.recipeapp.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "food_favorite_table")
data class Favorite(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val strMeal: String,
    val strThumb: String
) : Parcelable