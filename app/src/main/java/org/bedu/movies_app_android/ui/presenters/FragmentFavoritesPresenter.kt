package org.bedu.movies_app_android.ui.presenters

import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.domain.model.Movie
import org.bedu.movies_app_android.domain.useCases.MoviesFavoritesUseCase
import org.bedu.movies_app_android.domain.useCases.MoviesNextToSeeUseCase


interface FragmentFavoritesContract {

    interface View {
        fun showData(movies: List<Movie>)
    }

    interface Presenter {
        suspend fun insertNewMovie(movie: Movie, entity: ENTITIES)
        suspend fun deleteMovieById(id: Int, entity: ENTITIES)
        suspend fun getAll(): List<Movie>

    }
}

class FragmentFavoritesPresenter(
    private val view: FragmentFavoritesContract.View,
    private val moviesFavoritesUseCase: MoviesFavoritesUseCase,
    private val moviesNextToSeeUseCase: MoviesNextToSeeUseCase,
) : FragmentFavoritesContract.Presenter {
    override suspend fun insertNewMovie(movie: Movie, entity: ENTITIES) {
        if (entity == ENTITIES.NEXT_TO_SEE) moviesNextToSeeUseCase.insertOne(movie)

        if (entity == ENTITIES.FAVORITES) moviesFavoritesUseCase.insertOne(movie)
    }

    override suspend fun getAll(): List<Movie> {
        val movies = moviesFavoritesUseCase.getAll()

        view.showData(movies)

        return  movies

    }

    override suspend fun deleteMovieById(id: Int, entity: ENTITIES) {
        if (entity == ENTITIES.NEXT_TO_SEE) moviesNextToSeeUseCase.deleteOneById(id)

        if (entity == ENTITIES.FAVORITES) moviesFavoritesUseCase.deleteOneById(id)
    }


}