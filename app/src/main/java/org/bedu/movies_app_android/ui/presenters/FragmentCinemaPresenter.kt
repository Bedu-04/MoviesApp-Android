package org.bedu.movies_app_android.ui.presenters

import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.data.repository.DataRepository


interface FragmentContract {
    interface View {
        // Define los métodos que el presentador puede llamar en la vista
        fun showData(data: List<MovieResult>)
        // Otros métodos de la vista...
    }

    interface Presenter {
        // Define los métodos que la vista puede llamar en el presentador
        fun fetchData()
        // Otros métodos del presentador...
    }
}

class FragmentCinemaPresenter(
    private val view: FragmentContract.View,
    private val dataRepository: DataRepository<MovieResult>
) : FragmentContract.Presenter {
    override fun fetchData() {
        dataRepository.getMovies { movies ->
            view.showData(movies)
        }
    }

}