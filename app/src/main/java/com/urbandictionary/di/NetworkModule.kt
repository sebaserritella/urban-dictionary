package com.urbandictionary.di

import com.squareup.moshi.Moshi
import com.urbandictionary.BuildConfig
import com.urbandictionary.data.repository.UrbanDictionaryRepositoryImp
import com.urbandictionary.data.source.remote.UrbanDictionaryApiService
import com.urbandictionary.domain.repository.UrbanDictionaryRepository
import com.urbandictionary.domain.usecase.GetPostsUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), BuildConfig.BASE_URL) }

    single { createOkHttpClient() }

    single { MoshiConverterFactory.create() }

    single { Moshi.Builder().build() }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun createService(retrofit: Retrofit): UrbanDictionaryApiService {
    return retrofit.create(UrbanDictionaryApiService::class.java)
}

fun createPostRepository(urbanDictionaryApiService: UrbanDictionaryApiService): UrbanDictionaryRepository {
    return UrbanDictionaryRepositoryImp(urbanDictionaryApiService)
}

fun createGetPostsUseCase(urbanDictionaryRepository: UrbanDictionaryRepository): GetPostsUseCase {
    return GetPostsUseCase(urbanDictionaryRepository)
}
