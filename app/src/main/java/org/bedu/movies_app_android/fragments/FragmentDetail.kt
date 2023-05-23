package org.bedu.movies_app_android.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.adapters.RecyclerMovieDetailAdapter
import org.bedu.movies_app_android.models.Movie
import org.bedu.movies_app_android.store.StoreSingleton

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentDetail.newInstance] factory method to
 * create an instance of this fragment.
 */

class FragmentDetail : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var recycler: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        recycler = view.findViewById(R.id.recyclerMovieDetail)

        val args = arguments
        if (args != null) {
            val myArray = args.getParcelableArray("arreglo")
            if (myArray != null) {
                // Utilizar el arreglo como desees
                val movies = myArray.filterIsInstance<Movie>()
                recycler.adapter =  RecyclerMovieDetailAdapter(movies)
            }
        }

        return view
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentDetail().apply {
                arguments = Bundle().apply {

                }
            }
    }
}