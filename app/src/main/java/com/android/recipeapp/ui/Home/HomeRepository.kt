package com.android.recipeapp.ui.Home

import com.android.recipeapp.api.Api
import com.android.recipeapp.api.RetrofitClient
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: Api) {
    suspend fun getCategories() = api.getCategories()

    suspend fun getMeals(category: String) = RetrofitClient.api.getMealList(category)

}