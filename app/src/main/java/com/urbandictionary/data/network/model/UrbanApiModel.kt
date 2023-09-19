package com.urbandictionary.data.network.model

import com.squareup.moshi.JsonClass
import com.urbandictionary.data.database.entities.UrbanEntity
import com.urbandictionary.domain.model.Urban

@JsonClass(generateAdapter = true)
data class UrbanApiModel(
    val id: Long?,
    var searchWord: String? = null,
    val author: String? = null,
    val current_vote: String? = null,
    val defid: Int? = null,
    val definition: String? = null,
    val example: String? = null,
    val permalink: String? = null,
    val sound_urls: List<String>? = null,
    val thumbs_down: Int? = null,
    val thumbs_up: Int? = null,
    val word: String? = null,
    val written_on: String? = null
)

fun UrbanApiModel.asDatabaseModel(): UrbanEntity {
    return UrbanEntity(
        id = id,
        searchWord = searchWord,
        author = author,
        current_vote = current_vote,
        defid = defid,
        definition = definition,
        example = example,
        permalink = permalink,
        sound_urls = sound_urls,
        thumbs_down = thumbs_down,
        thumbs_up = thumbs_up
    )
}

fun List<UrbanApiModel>.asDatabaseModel(): List<UrbanEntity> {
    return this.map { it.asDatabaseModel() }
}

fun UrbanApiModel.asDomainModel(): Urban {
    return Urban(
        id = id,
        searchWord = searchWord,
        author = author,
        current_vote = current_vote,
        defid = defid,
        definition = definition,
        example = example,
        permalink = permalink,
        sound_urls = sound_urls,
        thumbs_down = thumbs_down,
        thumbs_up = thumbs_up
    )
}


fun List<UrbanApiModel>.asDomainModel(): List<Urban> {
    return this.map { it.asDomainModel() }
}