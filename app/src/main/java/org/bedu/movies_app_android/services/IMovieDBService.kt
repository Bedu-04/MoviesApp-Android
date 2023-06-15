package org.bedu.movies_app_android.services

import org.bedu.movies_app_android.models.MovieDB
import org.bedu.movies_app_android.models.MovieDBResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface IMovieDBService {

    /*@Headers({ "Accept: application/json"})*/
    @Headers("Accept: application/json", "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiM2U1NGIzOGJkMmQ2ODY0Yzk1MzIxYjNiYzVjZDJkMyIsInN1YiI6IjYxNDZhNTgxZTU1OTM3MDA2MTc3OGMxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rZqs0AaAiMSExT-2zQY5yvTeqxVjNY_zg4fXE--9Sz4")

    @GET("movie/now_playing?language=es-MEX&page=1")
    fun getCinemaListings():Call<MovieDBResult>
}