package com.android.recipeapp.response

import com.android.recipeapp.model.Categories
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoriesResponse(

    @SerializedName("categories")@Expose val category: MutableList<Categories>

)