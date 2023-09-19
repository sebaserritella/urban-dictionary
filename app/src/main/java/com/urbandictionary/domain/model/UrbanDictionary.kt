package com.urbandictionary.domain.model

data class UrbanDictionary(
    val list: List<Urban>? = null,
)

data class Urban(
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