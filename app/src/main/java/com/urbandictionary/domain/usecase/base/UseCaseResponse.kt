package com.urbandictionary.domain.usecase.base

import com.urbandictionary.domain.model.ApiError

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}

