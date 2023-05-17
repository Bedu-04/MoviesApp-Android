package org.bedu.movies_app_android.models

enum class Category {
    Comedia, Drama, Suspenso, Terror, Acción, CienciaFicción, Fantasía, Animación
}

enum class Language {
    Español, Inglés, Francés, Coreano
}

data class Movie(
    val id: Int,
    val name: String,
    val actors: List<String>,
    val directors: List<String>,
    val duration: Double,
    val date: String,
    val rating: Double,
    val language: Language,
    val category: Category,
    val resume: String,
    val image: Int,
)


