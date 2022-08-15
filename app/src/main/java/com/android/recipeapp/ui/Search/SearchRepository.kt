package com.android.recipeapp.ui.Search


import com.android.recipeapp.api.Api
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: Api,
) {

    suspend fun searchMeal(query: String) = api.getSearchMeal(query)

}