
package org.bedu.movies_app_android.domain.model

import android.os.Parcel
import android.os.Parcelable
import org.bedu.movies_app_android.data.database.entities.FavoriteMovieEntity
import org.bedu.movies_app_android.data.models.MovieResult

data class Movie(
        val adult: Boolean,
        val backdrop_path: String? = "",
        val id: Int,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String? = "",
        val release_date: String,
        val title: String,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int,
        var isFavorite: Boolean

): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readByte() != 0.toByte(),
                parcel.readString() ?: "",
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
                parcel.readByte() != 0.toByte(),


        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(backdrop_path)
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

        companion object CREATOR : Parcelable.Creator<org.bedu.movies_app_android.data.models.Movie> {
                override fun createFromParcel(parcel: Parcel): org.bedu.movies_app_android.data.models.Movie {
                        return org.bedu.movies_app_android.data.models.Movie(parcel)
                }

                override fun newArray(size: Int): Array<org.bedu.movies_app_android.data.models.Movie?> {
                        return arrayOfNulls(size)
                }
        }
}


fun FavoriteMovieEntity.toDomain() = Movie(adult, backdrop_path, id, original_language, original_title, overview, popularity, poster_path, release_date, title, video, vote_average, vote_count, isFavorite)
fun MovieResult.toDomain() = Movie(adult, backdrop_path, id, original_language, original_title, overview, popularity, poster_path, release_date, title, video, vote_average, vote_count, isFavorite)
