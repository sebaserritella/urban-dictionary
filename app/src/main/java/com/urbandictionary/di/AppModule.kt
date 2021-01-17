package com.urbandictionary.di

import com.urbandictionary.presentation.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { SearchViewModel(get()) }

    single { createGetUrbanDictionaryUseCase(get()) }

    single { createDictionaryRepository(get(), get()) }
}

