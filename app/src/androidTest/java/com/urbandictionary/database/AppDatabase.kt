package com.urbandictionary.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.urbandictionary.data.database.AppDatabase
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin

abstract class AppDatabase {
    lateinit var db: AppDatabase

    @Before
    fun initDB() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDB() {
        db.close()
        stopKoin()
    }
}
