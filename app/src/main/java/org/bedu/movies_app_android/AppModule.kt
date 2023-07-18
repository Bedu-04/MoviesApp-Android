package org.bedu.movies_app_android

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.bedu.movies_app_android.data.models.MovieResult
import org.bedu.movies_app_android.data.repository.DataRepository
import org.bedu.movies_app_android.data.repository.FavoritesRepository
import org.bedu.movies_app_android.data.repository.MovieRepository


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Define las dependencias globales que necesites
    // Puedes proporcionar dependencias aquí utilizando @Provides

    // Ejemplo:
    @Provides
    fun provideDataRepository(): DataRepository<MovieResult> {
        return MovieRepository()
    }

}