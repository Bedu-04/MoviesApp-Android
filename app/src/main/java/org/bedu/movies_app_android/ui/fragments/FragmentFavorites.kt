package org.bedu.movies_app_android.ui.fragments

import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.ui.adapters.RecyclerFavoritesAdapter
import org.bedu.movies_app_android.domain.model.Movie
import org.bedu.movies_app_android.domain.useCases.MoviesFavoritesUseCase
import org.bedu.movies_app_android.domain.useCases.MoviesNextToSeeUseCase
import org.bedu.movies_app_android.store.StoreSingleton
import org.bedu.movies_app_android.ui.presenters.ENTITIES
import org.bedu.movies_app_android.ui.presenters.FragmentFavoritesContract
import org.bedu.movies_app_android.ui.presenters.FragmentFavoritesPresenter
import javax.inject.Inject
import javax.inject.Named
@AndroidEntryPoint
class FragmentFavorites : Fragment(), FragmentFavoritesContract.View {
    private lateinit var recycler: RecyclerView
    private lateinit var presenter: FragmentFavoritesContract.Presenter
    private lateinit var adapter : RecyclerFavoritesAdapter


    @Inject
    @Named("STORE")
    lateinit var store: StoreSingleton

    @Inject
    lateinit var moviesFavoritesUseCase: MoviesFavoritesUseCase

    @Inject
    lateinit var moviesNextToSeeUseCase: MoviesNextToSeeUseCase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = FragmentFavoritesPresenter(this, moviesFavoritesUseCase, moviesNextToSeeUseCase)
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

        if(favoritesMovies.isNotEmpty()){textFavorites.text = getString(R.string.your_favorites_movies)} else {textFavorites.text = getString(R.string.dont_have_your_favorites_movies)}

        adapter = RecyclerFavoritesAdapter(favoritesMovies as MutableList<Movie>, goToDetailFragment, toggleMoviesDB())

        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(activity, 3)

        runBlocking {
            presenter.getAll()
        }

        return view
    }


    private val goToDetailFragment:(org.bedu.movies_app_android.domain.model.Movie) -> Unit = { movie ->
        val nextFragment = FragmentDetail()
        val args = Bundle()
        val myFavoriteList = arrayOf<Movie>(movie)

        args.putParcelableArray("myFavoriteList", myFavoriteList)
        nextFragment.arguments = args
        parentFragmentManager.beginTransaction().replace(R.id.containerView, nextFragment).addToBackStack(null).commit()
    }


    private fun toggleMoviesDB() = fun (movie: Movie, isInsertOperation: Boolean, entity: ENTITIES) {
        CoroutineScope(Dispatchers.Main).launch {
            runBlocking{
                if (isInsertOperation) {
                    presenter.insertNewMovie(movie, entity)

                }else {
                    presenter.deleteMovieById(movie.id, entity)
                }
            }


        }

    }

    override fun showData(movies: List<org.bedu.movies_app_android.domain.model.Movie>) {
        Log.d("PELICULAS DE DB", movies.toString())

        adapter.movies = movies as MutableList<org.bedu.movies_app_android.domain.model.Movie>
    }




}