package org.bedu.movies_app_android.models

import android.os.Parcel
import android.os.Parcelable

enum class Category {
    Comedia, Drama, Suspenso, Terror, Acción, CienciaFicción, Fantasía, Animación
}

enum class Language {
    Español, Inglés, Francés, Coreano
}

data class Movie (
    val id: Int,
    val name: String,
    val actors: List<String>,
    val directors: List<String>,
    val duration: Double,
    val date: String,
    val rating: Double,
    val language: Language,
    val category: Category,
    val resume: String,
    val image: Int,
    var isFavorite: Boolean,

): Parcelable  {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: emptyList(),
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readDouble(),
        Language.values()[parcel.readInt()],
        Category.values()[parcel.readInt()],
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeStringList(actors)
        parcel.writeStringList(directors)
        parcel.writeDouble(duration)
        parcel.writeString(date)
        parcel.writeDouble(rating)
        parcel.writeInt(language.ordinal)
        parcel.writeInt(category.ordinal)
        parcel.writeString(resume)
        parcel.writeInt(image)

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


