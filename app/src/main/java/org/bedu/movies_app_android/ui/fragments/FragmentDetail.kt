package org.bedu.movies_app_android.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.ui.adapters.RecyclerMovieDetailAdapter
import org.bedu.movies_app_android.data.models.Cast
import org.bedu.movies_app_android.data.models.Crew
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.data.repository.MovieRepository
import org.bedu.movies_app_android.domain.model.Movie
import org.bedu.movies_app_android.store.StoreSingleton
import org.bedu.movies_app_android.ui.presenters.FragmentDetailContract
import org.bedu.movies_app_android.ui.presenters.FragmentDetailPresenter
import javax.inject.Inject
import javax.inject.Named

class FragmentDetail : Fragment(), FragmentDetailContract.View {
    private lateinit var adapter : RecyclerMovieDetailAdapter
    private lateinit var presenter: FragmentDetailContract.Presenter

    private lateinit var recycler: RecyclerView
    private var cast: MutableList<Cast> = mutableListOf()
    private var movieSelected:  List<Movie> = emptyList()
    private var director: Crew = Crew(profile_path = null)

    @Inject
    @Named("STORE")
    lateinit var store: StoreSingleton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieRepository = MovieRepository()
        presenter = FragmentDetailPresenter(this, movieRepository)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        recycler = view.findViewById(R.id.recyclerMovieDetail)

        val args = arguments
        if (args != null) {
            val myArray = args.getParcelableArray("myFavoriteList")
            if (myArray != null) {
                movieSelected = myArray.filterIsInstance<Movie>()
                Log.d("MOVIE SELECTED", movieSelected.toString())
                adapter = RecyclerMovieDetailAdapter(movieSelected, cast, director, emptyList(), goToDetailFragment())

                /*presenter.getMovieCastById(movieSelected[0].id)
                presenter.getMoviesByGenre(movieSelected[0].genre_ids[0])*/

                presenter.getAllInfo(movieSelected[0].id, movieSelected[0].genre_ids[0])
            }
        }
        return view
    }

    private fun goToDetailFragment() = fun (movie: Movie) {
        val nextFragment = FragmentDetail()
        val args = Bundle()
        val myFavoriteList = arrayOf<Movie>(movie)

        args.putParcelableArray("myFavoriteList", myFavoriteList)
        nextFragment.arguments = args
        parentFragmentManager.beginTransaction().replace(R.id.containerView, nextFragment).addToBackStack(null).commit()

    }


    @SuppressLint("NotifyDataSetChanged")
    override fun showData(cast: List<Cast>, director: Crew, similar: List<Movie>) {
        adapter.actors = cast
        adapter.director = director
        recycler.adapter = adapter
        adapter.similarMovies = similar
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun showSimilarMovies(movies: List<Movie>) {
        Log.d("SIMILAR", movies.toString())
        adapter.similarMovies = movies
        adapter.notifyDataSetChanged()
    }

}