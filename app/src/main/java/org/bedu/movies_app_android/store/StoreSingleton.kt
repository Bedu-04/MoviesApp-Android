package org.bedu.movies_app_android.store

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import org.bedu.movies_app_android.data.database.entities.toDatabase
import org.bedu.movies_app_android.data.network.api.MovieDbApi
import org.bedu.movies_app_android.data.models.MovieDBResult
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.domain.model.Movie
import org.bedu.movies_app_android.domain.model.toDomain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Store {
    @Singleton
    @Provides
    @Named("STORE")
    fun store() = StoreSingleton.getInstance()

}

class StoreSingleton private constructor() {
    var moviesDB: MutableList<Movie> = mutableListOf<Movie>()
    var searchMoviesDB: MutableList<Movie> = mutableListOf<Movie>()

    companion object {
        @Volatile
        private var instance: StoreSingleton? = null

        fun getInstance(): StoreSingleton {
            return instance ?: synchronized(this) {
                instance ?: StoreSingleton().also { instance = it }
            }
        }
    }

    fun getFavorites(): List<Movie> {
        val movies = moviesDB.filter { m ->
            m.isFavorite
        }

        return movies
    }

    fun addFavoriteMovie(movieId: Int) {
        Log.d("agregando", "agregnado")
        moviesDB = moviesDB.map{ m ->
            if (m.id == movieId) {
                m.isFavorite = true
            }

            m
        } as MutableList<Movie>

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


}