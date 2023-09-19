package com.urbandictionary.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.urbandictionary.data.network.model.UrbanApiModel
import com.urbandictionary.domain.model.Urban

@Entity
data class UrbanEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0L,
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

fun UrbanEntity.asDomainModel(): Urban {
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

fun List<UrbanEntity>.asDomainModel(): List<Urban> {
    return this.map { it.asDomainModel() }
}


fun UrbanEntity.asApiModel(): UrbanApiModel {
    return UrbanApiModel(
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

fun List<UrbanEntity>.asApiModel(): List<UrbanApiModel> {
    return this.map { it.asApiModel() }
}