package org.bedu.movies_app_android.data.repository

import android.util.Log
import org.bedu.movies_app_android.data.database.dao.FavoriteMovieDao
import org.bedu.movies_app_android.data.database.entities.toDatabase
import org.bedu.movies_app_android.domain.model.Movie
import org.bedu.movies_app_android.domain.model.toDomain
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
) {

    suspend fun getAll(): List<Movie> {
        val response = favoriteMovieDao.getAll()
        return response.map { it.toDomain() }
    }

    suspend fun getIds(): List<Int> {
        val response = favoriteMovieDao.getAll()
        if (response.isEmpty()) {
            return emptyList()
        }
        return response.map { it.toDomain().id }
    }


    suspend fun insertOne(movie: Movie) {
        Log.d("LLEGO AQUI al repository", movie.toString())
        val newMovie = movie.toDatabase()
        newMovie.isFavorite = true

        return favoriteMovieDao.insertOne(newMovie)
    }


    suspend fun getOneById(id: Int): Movie? {
        val movie = favoriteMovieDao.getById(id)

        if (movie != null) {
            return movie.toDomain()
        }

        return null
    }


    suspend fun deleteOne(id: Int): Boolean {
        val movie = favoriteMovieDao.getById(id)

        if (movie == null) {
            return false
        }

        val response = favoriteMovieDao.deleteById(movie.id)

        if (response !== null) {

            Log.d("ALGO PASO AL ELIMINAR", response.toString())
            return true
        }

        return false

    }


}