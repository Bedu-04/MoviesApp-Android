package org.bedu.movies_app_android.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.RecyclerMovieCatalogAdapter
import org.bedu.movies_app_android.adapters.RecyclerMovieDBAdapter
import org.bedu.movies_app_android.api.MovieDbApi
import org.bedu.movies_app_android.models.Movie
import org.bedu.movies_app_android.models.MovieDB
import org.bedu.movies_app_android.models.MovieDBResult
import org.bedu.movies_app_android.models.MovieResult
import org.bedu.movies_app_android.store.StoreSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.lifecycleScope
import kotlin.concurrent.thread
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCinemaListings.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentCinemaListings : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var recycler: RecyclerView
    private var store = StoreSingleton.getInstance()
    // private var listener : (Product) ->Unit = {}
    // private var listener : (Movie) ->Unit = {}
    private var favorites = StoreSingleton.getInstance().getFavorites()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_cinema_listings, container, false)

        recycler = view.findViewById(R.id.recyclerCinemaListing)

        val adapter = RecyclerMovieDBAdapter(emptyList(), goToDetailFragment(), toggleFavorites())
        recycler.adapter = adapter

        if (store.moviesDB.size == 0 ) {
            moviesToAdapter(adapter)
        }

        adapter.movies = store.moviesDB


        recycler.layoutManager = GridLayoutManager(activity, 3)




        return view
    }


    private fun getProducts(): MutableList<Movie>{
        return StoreSingleton.getInstance().getData()
    }

    private fun moviesToAdapter(adapter: RecyclerMovieDBAdapter) {
        lifecycleScope.launch {
            val call = MovieDbApi.endpoint.getCinemaListings();

            call.enqueue(object: Callback<MovieDBResult>{
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<MovieDBResult>, response: Response<MovieDBResult>) {
                    val body = response.body()
                    val movies = body?.results;
                    if (movies !== null ){
                        adapter.movies = movies
                        store.moviesDB = movies as MutableList<MovieResult>
                        adapter.notifyDataSetChanged()
                    }


                }

                override fun onFailure(call: Call<MovieDBResult>, t: Throwable) {
                    Log.d("ERROR", t.toString())
                }

            })
        }
    }


    private fun goToDetailFragment() = fun (movie:MovieResult) {
        // Log.d("favorites", favorites.size.toString())

        // pasar al siguiente fragment la pelicula que se le dio click
        val nextFragment = FragmentDetail()
        val args = Bundle()
        val myFavoriteList = arrayOf<MovieResult>(movie)
        args.putParcelableArray("myFavoriteList", myFavoriteList)
        nextFragment.arguments = args
        parentFragmentManager.beginTransaction().replace(R.id.containerView, nextFragment).addToBackStack(null).commit()

    }

    private fun toggleFavorites() = fun (movie:MovieResult) {
        val hasMovie = store.getData().find { it -> (it.id == movie.id && it.isFavorite) }
        if (hasMovie !== null) {
            store.deleteFavoriteMovie(movie.id)
        }else {
            store.addFavoriteMovie(movie.id)
        }

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