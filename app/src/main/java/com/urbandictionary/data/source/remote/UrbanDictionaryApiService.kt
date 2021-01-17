package com.urbandictionary.data.source.remote

import com.urbandictionary.domain.model.UrbanDictionaryResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UrbanDictionaryApiService {

    companion object {
        const val keyValue = "x-rapidapi-key: cd1aaed060msh0d477fde70c7d7fp1c3278jsn78d25bc094aa"
        const val hostValue = "x-rapidapi-host: mashape-community-urban-dictionary.p.rapidapi.com"
    }

    @Headers(keyValue, hostValue)
    @GET("/define")
    suspend fun getDefine(@Query("term") term: String): UrbanDictionaryResponse
}