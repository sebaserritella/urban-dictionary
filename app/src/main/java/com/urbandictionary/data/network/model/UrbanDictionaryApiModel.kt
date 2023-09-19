package com.urbandictionary.data.network.model

import com.squareup.moshi.JsonClass
import com.urbandictionary.domain.model.UrbanDictionary

@JsonClass(generateAdapter = true)
data class UrbanDictionaryApiModel(
    val list: List<UrbanApiModel>?
)


fun UrbanDictionaryApiModel.asDomainModel(): UrbanDictionary {
    return UrbanDictionary(
        list = list?.asDomainModel()
    )
}

fun List<UrbanDictionaryApiModel>.asDomainModel(): List<UrbanDictionary> {
    return this.map { it.asDomainModel() }
}