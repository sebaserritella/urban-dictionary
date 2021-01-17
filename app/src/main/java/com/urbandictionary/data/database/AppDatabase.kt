package com.urbandictionary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.urbandictionary.domain.model.Urban
import com.urbandictionary.domain.model.UrbanDictionaryResponse

@Database(entities = [Urban::class], version = 1, exportSchema = false)
@TypeConverters(IntegerListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun urbanDao(): UrbanDao
}