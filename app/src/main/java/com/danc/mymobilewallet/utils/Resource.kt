package com.danc.mymobilewallet.utils

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ResponseBody
import org.json.JSONObject

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val errorBody: ResponseBody?,
        val isNetworkError: Boolean,
        val errorCode: Int?,

        ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}

