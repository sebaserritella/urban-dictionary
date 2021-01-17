package com.urbandictionary.domain.repository

import androidx.lifecycle.LiveData
import com.urbandictionary.domain.model.Urban
import com.urbandictionary.domain.model.UrbanDictionaryResponse


interface UrbanDictionaryRepository {

    suspend fun getDefine(term: String): UrbanDictionaryResponse
    fun getFromData(term: String): LiveData<List<Urban>?>
}