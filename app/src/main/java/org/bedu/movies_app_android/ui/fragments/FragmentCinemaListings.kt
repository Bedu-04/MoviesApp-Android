package org.bedu.movies_app_android.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.RecyclerMovieCatalogAdapter
import org.bedu.movies_app_android.ui.adapters.RecyclerMovieDBAdapter
import org.bedu.movies_app_android.data.network.api.MovieDbApi
import org.bedu.movies_app_android.data.models.Movie
import org.bedu.movies_app_android.data.models.MovieDB
import org.bedu.movies_app_android.data.models.MovieDBResult
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.store.StoreSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bedu.movies_app_android.data.models.CreditsResult
import org.bedu.movies_app_android.data.repository.MovieRepository
import org.bedu.movies_app_android.ui.presenters.FragmentCinemaPresenter
import org.bedu.movies_app_android.ui.presenters.FragmentContract
import kotlin.concurrent.thread
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCinemaListings.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

interface OnSearchListener {
    fun onSearch(query: String, resetMovies: Boolean = false)
}

class FragmentCinemaListings : Fragment(), OnSearchListener,FragmentContract.View {
    private var store = StoreSingleton.getInstance()
    private lateinit var recycler: RecyclerView
    private lateinit var adapter : RecyclerMovieDBAdapter
    private lateinit var presenter: FragmentContract.Presenter
    private var searchListener: OnSearchListener? = null
    private var isLoading = MutableLiveData<Boolean>(false)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieRepository = MovieRepository()
        presenter = FragmentCinemaPresenter(this, movieRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_cinema_listings, container, false)

        val progressBar = view.findViewById<ProgressBar>(R.id.progress);

        progressBar.isVisible = true;

        recycler = view.findViewById(R.id.recyclerCinemaListing)

        adapter = RecyclerMovieDBAdapter(emptyList(), goToDetailFragment(), toggleFavorites())

        recycler.adapter = adapter

        if (store.moviesDB.size == 0 ) {
            presenter.getMovies()
        }

        adapter.movies = store.moviesDB

        progressBar.isVisible = false;

        recycler.layoutManager = GridLayoutManager(activity, 3)

        return view
    }

    private fun goToDetailFragment() = fun (movie: MovieResult) {
        val nextFragment = FragmentDetail()
        val args = Bundle()
        val myFavoriteList = arrayOf<MovieResult>(movie)

        args.putParcelableArray("myFavoriteList", myFavoriteList)
        nextFragment.arguments = args
        parentFragmentManager.beginTransaction().replace(R.id.containerView, nextFragment).addToBackStack(null).commit()

    }

    private fun toggleFavorites() = fun (movie: MovieResult) {
        val hasMovie = store.moviesDB.find { it -> (it.id == movie.id && it.isFavorite) }
        if (hasMovie !== null) {
            store.deleteFavoriteMovie(movie.id)
        }else {
            store.addFavoriteMovie(movie.id)
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    override fun showData(data: List<MovieResult>, isSearch: Boolean) {
        adapter.movies = data
        if (isSearch) store.searchMoviesDB = data as MutableList<MovieResult>
        else store.moviesDB = data as MutableList<MovieResult>
        adapter.notifyDataSetChanged()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun resetData() {
        adapter.movies = store.moviesDB
        adapter.notifyDataSetChanged()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            searchListener = context as OnSearchListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement OnSearchListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        searchListener = null
    }

    override fun onSearch(query: String, resetMovies: Boolean) {
        if (!resetMovies){
            presenter.searchMovieByName(query)
            return
        }
        Log.d("REINICIO", "debo reiniciar pelis")
        resetData()



    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentCinemaListings.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCinemaListings().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}