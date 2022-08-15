package com.android.recipeapp.util

sealed class ResourceEvent {
    class Success<ResultType>(val resultList: List<ResultType>?, val result: ResultType?) :
        ResourceEvent()

    class Failure(val message: String) : ResourceEvent()
    object Loading : ResourceEvent()
    object Empty : ResourceEvent()
}