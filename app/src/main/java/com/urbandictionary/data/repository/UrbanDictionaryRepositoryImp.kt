package com.urbandictionary.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbandictionary.data.database.UrbanDao
import com.urbandictionary.data.source.remote.UrbanDictionaryApiService
import com.urbandictionary.domain.model.UrbanDictionaryResponse
import com.urbandictionary.domain.repository.UrbanDictionaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UrbanDictionaryRepositoryImp(
    private val localDataSource: UrbanDao,
    private val remoteDataSource: UrbanDictionaryApiService
) : UrbanDictionaryRepository {

    override suspend fun getFromData(term: String) = localDataSource.getDefine(term)

    override suspend fun getLocalDefine(term: String) = withContext(Dispatchers.IO) {
        var liveData = MutableLiveData<UrbanDictionaryResponse>()
        val local = localDataSource.getDefine(term)
        if (local.isEmpty()){
           liveData = getRemote(term)
        }

        liveData
    }

    override suspend fun getRemote(term: String): MutableLiveData<UrbanDictionaryResponse> {
        val liveData = MutableLiveData<UrbanDictionaryResponse>()
        val remote = remoteDataSource.getDefine(term)
        liveData.postValue(remote)

        withContext(Dispatchers.IO) {
            localDataSource.insertAllUrban(remote.list)
            //Log.i("insertando ", (remote.list.toString()))
        }

        return liveData
    }
}