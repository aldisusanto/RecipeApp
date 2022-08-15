package com.android.recipeapp.api

import com.android.recipeapp.model.*
import com.android.recipeapp.response.CategoriesResponse
import com.android.recipeapp.response.DetailResponse
import com.android.recipeapp.response.MealsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("categories.php")
    suspend fun getCategories(

    ): Response<CategoriesResponse>

    @GET("filter.php")
    suspend fun getMealList(
        @Query("c") category: String
    ): Response<MealsResponse>

    @GET("search.php")
    suspend fun getSearchMeal(
        @Query("s") query: String
    ): Response<MealsResponse>

    @GET("lookup.php")
    suspend fun getDetail(
        @Query("i") id: String
    ): Response<Meals>
}