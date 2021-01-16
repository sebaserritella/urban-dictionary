package com.urbandictionary.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class UrbanDictionaryResponse(
    val list: List<Urban>?
)

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
data class Urban(
    val author: String?,
    val current_vote: String?,
    @PrimaryKey
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
