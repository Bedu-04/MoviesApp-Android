package org.bedu.movies_app_android.ui.presenters

import org.bedu.movies_app_android.data.models.Cast
import org.bedu.movies_app_android.data.models.Crew
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.data.repository.DataRepository

interface FragmentDetailContract {

    interface View {
        // Define los métodos que el presentador puede llamar en la vista
        fun showData(cast: List<Cast>, director: Crew)
        // Otros métodos de la vista...
    }

    interface Presenter {
        // Define los métodos que la vista puede llamar en el presentador
        fun getMovieCastById(movieId: Int)
        // Otros métodos del presentador...
    }
}

class FragmentDetailPresenter(
    private val view: FragmentDetailContract.View,
    private val dataRepository: DataRepository<MovieResult>
) : FragmentDetailContract.Presenter {
    override fun getMovieCastById(movieId: Int) {
        dataRepository.getCastMovieById(movieId.toString()) { c, d ->
            view.showData(c, d)
        }
    }


}