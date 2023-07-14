package org.bedu.movies_app_android.data.network.services

import org.bedu.movies_app_android.data.models.CreditsResult
import org.bedu.movies_app_android.data.models.MovieDB
import org.bedu.movies_app_android.data.models.MovieDBResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface IMovieDBService {

    /*@Headers({ "Accept: application/json"})*/
    @Headers("Accept: application/json", "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiM2U1NGIzOGJkMmQ2ODY0Yzk1MzIxYjNiYzVjZDJkMyIsInN1YiI6IjYxNDZhNTgxZTU1OTM3MDA2MTc3OGMxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rZqs0AaAiMSExT-2zQY5yvTeqxVjNY_zg4fXE--9Sz4")
    @GET("movie/now_playing?language=es-MEX&page=1")
    fun getCinemaListings():Call<MovieDBResult>

    @Headers("Accept: application/json", "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiM2U1NGIzOGJkMmQ2ODY0Yzk1MzIxYjNiYzVjZDJkMyIsInN1YiI6IjYxNDZhNTgxZTU1OTM3MDA2MTc3OGMxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rZqs0AaAiMSExT-2zQY5yvTeqxVjNY_zg4fXE--9Sz4")
    @GET("movie/{movieId}/credits?language=es-MEX")
    fun getCreditsByMovieId(@Path("movieId") movieId: String):Call<CreditsResult>


    @GET("search/movie")
    @Headers("Accept: application/json", "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiM2U1NGIzOGJkMmQ2ODY0Yzk1MzIxYjNiYzVjZDJkMyIsInN1YiI6IjYxNDZhNTgxZTU1OTM3MDA2MTc3OGMxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rZqs0AaAiMSExT-2zQY5yvTeqxVjNY_zg4fXE--9Sz4")
    fun searchMovie(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "es-US",
        @Query("page") page: Int = 1,
    ):Call<MovieDBResult>
}