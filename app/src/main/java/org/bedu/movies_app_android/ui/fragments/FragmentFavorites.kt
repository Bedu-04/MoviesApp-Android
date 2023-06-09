package org.bedu.movies_app_android.ui.fragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.bedu.movies_app_android.R

import org.bedu.movies_app_android.ui.adapters.RecyclerFavoritesAdapter
import org.bedu.movies_app_android.data.models.Movie
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.store.StoreSingleton
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class FragmentFavorites : Fragment() {
    private lateinit var recycler: RecyclerView
    private var store = StoreSingleton.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*        arguments?.let {
            var param1 = it.getString(ARG_PARAM1)
            var param2 = it.getString(ARG_PARAM2)
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        recycler = view.findViewById(R.id.recyclerFavorites)

        val favoritesMovies = store.getFavorites()

        val textFavorites = view.findViewById<TextView>(R.id.text_wo_favorites)

        if(favoritesMovies.size > 0){textFavorites.text = getString(R.string.your_favorites_movies)} else {textFavorites.text = getString(R.string.dont_have_your_favorites_movies)}

        recycler.adapter = RecyclerFavoritesAdapter(favoritesMovies, goToDetailFragment,toggleMovie)

        recycler.layoutManager = GridLayoutManager(activity, 3)

        return view
    }


    private val goToDetailFragment:(MovieResult) -> Unit = { movie: MovieResult ->
        val nextFragment = FragmentDetail()
        val args = Bundle()
        val myArray = arrayOf(movie)
        args.putParcelableArray("myFavoriteList", myArray)
        nextFragment.arguments = args
        parentFragmentManager.beginTransaction().replace(R.id.containerView, nextFragment).addToBackStack(null).commit()
    }


    private val toggleMovie:(MovieResult) -> Unit = { movie: MovieResult ->
        val hasMovie = store.getFavorites().find { it -> (it.id == movie.id && it.isFavorite) }
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
         * @return A new instance of fragment FragmentFavorites.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentFavorites().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}