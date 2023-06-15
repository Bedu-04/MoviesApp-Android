package org.bedu.movies_app_android.models

data class MovieDBResult(
    val dates: Dates,
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)