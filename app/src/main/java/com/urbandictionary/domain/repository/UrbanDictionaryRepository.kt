package com.urbandictionary.domain.repository

import com.urbandictionary.data.model.UrbanDictionaryResponse


interface UrbanDictionaryRepository {

    suspend fun getDefine(term: String): UrbanDictionaryResponse
}