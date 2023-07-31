package org.bedu.movies_app_android.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager

import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.ui.adapters.RecyclerMovieDBAdapter

import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.store.StoreSingleton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

import org.bedu.movies_app_android.data.repository.MovieRepository
import org.bedu.movies_app_android.databinding.FragmentCinemaListingsBinding
import org.bedu.movies_app_android.domain.model.Movie
import org.bedu.movies_app_android.domain.useCases.MoviesFavoritesUseCase
import org.bedu.movies_app_android.ui.presenters.FragmentCinemaPresenter
import org.bedu.movies_app_android.ui.presenters.FragmentContract
import javax.inject.Inject
import javax.inject.Named

interface OnSearchListener {
    fun onSearch(query: String, resetMovies: Boolean = false)
}

@AndroidEntryPoint
class FragmentCinemaListings : Fragment(), OnSearchListener,FragmentContract.View {
    @Inject
    @Named("STORE")
    lateinit var store: StoreSingleton

    @Inject
    lateinit var moviesFavoritesUseCase: MoviesFavoritesUseCase

    private lateinit var adapter : RecyclerMovieDBAdapter
    private lateinit var presenter: FragmentContract.Presenter
    private var searchListener: OnSearchListener? = null
    private lateinit var binding: FragmentCinemaListingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieRepository = MovieRepository()
        presenter = FragmentCinemaPresenter(this, movieRepository, moviesFavoritesUseCase)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCinemaListingsBinding.inflate(inflater, container, false)

        val view = binding.root

        binding.progress.isVisible = true;

        val recycler = binding.recyclerCinemaListing

        adapter = RecyclerMovieDBAdapter(emptyList(), goToDetailFragment(), toggleFavorites())

        recycler.adapter = adapter


        presenter.getMovies()

        // adapter.movies = store.moviesDB



        binding.progress.isVisible = false;

        recycler.layoutManager = GridLayoutManager(activity, 3)

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

    private fun toggleFavorites() = fun (movie: Movie, isInsertOperation: Boolean) {
        Log.e("PORQUE ME AGREGO?", isInsertOperation.toString())


        CoroutineScope(Dispatchers.Main).launch {
            // val hasMovie = presenter.getMovieById(movie.id)
            runBlocking{

                if (isInsertOperation) {
                    presenter.insertNewMovie(movie)

                }else {
                    presenter.deleteMovieById(movie.id)
                }
            }


        }

    }


    @SuppressLint("NotifyDataSetChanged")
    override fun showData(data: List<Movie>, isSearch: Boolean) {
        adapter.movies = data
        if (isSearch) store.searchMoviesDB = data as MutableList<Movie>
        else store.moviesDB = data as MutableList<Movie>
        adapter.notifyDataSetChanged()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun resetData() {
        /*Log.d("RESET", store.moviesDB.map { it.id }.toString())
        */
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
        } else {
            presenter.getMovies()
            Log.d("REINICIO", "debo reiniciar pelis")
            // resetData()
        }

    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        Button btn1 = view.findViewById(R.id.nav_cinema_listings);
        Button btn2 = view.findViewById(R.id.nav_favorite_movies);

        final NavController navController=Navigation.findNavController(view);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(v).navigate(R.id.fragmentCinemaListings2);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                navController.navigate(R.id.fragmentFavorites);
            }
        });

    }
}