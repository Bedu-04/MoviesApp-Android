package org.bedu.movies_app_android.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.RecyclerMovieCatalogAdapter
import org.bedu.movies_app_android.adapters.RecyclerMovieAdapter
import org.bedu.movies_app_android.models.Category
import org.bedu.movies_app_android.models.Language
import org.bedu.movies_app_android.models.Movie
import org.bedu.movies_app_android.store.StoreSingleton

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

        recycler.adapter = RecyclerMovieCatalogAdapter(getProducts(), fun (movie:Movie) {
            // Log.d("favorites", favorites.size.toString())

            // pasar al siguiente fragment la pelicula que se le dio click
            val nextFragment = FragmentDetail()
            val args = Bundle()
            val miArreglo = arrayOf(movie)
            args.putParcelableArray("arreglo", miArreglo)
            nextFragment.arguments = args
            parentFragmentManager.beginTransaction().replace(R.id.containerView, nextFragment).addToBackStack(null).commit()

        }, fun (movie:Movie) {
            var hasMovie = store.getFavorites().find { it -> it.id == movie.id }
            if (hasMovie !== null) {
                store.deleteFavoriteMovie(movie)
            }else {
                store.addFavoriteMovie(movie)
            }

        }
        )

        recycler.layoutManager = GridLayoutManager(activity, 3)


        return view
    }

    //generamos datos dummy con este método
    private fun getProducts(): Array<Movie>{
        return StoreSingleton.getInstance().getData()
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