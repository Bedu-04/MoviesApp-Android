package org.bedu.movies_app_android.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.domain.model.Movie

@Entity(tableName = "next_to_see_movies")
@TypeConverters(IntArrayConverter::class)
data class NextToSeeMovieEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "original_language")
    val original_language: String,
    @ColumnInfo(name = "original_title")
    val original_title: String,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String? = "",
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    val poster_path: String? = "",
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "video")
    val video: Boolean,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int,
    @ColumnInfo(name = "genre_ids")
    val genre_ids: List<Int>,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean,
    @ColumnInfo(name = "isNextToSee")
    var isNextToSee: Boolean,

    )

fun Movie.toNextDatabase() = NextToSeeMovieEntity( id, original_language, original_title, adult, backdrop_path, overview, popularity, poster_path, release_date, title, video, vote_average, vote_count, genre_ids  ,isFavorite, isNextToSee = true)