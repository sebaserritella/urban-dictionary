package com.urbandictionary.data.network

import com.urbandictionary.data.model.Article
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface HitApi {
    @GET("search_by_date?query=android")
    fun getArticleByDateAsync(): Deferred<Article>
}