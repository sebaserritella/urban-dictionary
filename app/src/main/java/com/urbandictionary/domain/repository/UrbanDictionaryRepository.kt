package com.urbandictionary.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbandictionary.domain.model.Urban
import com.urbandictionary.domain.model.UrbanDictionaryResponse

interface UrbanDictionaryRepository {

    suspend fun getLocalDefine(term: String): LiveData<UrbanDictionaryResponse>
    suspend fun getFromData(term: String): List<Urban>
    suspend fun getRemote(term: String): MutableLiveData<UrbanDictionaryResponse>
}