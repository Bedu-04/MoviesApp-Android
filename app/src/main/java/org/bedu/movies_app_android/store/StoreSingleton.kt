package org.bedu.movies_app_android.store

import android.util.Log
import androidx.lifecycle.Lifecycling
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.bedu.movies_app_android.R
import org.bedu.movies_app_android.data.network.api.MovieDbApi
import org.bedu.movies_app_android.data.models.Category
import org.bedu.movies_app_android.data.models.Language
import org.bedu.movies_app_android.data.models.Movie
import org.bedu.movies_app_android.data.models.MovieDB
import org.bedu.movies_app_android.data.models.MovieDBResult
import org.bedu.movies_app_android.data.models.MovieResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreSingleton private constructor() {
    var moviesDB: MutableList<MovieResult> = mutableListOf<MovieResult>()
    var searchMoviesDB: MutableList<MovieResult> = mutableListOf<MovieResult>()

    companion object {
        @Volatile
        private var instance: StoreSingleton? = null

        fun getInstance(): StoreSingleton {
            return instance ?: synchronized(this) {
                instance ?: StoreSingleton().also { instance = it }
            }
        }
    }



    fun getFavorites(): MutableList<MovieResult>  {
        return moviesDB.filter { m ->
            m.isFavorite
        } as MutableList<MovieResult>
    }

    fun addFavoriteMovie(movieId: Int) {
        Log.d("agregando", "agregnado")
        moviesDB = moviesDB.map{ m ->
            if (m.id == movieId) {
                m.isFavorite = true
            }

            m
        } as MutableList<MovieResult>

    }

    fun deleteFavoriteMovie(movieId: Int) {
        moviesDB = moviesDB.map { movie ->
            if (movie.id == movieId) {
                movie.copy(isFavorite = false)
            } else {
                movie
            }
        }.toMutableList()
    }


    private fun getDataFromApi(): MutableList<MovieResult> {

        var moviesAux = emptyList<MovieResult>()

        runBlocking {
            val call = MovieDbApi.endpoint.getCinemaListings();

            call.enqueue(object: Callback<MovieDBResult> {
                override fun onResponse(call: Call<MovieDBResult>, response: Response<MovieDBResult>) {
                    val body = response.body()
                    val movies = body?.results;
                    if (movies !== null ){
                        Log.e("Respuesta","${response.body().toString()}")
                        Log.d("Movies", movies.size.toString())
                        moviesAux = movies
                    }

                }

                override fun onFailure(call: Call<MovieDBResult>, t: Throwable) {
                    Log.d("ERROR", t.toString())
                }

            })


        }
        return moviesAux as MutableList<MovieResult>
    }


    private fun loadStore(): MutableList<MovieResult> {
        if (moviesDB !== null && moviesDB.size === 0) {
            return getDataFromApi()
        }

      return moviesDB
    }

}