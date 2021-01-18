package com.urbandictionary.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class UrbanDictionaryResponse(
    val list: List<Urban>?
)

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
data class Urban(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val author: String?,
    val current_vote: String?,
    val defid: Int?,
    val definition: String?,
    val example: String?,
    val permalink: String?,
    val sound_urls: List<String>?,
    val thumbs_down: Int?,
    val thumbs_up: Int?,
    val word: String?,
    val written_on: String?
) : Parcelable
