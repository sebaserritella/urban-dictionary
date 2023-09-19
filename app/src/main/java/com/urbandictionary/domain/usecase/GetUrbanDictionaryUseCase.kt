package com.urbandictionary.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbandictionary.domain.model.UrbanDictionary
import com.urbandictionary.domain.repository.UrbanDictionaryRepository
import com.urbandictionary.domain.usecase.base.UseCase

class GetUrbanDictionaryUseCase constructor(
    private val urbanDictionaryRepository: UrbanDictionaryRepository
) : UseCase<LiveData<UrbanDictionary>, Params.ParameterTerm?>() {

    override suspend fun run(params: Params.ParameterTerm?): LiveData<UrbanDictionary> {
        return params?.term?.let {
            urbanDictionaryRepository.getDefine(params.term)
        } ?: MutableLiveData()
    }
}

sealed class Params {
    data class ParameterTerm(val term: String?) : Params()
}
