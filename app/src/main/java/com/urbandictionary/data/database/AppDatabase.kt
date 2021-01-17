package com.urbandictionary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.urbandictionary.data.model.Article
import com.urbandictionary.data.model.Urban

@Database(entities = [Urban::class], version = 0, exportSchema = false)
@TypeConverters(IntegerListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun urbanDao(): UrbanDao
}