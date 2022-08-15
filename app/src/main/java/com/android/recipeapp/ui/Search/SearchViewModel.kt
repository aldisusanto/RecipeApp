package com.android.recipeapp.ui.Search

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.android.recipeapp.R
import com.android.recipeapp.RecipeApplication
import com.android.recipeapp.response.MealsResponse
import com.android.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    application: Application,
    private val app: Application
) :
    AndroidViewModel(application) {

    val searchResult: MutableLiveData<Resource<MealsResponse>> = MutableLiveData()

    fun getSearchMeal(query: String) = viewModelScope.launch {
        safeMealsCall(query)
    }

    private fun handleSearchMealsResponse(response: Response<MealsResponse>): Resource<MealsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun safeMealsCall(query: String) {
        searchResult.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = searchRepository.searchMeal(query)
                searchResult.postValue(handleSearchMealsResponse(response))

            } else {
                searchResult.postValue(Resource.Error(app.getString(R.string.error_message)))
            }

        } catch (t: Throwable) {
            when (t) {
                is IOException -> searchResult.postValue(Resource.Error("Network Failure"))
                else -> searchResult.postValue(Resource.Error("Conversion Error"))
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
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true

                    else -> false

                }
            }
        }
        return false

    }


}