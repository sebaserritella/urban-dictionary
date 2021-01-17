package com.urbandictionary.domain.usecase

import com.urbandictionary.domain.model.UrbanDictionaryResponse
import com.urbandictionary.domain.repository.UrbanDictionaryRepository
import com.urbandictionary.domain.usecase.base.UseCase

class GetUrbanDictionaryUseCase constructor(
    private val urbanDictionaryRepository: UrbanDictionaryRepository
) : UseCase<UrbanDictionaryResponse, Any?>() {

    override suspend fun run(params: Any?): UrbanDictionaryResponse {
        val a = urbanDictionaryRepository.getFromData(params as String)

        if (a.value != null) {
            return UrbanDictionaryResponse(a.value)
        }

        return urbanDictionaryRepository.getDefine(params as String)
    }


}
