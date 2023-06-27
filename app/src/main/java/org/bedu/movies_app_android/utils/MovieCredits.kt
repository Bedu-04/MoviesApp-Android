package org.bedu.movies_app_android.utils

import org.bedu.movies_app_android.data.models.Cast
import org.bedu.movies_app_android.data.models.Crew


internal fun getMovieCast(originCast: List<Cast>): List<Cast> {
    val castWithImage = originCast.filter { it -> it.profile_path != null }

    return castWithImage
}


internal  fun getDirector(crew: List<Crew>): Crew {
    val director = crew.find { it -> it.job == "Director" } ?: return Crew(profile_path = "/yW6eiXF0CEXCHpqxqvEUZmq2mUq.jpg", name = "Not Found")

    if (director != null) {
        if (director.profile_path == null){
            director.profile_path = "/yW6eiXF0CEXCHpqxqvEUZmq2mUq.jpg"
        }
    }

    return  director
}

