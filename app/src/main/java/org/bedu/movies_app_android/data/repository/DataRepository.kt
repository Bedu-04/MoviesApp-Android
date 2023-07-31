package org.bedu.movies_app_android.data.repository

import org.bedu.movies_app_android.data.models.Cast
import org.bedu.movies_app_android.data.models.Crew
import org.bedu.movies_app_android.data.models.MovieResult

interface DataRepository<T> {
    fun getMovies(callback: (List<T>) -> Unit)

    fun getCastMovieById(movieId: String ,callback: (List<Cast>, Crew) -> Unit)

    fun getMoviesByName(movieName: String, callback: (List<T>) -> Unit)

    fun getMoviesByGenre(genres: Int, callback: (List<T>) -> Unit)
}
