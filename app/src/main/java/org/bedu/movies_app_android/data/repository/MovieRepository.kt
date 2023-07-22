package org.bedu.movies_app_android.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.lifecycleScope

import org.bedu.movies_app_android.data.models.Cast
import org.bedu.movies_app_android.data.models.CreditsResult
import org.bedu.movies_app_android.data.models.Crew
import org.bedu.movies_app_android.data.models.MovieDBResult
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.data.network.api.MovieDbApi
import org.bedu.movies_app_android.utils.getDirector
import org.bedu.movies_app_android.utils.getMovieCast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository: DataRepository<MovieResult> {
    override fun getMovies(callback: (List<MovieResult>) -> Unit) {

            val call = MovieDbApi.endpoint.getCinemaListings();

            call.enqueue(object: Callback<MovieDBResult> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<MovieDBResult>, response: Response<MovieDBResult>) {
                    val body = response.body()
                    val movies = body?.results;
                    if (movies !== null ){
                        callback(movies)
                    }


                }

                override fun onFailure(call: Call<MovieDBResult>, t: Throwable) {
                    Log.d("ERROR", t.toString())
                }

            })

    }

    override fun getCastMovieById(movieId: String, callback: (List<Cast>, Crew) -> Unit) {
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

    override fun getMoviesByName(movieName: String, callback: (List<MovieResult>) -> Unit) {
        val call = MovieDbApi.endpoint.searchMovie(movieName);

        call.enqueue(object: Callback<MovieDBResult> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<MovieDBResult>, response: Response<MovieDBResult>) {
                val body = response.body()
                val movies = body?.results;
                if (movies !== null ){
                    callback(movies)
                }else {
                    callback(emptyList())
                }


            }


            override fun onFailure(call: Call<MovieDBResult>, t: Throwable) {
                Log.d("ERROR", t.toString())
            }
        })

    }

}