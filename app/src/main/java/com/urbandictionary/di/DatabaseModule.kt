package com.urbandictionary.di

import androidx.room.Room
import com.urbandictionary.R
import com.urbandictionary.data.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val DatabaseModule = module {
    single {
        val db: AppDatabase = get()
        db.urbanDao()
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

