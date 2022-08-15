package com.android.recipeapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Categories(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String

) : Parcelable
