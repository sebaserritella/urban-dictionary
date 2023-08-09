package com.urbandictionary.domain.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UrbanDictionaryResponse(
    val list: List<Urban>?
)

@Entity
@JsonClass(generateAdapter = true)
data class Urban(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var searchWord: String?,
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(searchWord)
        parcel.writeString(author)
        parcel.writeString(current_vote)
        parcel.writeValue(defid)
        parcel.writeString(definition)
        parcel.writeString(example)
        parcel.writeString(permalink)
        parcel.writeStringList(sound_urls)
        parcel.writeValue(thumbs_down)
        parcel.writeValue(thumbs_up)
        parcel.writeString(word)
        parcel.writeString(written_on)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Urban> {
        override fun createFromParcel(parcel: Parcel): Urban {
            return Urban(parcel)
        }

        override fun newArray(size: Int): Array<Urban?> {
            return arrayOfNulls(size)
        }
    }
}
