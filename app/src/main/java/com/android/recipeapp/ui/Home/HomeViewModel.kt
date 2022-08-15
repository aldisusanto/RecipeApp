package com.android.recipeapp.ui.Home

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.*
import com.android.recipeapp.R
import com.android.recipeapp.RecipeApplication
import com.android.recipeapp.response.CategoriesResponse
import com.android.recipeapp.response.MealsResponse
import com.android.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    application: Application,
    private val app: Application
) : AndroidViewModel(application) {
    val categories: MutableLiveData<Resource<CategoriesResponse>> = MutableLiveData()

    val meals: MutableLiveData<Resource<MealsResponse>> = MutableLiveData()

    init {
        getCategories()
    }

    private fun getCategories() = viewModelScope.launch {
        safeCategoryMealCall()
    }

    private fun handleCategoriesMealsResponse(response: Response<CategoriesResponse>): Resource<CategoriesResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun getMealList(category: String) = viewModelScope.launch {
        safeMealsCall(category)
    }

    private fun handleMealsResponse(response: Response<MealsResponse>): Resource<MealsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun safeCategoryMealCall() {
        categories.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = homeRepository.getCategories()
                categories.postValue(handleCategoriesMealsResponse(response))

            } else {
                categories.postValue(Resource.Error(app.getString(R.string.error_message)))
            }

        } catch (t: Throwable) {
            when (t) {
                is IOException -> categories.postValue(Resource.Error("Network Failure"))
                else -> categories.postValue(Resource.Error("Conversion Error"))
            }

        }
    }

    private suspend fun safeMealsCall(category: String) {
        meals.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = homeRepository.getMeals(category)
                meals.postValue(handleMealsResponse(response))

            } else {
                meals.postValue(Resource.Error(app.getString(R.string.error_message)))
            }

        } catch (t: Throwable) {
            when (t) {
                is IOException -> meals.postValue(Resource.Error("Network Failure"))
                else -> meals.postValue(Resource.Error("Conversion Error"))
            }

        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<RecipeApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }

        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true

                    else -> false

                }
            }
        }
        return false

    }


}