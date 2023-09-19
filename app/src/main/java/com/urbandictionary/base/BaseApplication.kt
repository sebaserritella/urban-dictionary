package com.urbandictionary.base

import android.app.Application
import androidx.multidex.MultiDex
import com.urbandictionary.BuildConfig
import com.urbandictionary.di.AppModule
import com.urbandictionary.di.DatabaseModule
import com.urbandictionary.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@BaseApplication)
            modules(listOf(DatabaseModule, NetworkModule, AppModule))
        }
    }
}