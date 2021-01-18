package com.urbandictionary.base

import android.app.Application
import androidx.multidex.MultiDex
import com.urbandictionary.BuildConfig
import com.urbandictionary.di.AppModule
import com.urbandictionary.di.NetworkModule
import com.urbandictionary.di.DatabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE
import timber.log.Timber

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            logger(object : Logger() {
                override fun log(level: Level, msg: MESSAGE) {
                    when (level) {
                        Level.DEBUG -> Timber.d(msg)
                        Level.INFO -> Timber.i(msg)
                        Level.ERROR -> Timber.e(msg)
                    }
                }
            })
            androidContext(this@BaseApplication)
            modules(DatabaseModule, NetworkModule, AppModule)
        }
    }
}