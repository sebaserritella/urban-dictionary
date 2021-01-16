package com.urbandictionary.injection

import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.urbandictionary.BuildConfig
import com.urbandictionary.R
import com.urbandictionary.data.database.AppDatabase
import com.urbandictionary.data.network.HitApi
import com.urbandictionary.data.repository.ArticleRepository
import com.urbandictionary.presentation.article.ArticleListViewModel
import com.urbandictionary.presentation.search.SearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Modules {
    private val networkModule = module {
        single {
            val retrofit: Retrofit = get()
            retrofit.create(HitApi::class.java)
        }
        single {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }
    }

    private val databaseModule = module {
        single {
            val db: AppDatabase = get()
            db.articleDao()
        }
        single {
            Room.databaseBuilder(
                get(),
                AppDatabase::class.java,
                androidApplication().getString(R.string.database)
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    private val repositoryModule = module {
        single { ArticleRepository(get(), get()) }
    }

    private val viewModelModule = module {
        viewModel { ArticleListViewModel(get()) }
        viewModel { SearchViewModel(get()) }
    }

    val all: List<Module> = listOf(
        databaseModule,
        viewModelModule,
        networkModule,
        repositoryModule
    )
}