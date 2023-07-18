package org.bedu.movies_app_android.ui.presenters

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.data.repository.DataRepository
import org.bedu.movies_app_android.domain.model.toDomain
import org.bedu.movies_app_android.domain.useCases.MoviesFavoritesUseCase


interface FragmentContract {
    interface View {
        // Define los métodos que el presentador puede llamar en la vista
        fun showData(data: List<org.bedu.movies_app_android.domain.model.Movie>, isSearch: Boolean)
    }

    interface Presenter {
        fun getMovies()

        fun searchMovieByName(movieName: String)

        suspend fun insertNewMovie(movie: org.bedu.movies_app_android.domain.model.Movie)

        suspend fun getMovieById(id: Int): org.bedu.movies_app_android.domain.model.Movie?

        suspend fun deleteMovieById(id: Int): Boolean
    }

}

class FragmentCinemaPresenter(
    private val view: FragmentContract.View,
    private val dataRepository: DataRepository<MovieResult>,
    private val moviesFavoritesUseCase: MoviesFavoritesUseCase,
) : FragmentContract.Presenter {
    override fun getMovies() {
        dataRepository.getMovies { movies ->
            CoroutineScope(Dispatchers.Main).launch {
                val ids = moviesFavoritesUseCase.getIds()
                Log.d("LLEGO AQUI", ids.toString())
                val favorites = movies.map { it.toDomain() }
                val moviesFavorites = favorites.map {
                    if (ids.contains(it.id)) {
                        it.isFavorite = true
                    }
                    it
                }
                view.showData(moviesFavorites, false)
            }

        }
    }

    override fun searchMovieByName(movieName: String) {
        dataRepository.getMoviesByName(movieName) { movies ->
            CoroutineScope(Dispatchers.Main).launch {
                val ids = moviesFavoritesUseCase.getIds()
                val moviesFavorites = movies.map {
                    if (ids.contains(it.id)) {
                        it.isFavorite = true
                    }
                    it.toDomain()
                }
                view.showData(moviesFavorites, true)
            }


        }
    }

    override suspend fun insertNewMovie(movie: org.bedu.movies_app_android.domain.model.Movie) {
        moviesFavoritesUseCase.insertOne(movie);
    }


    override suspend fun getMovieById(id: Int): org.bedu.movies_app_android.domain.model.Movie? {

        return withContext(Dispatchers.Main) {
            val movie = moviesFavoritesUseCase.getById(id);
            Log.d("getMovieById", movie?.original_title ?: "no encontre")
            movie
        }
    }

    override suspend fun deleteMovieById(id: Int): Boolean {
        return moviesFavoritesUseCase.deleteOneById(id)
    }


}



