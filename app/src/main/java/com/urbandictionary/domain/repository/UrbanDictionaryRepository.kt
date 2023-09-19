package com.urbandictionary.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbandictionary.domain.model.Urban
import com.urbandictionary.domain.model.UrbanDictionary

interface UrbanDictionaryRepository {

    suspend fun getDefine(term: String): LiveData<UrbanDictionary>
    suspend fun getFromData(term: String): List<Urban>
    suspend fun getFromRemoteDataSource(term: String): MutableLiveData<UrbanDictionary>
}