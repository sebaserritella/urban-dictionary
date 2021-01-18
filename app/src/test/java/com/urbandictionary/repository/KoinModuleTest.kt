package com.urbandictionary.repository

import com.urbandictionary.di.AppModule
import com.urbandictionary.di.NetworkModule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

class KoinModuleTest : AutoCloseKoinTest() {

    @Test
    fun testCoreModule() {
        koinApplication {
            printLogger(Level.DEBUG)
            modules(listOf(AppModule, NetworkModule))
        }.checkModules()
    }

}