package com.urbandictionary.data.repository

import android.util.Log
import com.urbandictionary.data.database.UrbanDao
import com.urbandictionary.data.source.remote.UrbanDictionaryApiService
import com.urbandictionary.domain.model.UrbanDictionaryResponse
import com.urbandictionary.domain.repository.UrbanDictionaryRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class UrbanDictionaryRepositoryImp(
    private val urbanDao: UrbanDao,
    private val urbanDictionaryApiService: UrbanDictionaryApiService
) : UrbanDictionaryRepository {

    override fun getFromData(term: String) = urbanDao.getDefine(term)

    override suspend fun getDefine(term: String): UrbanDictionaryResponse {
        val define = urbanDictionaryApiService.getDefine(term)

        withContext(IO){
            define.list?.let {
                urbanDao.insertAllUrban(it)
                Log.i("insertando ", it.toString())
            }
        }

        return define
    }
}