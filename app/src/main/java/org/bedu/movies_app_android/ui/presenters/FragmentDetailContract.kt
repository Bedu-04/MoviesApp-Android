package org.bedu.movies_app_android.ui.presenters

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.bedu.movies_app_android.data.models.Cast
import org.bedu.movies_app_android.data.models.Crew
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.data.repository.DataRepository

interface FragmentDetailContract {

    interface View {
        fun showData(cast: List<Cast>, director: Crew)

        fun showSimilarMovies(movies: List<MovieResult>)

    }

    interface Presenter {
        fun getMovieCastById(movieId: Int)

        fun getMoviesByGenre(genreId: Int)


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

    override fun getMoviesByGenre(genreId: Int) {
        dataRepository.getMoviesByGenre(genreId) { movies ->
            view.showSimilarMovies(movies)
        }

    }


}