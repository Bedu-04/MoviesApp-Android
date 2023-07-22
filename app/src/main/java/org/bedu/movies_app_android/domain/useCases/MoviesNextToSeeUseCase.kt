package org.bedu.movies_app_android.domain.useCases

import android.util.Log

import org.bedu.movies_app_android.data.repository.NextToSeeRepository
import org.bedu.movies_app_android.domain.model.Movie
import javax.inject.Inject

class MoviesNextToSeeUseCase @Inject constructor(
    private val repository: NextToSeeRepository
) {

    suspend fun getAll():List<Movie> {
        return repository.getAll()
    }

    suspend fun getById(id: Int):Movie? {
        return repository.getOneById(id)
    }

    suspend fun getIds():List<Int> {
        return repository.getIds()
    }

    suspend fun insertOne(movie: Movie) {
        Log.d("LLEGO AQUI al invoke", movie.original_title)
        return repository.insertOne(movie)
    }

    suspend fun deleteOneById(id: Int):Boolean {
        return repository.deleteOne(id)
    }

}