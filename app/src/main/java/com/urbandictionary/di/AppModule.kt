package com.urbandictionary.di

import com.urbandictionary.data.database.UrbanDao
import com.urbandictionary.data.repository.UrbanDictionaryRepositoryImp
import com.urbandictionary.data.source.remote.UrbanDictionaryApiService
import com.urbandictionary.domain.repository.UrbanDictionaryRepository
import com.urbandictionary.domain.usecase.GetUrbanDictionaryUseCase
import com.urbandictionary.presentation.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { SearchViewModel(get()) }

    single { createGetUrbanDictionaryUseCase(get()) }

    single { createDictionaryRepository(get(), get()) }
}

fun createDictionaryRepository(urbanDao: UrbanDao, urbanDictionaryApiService: UrbanDictionaryApiService): UrbanDictionaryRepository {
    return UrbanDictionaryRepositoryImp(urbanDao, urbanDictionaryApiService)
}

fun createGetUrbanDictionaryUseCase(urbanDictionaryRepository: UrbanDictionaryRepository): GetUrbanDictionaryUseCase {
    return GetUrbanDictionaryUseCase(urbanDictionaryRepository)
}
