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
import org.bedu.movies_app_android.domain.useCases.MoviesNextToSeeUseCase


enum class ENTITIES {
    FAVORITES, NEXT_TO_SEE
}

interface FragmentContract {
    interface View {
        // Define los métodos que el presentador puede llamar en la vista
        fun showData(data: List<org.bedu.movies_app_android.domain.model.Movie>, isSearch: Boolean)
    }

    interface Presenter {
        fun getMovies()

        fun searchMovieByName(movieName: String)

        suspend fun insertNewMovie(movie: org.bedu.movies_app_android.domain.model.Movie, entity: ENTITIES)

        suspend fun getMovieById(id: Int): org.bedu.movies_app_android.domain.model.Movie?

        suspend fun deleteMovieById(id: Int, entity: ENTITIES)
    }

}

class FragmentCinemaPresenter(
    private val view: FragmentContract.View,
    private val dataRepository: DataRepository<MovieResult>,
    private val moviesFavoritesUseCase: MoviesFavoritesUseCase,
    private val moviesNextToSeeUseCase: MoviesNextToSeeUseCase,
) : FragmentContract.Presenter {
    override fun getMovies() {
        dataRepository.getMovies { movies ->
            CoroutineScope(Dispatchers.Main).launch {
                val favIds = moviesFavoritesUseCase.getIds()
                val nextIds = moviesNextToSeeUseCase.getIds()

                val favorites = movies.map { it.toDomain() }
                val moviesFavorites = favorites.map {
                    if (favIds.contains(it.id)) {
                        it.isFavorite = true
                    }

                    if (nextIds.contains(it.id)) {
                        it.isNextToSee = true
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
                val favIds = moviesFavoritesUseCase.getIds()
                val nextIds = moviesNextToSeeUseCase.getIds()
                val moviesFavorites = movies.map {
                    if (favIds.contains(it.id)) {
                        it.isFavorite = true
                    }

                    if (nextIds.contains(it.id)) {
                        it.IsNextToSee = true
                    }
                    it.toDomain()
                }
                view.showData(moviesFavorites, true)
            }


        }
    }

    override suspend fun insertNewMovie(movie: org.bedu.movies_app_android.domain.model.Movie, entity: ENTITIES) {
        Log.d("new movie", entity.toString())

        if (entity == ENTITIES.FAVORITES) {
            moviesFavoritesUseCase.insertOne(movie);
        }

        if(entity == ENTITIES.NEXT_TO_SEE) {
            moviesNextToSeeUseCase.insertOne(movie);
        }


    }


    override suspend fun getMovieById(id: Int): org.bedu.movies_app_android.domain.model.Movie? {

        return withContext(Dispatchers.Main) {
            val movie = moviesFavoritesUseCase.getById(id);
            Log.d("getMovieById", movie?.original_title ?: "no encontre")
            movie
        }
    }

    override suspend fun deleteMovieById(id: Int, entity: ENTITIES) {

        if (entity == ENTITIES.FAVORITES) {
            moviesNextToSeeUseCase.deleteOneById(id);
        }

        if (entity == ENTITIES.NEXT_TO_SEE) {
            moviesNextToSeeUseCase.deleteOneById(id);
        }
    }


}



