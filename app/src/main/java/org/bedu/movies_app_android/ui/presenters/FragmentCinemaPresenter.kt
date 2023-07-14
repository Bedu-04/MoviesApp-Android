package org.bedu.movies_app_android.ui.presenters

import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.data.repository.DataRepository


interface FragmentContract {
    interface View {
        // Define los métodos que el presentador puede llamar en la vista
        fun showData(data: List<MovieResult>, isSearch: Boolean)
        // Otros métodos de la vista...
    }

    interface Presenter {
        // Define los métodos que la vista puede llamar en el presentador
        fun getMovies()
        fun searchMovieByName(movieName: String)
    }
}

class FragmentCinemaPresenter(
    private val view: FragmentContract.View,
    private val dataRepository: DataRepository<MovieResult>
) : FragmentContract.Presenter {
    override fun getMovies() {
        dataRepository.getMovies { movies ->
            view.showData(movies, false)
        }
    }

    override fun searchMovieByName(movieName: String) {
        dataRepository.getMoviesByName(movieName) {movies ->
            view.showData(movies, true)
        }
    }


}