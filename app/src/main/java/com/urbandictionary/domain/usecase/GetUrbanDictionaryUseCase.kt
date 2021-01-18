package com.urbandictionary.domain.usecase

import androidx.lifecycle.LiveData
import com.urbandictionary.domain.model.UrbanDictionaryResponse
import com.urbandictionary.domain.repository.UrbanDictionaryRepository
import com.urbandictionary.domain.usecase.base.UseCase

class GetUrbanDictionaryUseCase constructor(
    private val urbanDictionaryRepository: UrbanDictionaryRepository
) : UseCase<LiveData<UrbanDictionaryResponse>, Any?>() {

    override suspend fun run(params: Any?): LiveData<UrbanDictionaryResponse> {
        return urbanDictionaryRepository.getLocalDefine(params as String)
    }


}
