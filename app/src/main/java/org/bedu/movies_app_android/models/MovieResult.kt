package org.bedu.movies_app_android.models

import android.os.Parcel
import android.os.Parcelable

data class MovieResult(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    var isFavorite: Boolean,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: "",
        (parcel.createStringArrayList() ?: emptyList()) as List<Int>,
        parcel.readInt()?: 0,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble() ?: 0.0,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
        parcel.readDouble() ?: 0.0,
        parcel.readInt()?: 0,
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(backdrop_path)
        parcel.writeList(genre_ids)
        parcel.writeInt(id)
        parcel.writeString(original_language)
        parcel.writeString(original_title)
        parcel.writeString(overview)
        parcel.writeDouble(popularity)
        parcel.writeString(poster_path)
        parcel.writeString(release_date)
        parcel.writeString(title)
        parcel.writeDouble(vote_average)
        parcel.writeInt(vote_count)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}