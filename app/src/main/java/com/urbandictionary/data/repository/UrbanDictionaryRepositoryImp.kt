package com.urbandictionary.data.repository

import androidx.lifecycle.MutableLiveData
import com.urbandictionary.data.database.dao.UrbanDao
import com.urbandictionary.data.database.entities.asDomainModel
import com.urbandictionary.data.network.model.asDatabaseModel
import com.urbandictionary.data.network.model.asDomainModel
import com.urbandictionary.data.network.UrbanDictionaryApiService
import com.urbandictionary.domain.model.Urban
import com.urbandictionary.domain.model.UrbanDictionary
import com.urbandictionary.domain.repository.UrbanDictionaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UrbanDictionaryRepositoryImp(
    private val localDataSource: UrbanDao,
    private val remoteDataSource: UrbanDictionaryApiService
) : UrbanDictionaryRepository {

    override suspend fun getFromData(term: String): List<Urban> {
        return localDataSource.getDefine(term).asDomainModel()
    }

    override suspend fun getDefine(term: String) = withContext(Dispatchers.IO) {
        var liveData = MutableLiveData<UrbanDictionary>()
        val local = localDataSource.getDefine(term)

        liveData.postValue(UrbanDictionary(local.asDomainModel()))

        if (local.isEmpty()) {
            liveData = getFromRemoteDataSource(term)
        }

        liveData
    }

    override suspend fun getFromRemoteDataSource(term: String): MutableLiveData<UrbanDictionary> {
        val liveData = MutableLiveData<UrbanDictionary>()
        val remote = remoteDataSource.getDefine(term)
        liveData.postValue(remote.asDomainModel())

        remote.list?.let { list ->
            val entities = list.asDatabaseModel()

            entities.let {
                for (search in it) {
                    search.searchWord = term
                }
            }

            withContext(Dispatchers.IO) {
                try {
                    localDataSource.insertAllUrban(entities)

                } catch (e: Exception) {
                    println(e)
                }
            }
        }

        return liveData
    }
}