package org.bedu.movies_app_android.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.adapters.RecyclerMovieDBAdapter
import org.bedu.movies_app_android.adapters.RecyclerMovieDetailAdapter
import org.bedu.movies_app_android.api.MovieDbApi
import org.bedu.movies_app_android.models.Cast
import org.bedu.movies_app_android.models.CreditsResult
import org.bedu.movies_app_android.models.Crew
import org.bedu.movies_app_android.models.Movie
import org.bedu.movies_app_android.models.MovieResult
import org.bedu.movies_app_android.store.StoreSingleton
import org.bedu.movies_app_android.utils.getDirector
import org.bedu.movies_app_android.utils.getMovieCast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentDetail : Fragment() {
    private lateinit var recycler: RecyclerView
    private var cast: MutableList<Cast> = mutableListOf()
    private var movieSelected:  List<MovieResult> = emptyList()
    private var director: Crew = Crew(profile_path = null)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                val adapter = RecyclerMovieDetailAdapter(movieSelected, cast, director)

                getMovieCredits(movieSelected[0].id){cast, director ->
                    adapter.actors = cast
                    adapter.director = director
                    recycler.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        }



        return view
    }


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
    }

}