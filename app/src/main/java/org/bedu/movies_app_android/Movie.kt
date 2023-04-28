package org.bedu.movies_app_android

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
    val duration: Double,
    val directors: List<String>,
    val date: String,
    val rating: Double,
    val language: Language,
    val category: Category
)


