package com.urbandictionary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.urbandictionary.data.database.dao.UrbanDao
import com.urbandictionary.data.database.entities.UrbanEntity

@Database(entities = [UrbanEntity::class], version = 1, exportSchema = false)
@TypeConverters(IntegerListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun urbanDao(): UrbanDao
}