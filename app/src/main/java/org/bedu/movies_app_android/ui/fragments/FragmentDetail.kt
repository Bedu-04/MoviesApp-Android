package org.bedu.movies_app_android.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.ui.adapters.RecyclerMovieDetailAdapter
import org.bedu.movies_app_android.data.network.api.MovieDbApi
import org.bedu.movies_app_android.data.models.Cast
import org.bedu.movies_app_android.data.models.CreditsResult
import org.bedu.movies_app_android.data.models.Crew
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.data.repository.MovieRepository
import org.bedu.movies_app_android.ui.presenters.FragmentDetailContract
import org.bedu.movies_app_android.ui.presenters.FragmentDetailPresenter
import org.bedu.movies_app_android.utils.getDirector
import org.bedu.movies_app_android.utils.getMovieCast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentDetail : Fragment(), FragmentDetailContract.View {
    private lateinit var adapter : RecyclerMovieDetailAdapter
    private lateinit var presenter: FragmentDetailContract.Presenter

    private lateinit var recycler: RecyclerView
    private var cast: MutableList<Cast> = mutableListOf()
    private var movieSelected:  List<MovieResult> = emptyList()
    private var director: Crew = Crew(profile_path = null)



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
                movieSelected = myArray.filterIsInstance<MovieResult>()
                adapter = RecyclerMovieDetailAdapter(movieSelected, cast, director)

                presenter.getMovieCastById(movieSelected[0].id)

                /*getMovieCredits(movieSelected[0].id){cast, director ->
                    adapter.actors = cast
                    adapter.director = director
                    recycler.adapter = adapter
                    adapter.notifyDataSetChanged()
                }*/
            }
        }



        return view
    }

/*
    private fun getMovieCredits(movieId: Int, callback: (List<Cast>, Crew) -> Unit) {
        lifecycleScope.launch {
            val call = MovieDbApi.endpoint.getCreditsByMovieId(movieId.toString())

            call.enqueue(object: Callback<CreditsResult> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<CreditsResult>,
                    response: Response<CreditsResult>
                ) {
                    val body = response.body()

                    val castResult = body?.let { getMovieCast(it.cast) }
                    val director = body?.let { getDirector(it.crew) }

                    if (!castResult.isNullOrEmpty()) {
                        if (director != null) {
                            callback(castResult, director)
                        }
                    }
                }

                override fun onFailure(call: Call<CreditsResult>, t: Throwable) {
                    Log.d("ERROR", t.toString())

                }
            })
        }
    }*/

    @SuppressLint("NotifyDataSetChanged")
    override fun showData(cast: List<Cast>, director: Crew) {
        adapter.actors = cast
        adapter.director = director
        recycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}