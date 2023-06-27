package org.bedu.movies_app_android.data.network.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.bedu.movies_app_android.data.network.services.IMovieDBService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieDbApi {


    private const val TIMEOUT_CALL_SECONDS = 20L

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(TIMEOUT_CALL_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_CALL_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_CALL_SECONDS, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit
        .Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val endpoint = retrofit.create(IMovieDBService::class.java)
}