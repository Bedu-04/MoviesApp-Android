package org.bedu.movies_app_android.ui.presenters

import org.bedu.movies_app_android.data.models.Cast
import org.bedu.movies_app_android.data.models.Crew
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.data.repository.DataRepository
import org.bedu.movies_app_android.domain.model.Movie
import org.bedu.movies_app_android.domain.model.toDomain

interface FragmentDetailContract {

    interface View {
        fun showData(cast: List<Cast>, director: Crew, similar: List<Movie>)

        fun showSimilarMovies(movies: List<Movie>)

    }

    interface Presenter {
        fun getMovieCastById(movieId: Int)

        fun getMoviesByGenre(genreId: Int)

        fun getAllInfo(movieId: Int,genreId: Int)


    }
}

class FragmentDetailPresenter(
    private val view: FragmentDetailContract.View,
    private val dataRepository: DataRepository<MovieResult>
) : FragmentDetailContract.Presenter {
    override fun getMovieCastById(movieId: Int) {
        dataRepository.getCastMovieById(movieId.toString()) { c, d ->
            view.showData(c, d, emptyList())
        }
    }

    override fun getMoviesByGenre(genreId: Int) {
        dataRepository.getMoviesByGenre(genreId) { movies ->
            val moviesDomain = movies.map { it -> it.toDomain() }
            view.showSimilarMovies(moviesDomain)
        }

    }

    override fun getAllInfo(movieId: Int, genreId: Int) {
        dataRepository.getCastMovieById(movieId.toString()) { c, d ->
            dataRepository.getMoviesByGenre(genreId) { movies ->
                val moviesDomain = movies.map { it -> it.toDomain() }
                // view.showSimilarMovies(moviesDomain)
                view.showData(c, d, moviesDomain)
            }

        }
    }


}