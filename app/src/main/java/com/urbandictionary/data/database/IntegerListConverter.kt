package com.urbandictionary.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.urbandictionary.data.model.Urban

class IntegerListConverter {

    @TypeConverter
    fun fromStringUrban(value: String): List<Urban>? {
        val listType = object : TypeToken<List<Urban>>() {}.type
        return Gson().fromJson<List<Urban>>(value, listType)
    }

    @TypeConverter
    fun fromListUrban(list: List<Urban>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromString(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson<List<String>>(value, listType)
    }

    @TypeConverter
    fun fromListString(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
