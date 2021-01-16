package com.urbandictionary.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.urbandictionary.data.model.Hit

class IntegerListConverter {

    @TypeConverter
    fun fromString(value: String): List<Hit>? {
        val listType = object : TypeToken<List<Hit>>() {}.type
        return Gson().fromJson<List<Hit>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Hit>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
