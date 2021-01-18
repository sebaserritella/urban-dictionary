package com.urbandictionary.data.source.remote

import com.urbandictionary.BuildConfig.KEY_HOST_VALUE
import com.urbandictionary.BuildConfig.KEY_VVALUE_API
import com.urbandictionary.domain.model.UrbanDictionaryResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UrbanDictionaryApiService {

    companion object {
        const val keyValue = "x-rapidapi-key: $KEY_VVALUE_API"
        const val hostValue = "x-rapidapi-host: $KEY_HOST_VALUE"
    }

    @Headers(keyValue, hostValue)
    @GET("/define")
    suspend fun getDefine(@Query("term") term: String): UrbanDictionaryResponse
}