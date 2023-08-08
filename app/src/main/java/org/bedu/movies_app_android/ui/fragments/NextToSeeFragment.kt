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
import org.bedu.movies_app_android.ui.adapters.RecyclerFavoritesAdapter
import org.bedu.movies_app_android.domain.model.Movie
import org.bedu.movies_app_android.domain.useCases.MoviesFavoritesUseCase
import org.bedu.movies_app_android.domain.useCases.MoviesNextToSeeUseCase
import org.bedu.movies_app_android.store.StoreSingleton
import org.bedu.movies_app_android.ui.presenters.ENTITIES
import org.bedu.movies_app_android.ui.presenters.FragmentNextToPresenter
import org.bedu.movies_app_android.ui.presenters.FragmentNextToSeeContract
import javax.inject.Inject
import javax.inject.Named


@AndroidEntryPoint
class NextToSeeFragment : Fragment(), FragmentNextToSeeContract.View {
    private lateinit var recycler: RecyclerView
    private lateinit var presenter: FragmentNextToSeeContract.Presenter
    private lateinit var adapter : RecyclerFavoritesAdapter


    @Inject
    @Named("STORE")
    lateinit var store: StoreSingleton

    @Inject
    lateinit var moviesNextToSeeUseCase: MoviesNextToSeeUseCase

    @Inject
    lateinit var moviesFavoritesUseCase: MoviesFavoritesUseCase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = FragmentNextToPresenter(this, moviesNextToSeeUseCase, moviesFavoritesUseCase)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        recycler = view.findViewById(R.id.recyclerFavorites)

        val textFavorites = view.findViewById<TextView>(R.id.text_wo_favorites)

        textFavorites.text = getString(R.string.string_next_to_see);
        adapter = RecyclerFavoritesAdapter(emptyList(), goToDetailFragment, toggleMoviesDB())

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
        adapter.movies = movies as MutableList<org.bedu.movies_app_android.domain.model.Movie>
    }




}
