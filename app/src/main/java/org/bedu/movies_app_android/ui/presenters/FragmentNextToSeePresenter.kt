package org.bedu.movies_app_android.ui.presenters

import android.util.Log
import org.bedu.movies_app_android.domain.model.Movie
import org.bedu.movies_app_android.domain.useCases.MoviesFavoritesUseCase
import org.bedu.movies_app_android.domain.useCases.MoviesNextToSeeUseCase


interface FragmentNextToSeeContract {
    interface View {
        // Define los métodos que el presentador puede llamar en la vista
        fun showData(movies: List<Movie>)
    }

    interface Presenter {
        suspend fun insertNewMovie(movie: Movie, entity: ENTITIES)

        suspend fun deleteMovieById(id: Int, entity: ENTITIES)

        suspend fun getAll(): List<Movie>
    }

}

class FragmentNextToPresenter(
    private val view: FragmentNextToSeeContract.View,
    private val moviesNextToSeeUseCase: MoviesNextToSeeUseCase,
    private val moviesFavoritesUseCase: MoviesFavoritesUseCase,
) : FragmentNextToSeeContract.Presenter {
    override suspend fun insertNewMovie(movie: Movie, entity: ENTITIES) {
        if (entity == ENTITIES.NEXT_TO_SEE) moviesNextToSeeUseCase.insertOne(movie)

        if (entity == ENTITIES.FAVORITES) moviesFavoritesUseCase.insertOne(movie)
    }

    override suspend fun getAll(): List<Movie> {
        val movies = moviesNextToSeeUseCase.getAll()
        Log.d("YTRAIGO", movies.size.toString())
        view.showData(movies)

        return  movies

    }

    override suspend fun deleteMovieById(id: Int, entity: ENTITIES) {
        if (entity == ENTITIES.NEXT_TO_SEE) moviesNextToSeeUseCase.deleteOneById(id)

        if (entity == ENTITIES.FAVORITES) moviesFavoritesUseCase.deleteOneById(id)
    }


}



