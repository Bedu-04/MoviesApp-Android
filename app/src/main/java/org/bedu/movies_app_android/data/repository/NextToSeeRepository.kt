package org.bedu.movies_app_android.data.repository

import org.bedu.movies_app_android.data.database.dao.NextToSeeMovieDao
import android.util.Log
import org.bedu.movies_app_android.data.database.entities.toNextDatabase
import org.bedu.movies_app_android.domain.model.Movie
import org.bedu.movies_app_android.domain.model.toDomain
import javax.inject.Inject

class NextToSeeRepository @Inject constructor(
    private val nextToSeeDao: NextToSeeMovieDao
) {

    suspend fun getAll(): List<Movie> {
        val response = nextToSeeDao.getAll()
        return response.map { it.toDomain() }
    }

    suspend fun getIds(): List<Int> {
        val response = nextToSeeDao.getAll()
        if (response.isEmpty()) {
            return emptyList()
        }
        return response.map { it.toDomain().id }
    }


    suspend fun insertOne(movie: Movie) {
        Log.d("LLEGO AQUI al repository", movie.toString())
        val newMovie = movie.toNextDatabase()
        newMovie.isFavorite = true

        return nextToSeeDao.insertOne(newMovie)
    }


    suspend fun getOneById(id: Int): Movie? {
        val movie = nextToSeeDao.getById(id)

        if (movie != null) {
            return movie.toDomain()
        }

        return null
    }


    suspend fun deleteOne(id: Int): Boolean {
        val movie = nextToSeeDao.getById(id)

        if (movie == null) {
            return false
        }

        val response = nextToSeeDao.deleteById(movie.id)

        if (response !== null) {

            Log.d("ALGO PASO AL ELIMINAR", response.toString())
            return true
        }

        return false

    }


}