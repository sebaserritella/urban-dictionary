package com.urbandictionary.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.urbandictionary.data.network.model.UrbanApiModel
import com.urbandictionary.data.network.model.UrbanDictionaryApiModel

class IntegerListConverter {

    @TypeConverter
    fun fromStringUrbanDictionaryResponse(value: String): List<UrbanDictionaryApiModel>? {
        val listType = object : TypeToken<List<UrbanDictionaryApiModel>?>() {}.type
        return Gson().fromJson<List<UrbanDictionaryApiModel>?>(value, listType)
    }

    @TypeConverter
    fun fromListUrbanDictionaryResponse(list: List<UrbanDictionaryApiModel>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringUrban(value: String): List<UrbanApiModel>? {
        val listType = object : TypeToken<List<UrbanApiModel>>() {}.type
        return Gson().fromJson<List<UrbanApiModel>>(value, listType)
    }

    @TypeConverter
    fun fromListUrban(list: List<UrbanApiModel>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromString(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson<List<String>>(value, listType)
    }

    @TypeConverter
    fun fromListString(list: List<String>?): String {
        if (list == null) return ""
        val gson = Gson()
        return gson.toJson(list)
    }
}
