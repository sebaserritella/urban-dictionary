package com.urbandictionary.data.repository

import com.urbandictionary.data.model.UrbanDictionaryResponse
import com.urbandictionary.data.source.remote.UrbanDictionaryApiService
import com.urbandictionary.domain.repository.UrbanDictionaryRepository

class UrbanDictionaryRepositoryImp(private val urbanDictionaryApiService: UrbanDictionaryApiService) : UrbanDictionaryRepository {

    override suspend fun getDefine(term: String): UrbanDictionaryResponse {
        return urbanDictionaryApiService.getDefine(term)
    }
}