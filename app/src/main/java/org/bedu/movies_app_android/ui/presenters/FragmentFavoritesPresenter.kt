package org.bedu.movies_app_android.ui.presenters

import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.domain.model.Movie
import org.bedu.movies_app_android.domain.useCases.MoviesFavoritesUseCase


interface FragmentFavoritesContract {

    interface View {
        fun showData(movies: List<Movie>)
    }

    interface Presenter {
        suspend fun insertNewMovie(movie: Movie)
        suspend fun deleteMovieById(id: Int): Boolean
        suspend fun getAll(): List<Movie>

    }
}

class FragmentFavoritesPresenter(
    private val view: FragmentFavoritesContract.View,
    private val moviesFavoritesUseCase: MoviesFavoritesUseCase,
) : FragmentFavoritesContract.Presenter {
    override suspend fun insertNewMovie(movie: Movie) {
        moviesFavoritesUseCase.insertOne(movie)
    }

    override suspend fun getAll(): List<Movie> {
        val movies = moviesFavoritesUseCase.getAll()

        view.showData(movies)

        return  movies

    }

    override suspend fun deleteMovieById(id: Int): Boolean {
        return moviesFavoritesUseCase.deleteOneById(id)
    }


}