package com.urbandictionary.domain.usecase

import com.urbandictionary.data.model.UrbanDictionaryResponse
import com.urbandictionary.domain.repository.UrbanDictionaryRepository
import com.urbandictionary.domain.usecase.base.UseCase

class GetPostsUseCase constructor(
    private val urbanDictionaryRepository: UrbanDictionaryRepository
) : UseCase<UrbanDictionaryResponse, Any?>() {

    override suspend fun run(params: Any?): UrbanDictionaryResponse {
        return urbanDictionaryRepository.getDefine("perro")
    }

}