package com.android.recipeapp.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class DetailResponse(
    @SerializedName("idMeal") @Expose val idMeal: String,
    @SerializedName("strMeal") @Expose val strMeal: String,
    @SerializedName("strMealThumb") @Expose val strMealThumb: String,
    @SerializedName("strArea") @Expose val strArea: String,
    @SerializedName("strIngredient1") @Expose val strIngredient1: String,
    @SerializedName("strMeasure1") @Expose val strMeasure1: String,
)